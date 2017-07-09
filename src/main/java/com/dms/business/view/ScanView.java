package com.dms.business.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.dms.business.service.ClassificationService;
import com.dms.business.service.DocumentService;
import com.dms.business.service.LetterService;
import com.dms.business.service.LetterTextService;
import com.dms.business.service.PersonService;
import com.dms.business.service.TypeService;
import com.dms.model.domain.DocumentDomain;
import com.dms.model.domain.LetterDomain;
import com.dms.model.domain.LetterTextDomain;
import com.dms.model.domain.PersonDomain;
import com.dms.model.domain.ScanDocument;
import com.dms.model.domain.TypeDomain;

@ManagedBean(name="scanView")
@ViewScoped
public class ScanView implements Serializable {
     
	private static final long serialVersionUID = 1L;
	private List <ScanDocument> scanDocuments = new ArrayList<ScanDocument> ();
	
	private ScanDocument scanDocument;
	
	Map <Integer, PersonDomain> allPeople = new HashMap <Integer, PersonDomain>();
	Map <Integer, TypeDomain> allTypes = new HashMap <Integer, TypeDomain>();
	
	final String INCOMING = "/scan/incoming/";
	final String PROCESSED = "/scan/processed/";
	final String datapath = "/scan/tessdata/";
     
    @ManagedProperty("#{documentService}")
    private DocumentService documentService;
    
    @ManagedProperty("#{letterService}")
    private LetterService letterService;
    
    @ManagedProperty("#{letterTextService}")
    private LetterTextService letterTextService;
    
    @ManagedProperty("#{personService}")
    private PersonService personService;
    
    @ManagedProperty("#{typeService}")
    private TypeService typeService;
    
    @ManagedProperty("#{classificationService}")
    private ClassificationService classificationService;
    
    private StreamedContent file;
    
    private String fileName;
 
    @PostConstruct
    public void init() {
    	allPeople = new HashMap <Integer, PersonDomain>();
    	addDocumentsForScan(INCOMING);
    	addPersons();
    	addTypes();
    }
    
