package com.dms.model.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.domain.DocumentDetails;
import com.dms.model.domain.DocumentDomain;
import com.dms.model.entity.Document;
import com.dms.model.entity.Letter;
import com.dms.utils.DbUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/test-applicationContext.xml" })
@Transactional
public class DaoTest {

	@PersistenceContext
	private EntityManager transactionManager;

	@Autowired
	DocumentDAO documentDAO;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Rollback(false)
	public void testDocinsertQuery() {

		DocumentDomain documentDomain = new DocumentDomain();
		documentDAO.createDocument(documentDomain);
	}

	@Test
	@Ignore
	public void testNativeQuery() {

		Document d = new Document();
		d.setExt("B");
		transactionManager.persist(d);
		Letter l = new Letter();
		transactionManager.persist(l);
		d.setLetter(l);
		transactionManager.persist(d);
		transactionManager.flush();

		Query query = transactionManager
				.createNamedQuery("findAllDocumentDetails");
		List<DocumentDetails> docs = query.getResultList();
		Assert.assertSame(5, docs.size());
		Assert.assertTrue(docs.get(4).getExt().equals("B"));
	}

	@Test
	@Ignore
	public void testNativeQuery2() {

		Document d = new Document();
		d.setExt("B");
		d.setDateOnLetter(Calendar.getInstance().getTime());
		transactionManager.persist(d);
		Letter l = new Letter();
		transactionManager.persist(l);
		d.setLetter(l);
		transactionManager.persist(d);
		transactionManager.flush();

		StringBuffer sb = new StringBuffer();
		sb.append(DbUtils.SQL_DOCUMENT_DETAILS);
		Query query = transactionManager.createNativeQuery(sb.toString(),
				"document-details");

		List<DocumentDetails> docs = query.getResultList();
		Assert.assertSame(5, docs.size());
		Assert.assertTrue(docs.get(4).getExt().equals("B"));
	}

}
