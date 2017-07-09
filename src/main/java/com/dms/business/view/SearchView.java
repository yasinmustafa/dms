package com.dms.business.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.tika.exception.TikaException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.xml.sax.SAXException;

import com.dms.business.service.DocumentService;
import com.dms.business.service.LetterService;
import com.dms.business.service.LetterTextService;
import com.dms.business.service.PersonService;
import com.dms.business.service.TypeService;
import com.dms.model.domain.DocumentDetails;
import com.dms.model.domain.DocumentDomain;
import com.dms.model.domain.PersonDomain;
import com.dms.model.domain.SearchParams;
import com.dms.model.domain.TypeDomain;


@ManagedBean(name="searchView")
@ViewScoped
public class SearchView implements Serializable {
     
	private static final long serialVersionUID = 1L;

	private List<DocumentDetails> documentDetails;
	
	private List<DocumentDetails> filteredDocumentDetails;
	
	private SearchParams searchParams;
	
	private DocumentDetails documentDetail;
	
	Map <Integer, PersonDomain> allPeople = new HashMap <Integer, PersonDomain>();
	Map <Integer, TypeDomain> allTypes = new HashMap <Integer, TypeDomain>();
	
	final String INCOMING = "/scan/incoming/";
	final String PROCESSED = "/scan/processed/";
     
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
    
    private StreamedContent file;
    
    private Integer fileName;
    
    public void openDocument(DocumentDetails documentDetail) throws IOException {
    	File processedFile = new File(PROCESSED + documentDetail.getId() + "." + documentDetail.getExt());
    	InputStream stream = new FileInputStream(processedFile.getCanonicalPath());
    	String contentType = new MimetypesFileTypeMap().getContentType(processedFile);
    	file = new DefaultStreamedContent(stream, contentType, processedFile.getName());
    }
 
    public StreamedContent getFile() {
        return file;
    }
 
    @PostConstruct
    public void init() {
    	searchParams = new SearchParams();
    	allPeople = new HashMap <Integer, PersonDomain>();
    	addPersons();
    	addTypes();
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
    
    public void search(ActionEvent actionEvent) {
    	setDocumentDetails(documentService.findDocumentDetails(getSearchParams()));
    	searchParams = new SearchParams();
    }
    
    public void clear(ActionEvent actionEvent) {
    	searchParams = new SearchParams();
    	setDocumentDetails(Collections.<DocumentDetails> emptyList());
    }
    
    public void storeDocument(ActionEvent actionEvent) throws IOException, SAXException, TikaException {
    	DocumentDomain document = documentService.findDocumentById(documentDetail.getId());
    	if (documentDetail.getPerFromId()!=null)
    		document.setPersonByDocSentFromPerId(allPeople.get(documentDetail.getPerFromId()));
    	if (documentDetail.getPerToId()!=null)
    		document.setPersonByDocSentToPerId(allPeople.get(documentDetail.getPerToId()));
    	if (documentDetail.getTypId()!=null)
    		document.setType(allTypes.get(documentDetail.getTypId()));
    	document.setDateOnLetter(documentDetail.getDateOnLetter());
    	document.setComText(documentDetail.getComText());
    	documentService.updateDocument(document);
    	setDocumentDetail(new DocumentDetails());
    	
    }

	public List<DocumentDetails> getDocumentDetails() {
		return documentDetails;
	}

	public void setDocumentDetails(List<DocumentDetails> documentDetails) {
		this.documentDetails = documentDetails;
	}

	public List<DocumentDetails> getFilteredDocumentDetails() {
		return filteredDocumentDetails;
	}

	public void setFilteredDocumentDetails(List<DocumentDetails> filteredDocumentDetails) {
		this.filteredDocumentDetails = filteredDocumentDetails;
	}

	public SearchParams getSearchParams() {
		return searchParams;
	}

	public void setSearchParams(SearchParams searchParams) {
		this.searchParams = searchParams;
	}

	public DocumentDetails getDocumentDetail() {
		return documentDetail;
	}

	public void setDocumentDetail(DocumentDetails documentDetail) {
		this.documentDetail = documentDetail;
	}

	public Map<Integer, PersonDomain> getAllPeople() {
		return allPeople;
	}

	public void setAllPeople(Map<Integer, PersonDomain> allPeople) {
		this.allPeople = allPeople;
	}

	public Map<Integer, TypeDomain> getAllTypes() {
		return allTypes;
	}

	public void setAllTypes(Map<Integer, TypeDomain> allTypes) {
		this.allTypes = allTypes;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public Integer getFileName() {
		return fileName;
	}

	public void setFileName(Integer fileName) {
		this.fileName = fileName;
	}
     
 
}