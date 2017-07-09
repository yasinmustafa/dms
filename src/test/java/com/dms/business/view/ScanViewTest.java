package com.dms.business.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.dms.business.service.DocumentService;
import com.dms.business.service.LetterService;
import com.dms.business.service.LetterTextService;
import com.dms.model.domain.DocumentDetails;
import com.dms.model.domain.ScanDocument;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/test-applicationContext.xml"})
@Ignore
public class ScanViewTest {
	
	@Autowired
    DocumentService documentService;
	
	@Autowired
    LetterService letterService;
	
	@Autowired
    LetterTextService letterTextService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_storeDoc() throws IOException, SAXException, TikaException, TesseractException {
		ScanView scanView = new ScanView();
		scanView.setDocumentService(documentService);
		ScanDocument scanDocument = new ScanDocument();
		scanDocument.setDocTitle("test");
		scanDocument.setComment("haha");
		scanView.setScanDocument(scanDocument);
		scanView.storeDocument(null);
		List <DocumentDetails> d = documentService.findDocumentDetails();
		Assert.assertTrue(d.size()==5);
		Assert.assertTrue(d.get(4).getComText().equals("haha"));
		
	}

}