    public void autoScan(ActionEvent actionEvent) throws IOException, SAXException, TikaException, TesseractException {
    	File[] files = getFilesInDirectory(INCOMING);
    	if (files==null)
    	{
    		return;
    	}
    	for (File file : files) {
    		DocumentDomain document = new DocumentDomain();
    		if (!file.getName().toLowerCase().endsWith("pdf")) {
    			if (file.getName().toLowerCase().endsWith("tif")
    					|| file.getName().toLowerCase().endsWith("jpg")
    					|| file.getName().toLowerCase().endsWith("bmp")) {
    				processImage(file, document);
    				continue;
    				
    			} else {
    				continue;
    			}
    			
    		}
    		ContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);
    		Metadata metadata = new Metadata();
    		parsePdf(file, handler, metadata);
    		String result = handler.toString();
    		String[] lines;
    		if (result.trim().isEmpty())
    		{
    			result = parseOcr(file);
    			lines = result.toString().split("\\r?\\n");
    		} else {
    			lines = handler.toString().split("\\r?\\n");
    		}
        	document.setPersonByDocSentToPerId(classificationService.getPersonTo(result));
        	document.setType(classificationService.getCorrectType(result));
        	document.setDateOnLetter(classificationService.determineDateOnLetterForType(lines, document.getType()));
        	String[] fileNameArray = file.getName().split("\\.");
        	document.setFilename(file.getName());
			document.setExt(fileNameArray[1].toLowerCase());
			document.setAutoInserted(true);
        	document.setIndexedDate(Calendar.getInstance().getTime());
        	DocumentDomain documentDomain = documentService.createDocument(document);
        	addDocumentContents(file, documentDomain, lines, metadata);
        	moveToProcessed(INCOMING+file.getName(), documentDomain.getId().toString() + "." + documentDomain.getExt());
    	}
    	scanDocuments = new ArrayList<ScanDocument> ();
    }
    
    protected void processImage(File file, DocumentDomain document) throws IOException, SAXException, TikaException, TesseractException {
    	
		String result = parseOcr(file);
		String[] lines = result.toString().split("\\r?\\n");
		document.setPersonByDocSentToPerId(classificationService.getPersonTo(result));
    	document.setType(classificationService.getCorrectType(result));
    	document.setDateOnLetter(classificationService.determineDateOnLetterForType(lines, document.getType()));
    	String[] fileNameArray = file.getName().split("\\.");
    	document.setFilename(file.getName());
		document.setExt(fileNameArray[1].toLowerCase());
		document.setAutoInserted(true);
    	document.setIndexedDate(Calendar.getInstance().getTime());
    	DocumentDomain documentDomain = documentService.createDocument(document);
    	addDocumentContentsForImage(file, documentDomain, lines);
    	moveToProcessed(INCOMING+file.getName(), documentDomain.getId().toString() + "." + documentDomain.getExt());
    	
    }
    
    protected String parseOcr(File file) throws TesseractException {
    		Tesseract instance = new Tesseract();
            instance.setDatapath(datapath);
            String result = instance.doOCR(file);
            return result;
            

    }
    
    public void openDocument(ScanDocument scanDocument) throws IOException {
    	File inFile = new File(INCOMING + scanDocument.getDocTitle());
    	InputStream stream = new FileInputStream(inFile.getCanonicalPath());
    	String contentType = new MimetypesFileTypeMap().getContentType(inFile);
    	file = new DefaultStreamedContent(stream, contentType, inFile.getName());
    }
    
    protected void addTypes() {
    	typeService.findTypes();
    	allTypes = typeService.findAllTypes();
    }
    
    protected void addPersons() {
    	personService.findPersons();
    	personService.findCompanies();
    	allPeople = personService.findAllPeople();
    	
    }
    

    protected void addDocumentsForScan(String directory) {
    	File[] files = getFilesInDirectory(directory);
    	if (files==null)
    	{
    		return;
    	}
    	for (File file : files) {
    		ScanDocument scanDocument = new ScanDocument();
    		scanDocument.setDocTitle(file.getName());
    		scanDocuments.add(scanDocument);
    	}
    }
    
    protected void parsePdf(File file, ContentHandler handler, Metadata metadata) throws IOException, SAXException, TikaException {
    	
    	InputStream input = new FileInputStream(file.getAbsolutePath());
        PDFParser pdfParser = new PDFParser();
    	pdfParser.parse(input, handler, metadata, new ParseContext());
    }
     
    protected File[] getFilesInDirectory(String directoryName) {
        File directory = new File(directoryName);
        File[] files = directory.listFiles();
        return files;
    }
    
    public void storeDocument(ActionEvent actionEvent) throws IOException, SAXException, TikaException, TesseractException {
    	DocumentDomain document = new DocumentDomain();
    	if (scanDocument.getPersonFrom()!=null)
    		document.setPersonByDocSentFromPerId(allPeople.get(scanDocument.getPersonFrom()));
    	if (scanDocument.getPersonTo()!=null)
    		document.setPersonByDocSentToPerId(allPeople.get(scanDocument.getPersonTo()));
    	if (scanDocument.getType()!=null)
    		document.setType(allTypes.get(scanDocument.getType()));
    	document.setDateOnLetter(scanDocument.getDateOnLetter());
    	document.setFilename(scanDocument.getDocTitle());
    	document.setComText(scanDocument.getComment());
    	document.setExt(getFileExtension(scanDocument.getDocTitle()));
    	document.setIndexedDate(Calendar.getInstance().getTime());
    	DocumentDomain documentDomain = documentService.createDocument(document);
    	addDocumentContents(new File(INCOMING+scanDocument.getDocTitle()), documentDomain, null, null);
    	moveToProcessed(INCOMING+scanDocument.getDocTitle(), documentDomain.getId().toString() + "." + documentDomain.getExt());
    	scanDocuments.remove(scanDocument);
    	setScanDocument(new ScanDocument());
    	
    }
    
    /**
     * Add document contents
     * 
     * @param file
     * @param documentDomain
     * @param lines - optional. set to null if not 'auto inserting' 
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     * @throws TesseractException 
     */
	protected void addDocumentContents(File file, DocumentDomain documentDomain, String [] lines, Metadata metadata)
			throws IOException, SAXException, TikaException, TesseractException {
		if (!file.getName().toLowerCase().endsWith("pdf")) {
			return;
		}
		

		if (lines==null)
		{
			metadata = new Metadata();
			ContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);
			parsePdf(file, handler, metadata);
			String result = handler.toString();
			if (result.trim().isEmpty())
			{
				result = parseOcr(file);
				lines = result.toString().split("\\r?\\n");
			} else {
				lines = handler.toString().split("\\r?\\n");
			}
		}
		
				
		LetterDomain letter = new LetterDomain();
		for (String name : metadata.names()) {
			if (name.equals("Content-Type"))
				letter.setMetaContentType(metadata.get(name));
			else if (name.equals("Content-Type"))
				letter.setMetaContentType(metadata.get(name));
			else if (name.equals("producer"))
				letter.setMetaProducer(metadata.get(name));
			else if (name.equals("meta:creation-date"))
				letter.setMetaCreationDate(convertToDate(metadata.get(name)));
			else if (name.equals("xmpTPg:NPages"))
				letter.setMetaNoPages(Integer.parseInt(metadata.get(name)));
			else if (name.equals("dcterms:created"))
				letter.setMetaCreatedDate(convertToDate(metadata.get(name)));
			else if (name.equals("Last-Modified"))
				letter.setMetaLastModifiedDate(convertToDate(metadata.get(name)));
			else if (name.equals("date"))
				letter.setMetaDate(convertToDate(metadata.get(name)));
			else if (name.equals("dcterms:modified"))
				letter.setMetaModifiedDate(convertToDate(metadata.get(name)));
			else if (name.equals("meta:save-date"))
				letter.setMetaSaveDate(convertToDate(metadata.get(name)));
		}
		letter.setDocumentId(documentDomain.getId());
		LetterDomain createdLetter = letterService.createLetter(letter);
		
		
		for (int i=0; i<lines.length; i++)
		{
			LetterTextDomain letterText = new LetterTextDomain();
			letterText.setSequence(i+1);
			letterText.setLetId(createdLetter.getLetId());
			if (lines[i].contains("###"))
			{
				String [] twoPart = lines[i].split("###");
				letterText.setText(twoPart[0]);
				letterText.setExtract(twoPart[1]);
			}
			else
			{
				letterText.setText(lines[i]);
			}
			letterTextService.createLetterText(letterText);
		}

	}
	
	protected void addDocumentContentsForImage(File file, DocumentDomain documentDomain, String [] lines)
			throws IOException, SAXException, TikaException, TesseractException {
		if (!file.getName().toLowerCase().endsWith("tif")
				&& !file.getName().toLowerCase().endsWith("jpg")
				&& !file.getName().toLowerCase().endsWith("bmp")) {
			return;
		}

		if (lines==null)
		{
			String result = parseOcr(file);
			lines = result.toString().split("\\r?\\n");
		}
		BasicFileAttributes metadata = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		LetterDomain letter = new LetterDomain();
		
		
			if (metadata.creationTime()!=null)
				letter.setMetaCreationDate(new Date(metadata.creationTime().toMillis()));
			

			if (metadata.lastModifiedTime()!=null)
				letter.setMetaLastModifiedDate(new Date(metadata.lastModifiedTime().toMillis()));

		letter.setDocumentId(documentDomain.getId());
		LetterDomain createdLetter = letterService.createLetter(letter);
		
		
		for (int i=0; i<lines.length; i++)
		{
			LetterTextDomain letterText = new LetterTextDomain();
			letterText.setSequence(i+1);
			letterText.setLetId(createdLetter.getLetId());
			if (lines[i].contains("###"))
			{
				String [] twoPart = lines[i].split("###");
				letterText.setText(twoPart[0]);
				letterText.setExtract(twoPart[1]);
			}
			else
			{
				letterText.setText(lines[i]);
			}
			letterTextService.createLetterText(letterText);
		}

	}
    
    protected Date convertToDate(String metaDate) {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, Integer.parseInt(metaDate.substring(0, 4)));
    	cal.set(Calendar.MONTH, Integer.parseInt(metaDate.substring(5, 7)));
    	cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(metaDate.substring(8, 10)));
    	
    	cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(metaDate.substring(11, 13)));
    	cal.set(Calendar.MINUTE, Integer.parseInt(metaDate.substring(14, 16)));
    	cal.set(Calendar.SECOND, Integer.parseInt(metaDate.substring(17, 19)));
    	return cal.getTime();
    }
    
    protected void moveToProcessed(String longFileName, String newFileName)
    {
    	File file = new File(longFileName);
    	file.renameTo(new File(PROCESSED+newFileName));
    }
    
    protected String getFileExtension(String fileName) {
    	if (fileName.contains(".")) {
    		return fileName.split("\\.")[1];
    	}
    	return fileName.toLowerCase();
    }

	public ScanDocument getScanDocument() {
		return scanDocument;
	}

	public void setScanDocument(ScanDocument scanDocument) {
		this.scanDocument = scanDocument;
	}


	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public LetterService getLetterService() {
		return letterService;
	}

	public void setLetterService(LetterService letterService) {
		this.letterService = letterService;
	}

	public LetterTextService getLetterTextService() {
		return letterTextService;
	}

	public void setLetterTextService(LetterTextService letterTextService) {
		this.letterTextService = letterTextService;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<ScanDocument> getScanDocuments() {
		return scanDocuments;
	}

	public void setScanDocuments(List<ScanDocument> scanDocuments) {
		this.scanDocuments = scanDocuments;
	}

	public ClassificationService getClassificationService() {
		return classificationService;
	}

	public void setClassificationService(ClassificationService classificationService) {
		this.classificationService = classificationService;
	}
     
 
}