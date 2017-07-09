package com.dms.model.dao;
 
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.domain.DocumentDetails;
import com.dms.model.domain.DocumentDomain;
import com.dms.model.domain.SearchParams;
import com.dms.model.entity.Document;
import com.dms.model.repository.DocumentRepository;
import com.dms.utils.DbUtils;

@Component("documentDAO")
public class DocumentDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager transactionManager;
    
    @Autowired
    private DocumentRepository repository;
    
    @Autowired 
    Mapper mapper;
    
    public List <DocumentDetails> findDocumentDetails()
    {
    	Query query = transactionManager.createNativeQuery(DbUtils.SQL_DOCUMENT_DETAILS,
                        "document-details"
    		);
    	//Query query = transactionManager.createNamedQuery("findAllDocumentDetails");
    	List<DocumentDetails> docs = query.getResultList();
    	return docs;
     
    }
    
    public List <DocumentDetails> findDocumentDetailsBySearchParams(SearchParams searchParams)
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append(DbUtils.SQL_DOCUMENT_DETAILS);
    	
    	if (searchParams.getIdFrom() != 0 && searchParams.getIdTo() != 0)
			sb.append(" AND ID BETWEEN :idFrom AND :idTo");
		if (searchParams.getIdFrom() == 0 && searchParams.getIdTo() != 0)
			sb.append(" AND ID = :idTo");
		if (searchParams.getIdFrom() != 0 && searchParams.getIdTo() == 0)
			sb.append(" AND ID = :idFrom");
   	
    	if (searchParams.getFrom() != null && searchParams.getTo() != null)
			sb.append(" AND DATE(DATE_ON_LETTER) BETWEEN DATE(:dtLetterFrom) AND DATE(:dtLetterTo)");
		if (searchParams.getFrom() == null && searchParams.getTo() != null)
			sb.append(" AND DATE_ON_LETTER = DATE(:dtLetterTo)");
		if (searchParams.getFrom() != null && searchParams.getTo() == null)
			sb.append(" AND DATE_ON_LETTER = DATE(:dtLetterFrom)");
		
		if (searchParams.getIndexedFrom() != null && searchParams.getIndexedTo() != null)
			sb.append(" AND INDEXED_DATE BETWEEN DATE(:dtIndexedFrom) and DATE(:dtIndexedTo)");
		if (searchParams.getIndexedFrom() == null && searchParams.getIndexedTo() != null)
			sb.append(" AND INDEXED_DATE = DATE(:dtIndexedTo)");
		if (searchParams.getIndexedFrom() != null && searchParams.getIndexedTo() == null)
			sb.append(" AND INDEXED_DATE = DATE(:dtIndexedFrom)");
			
		if (searchParams.getPersonFrom()!=null && searchParams.getPersonFrom()!=0)
			sb.append(" AND SENT_FROM_PER_ID = :personFrom"); 
			
		if (searchParams.getPersonTo()!=null && searchParams.getPersonTo()!=0)
			sb.append(" AND SENT_TO_PER_ID = :personTo");
			
		if (searchParams.getType()!=null && searchParams.getType()!=0)
			sb.append(" AND document.TYP_ID = :typeId");
			
		if (searchParams.getComment()!=null && searchParams.getComment().trim().length() > 0)
			sb.append(" AND LOWER(COM_TEXT) LIKE LOWER(:comments)");
			
		if (searchParams.getAutoInserted()!=null && searchParams.getAutoInserted()==true)
			sb.append(" AND doc_auto_inserted = 1");
		if (searchParams.getAutoInserted()!=null && searchParams.getAutoInserted()==false)
			sb.append(" AND doc_auto_inserted = 0");
			
		if (searchParams.getLetterTextSearch() != null
				&& searchParams.getLetterTextSearch().trim().length() > 0) {
			sb.append(" AND EXISTS ");
			sb.append(" (SELECT NULL ");
			sb.append(" FROM letter, letter_text ");
			sb.append(" WHERE letter.DOC_ID = document.ID ");
			sb.append(" and letter_text.let_id = letter.let_id ");
			sb.append(" AND LOWER(letter_text.TEXT) LIKE LOWER(:letterText))");
		}		
			
    	Query query = transactionManager.createNativeQuery(sb.toString(), "document-details");
    	
    	if (searchParams.getIdFrom() != 0 && searchParams.getIdTo() != 0){
			query.setParameter("idFrom", searchParams.getIdFrom());
			query.setParameter("idTo", searchParams.getIdTo());
		}
		if (searchParams.getIdFrom() == 0 && searchParams.getIdTo() != 0)
			query.setParameter("idTo", searchParams.getIdTo());
		if (searchParams.getIdFrom() != 0 && searchParams.getIdTo() == 0)
			query.setParameter("idFrom", searchParams.getIdFrom());
    	
    	if (searchParams.getFrom() != null && searchParams.getTo() != null) {
			query.setParameter("dtLetterFrom", searchParams.getFrom());
			query.setParameter("dtLetterTo", searchParams.getTo());
		}
    	if (searchParams.getFrom() == null && searchParams.getTo() != null)
    		query.setParameter("dtLetterTo", searchParams.getTo());
		if (searchParams.getFrom() != null && searchParams.getTo() == null)
			query.setParameter("dtLetterFrom", searchParams.getFrom());
    	if (searchParams.getIndexedFrom() != null && searchParams.getIndexedTo() != null) {
    		query.setParameter("dtIndexedFrom", searchParams.getIndexedFrom());
    		query.setParameter("dtIndexedTo", searchParams.getIndexedTo());
    	}
		if (searchParams.getIndexedFrom() == null && searchParams.getIndexedTo() != null)
			query.setParameter("dtIndexedTo", searchParams.getIndexedTo());
		if (searchParams.getIndexedFrom() != null && searchParams.getIndexedTo() == null)
			query.setParameter("dtIndexedFrom", searchParams.getIndexedFrom());
			
		if (searchParams.getPersonFrom()!=null && searchParams.getPersonFrom()!=0)
			query.setParameter("personFrom", searchParams.getPersonFrom()); 
			
		if (searchParams.getPersonTo()!=null && searchParams.getPersonTo()!=0)
			query.setParameter("personTo", searchParams.getPersonTo());
			
		if (searchParams.getType()!=null && searchParams.getType()!=0)
			query.setParameter("typeId", searchParams.getType());
			
		if (searchParams.getComment()!=null && searchParams.getComment().trim().length() > 0)
			query.setParameter("comments", "%"+searchParams.getComment()+"%");
		if (searchParams.getLetterTextSearch() != null
				&& searchParams.getLetterTextSearch().trim().length() > 0) 
			query.setParameter("letterText", "%"+searchParams.getLetterTextSearch()+"%");
			
    	List<DocumentDetails> docs = query.getResultList();
    	return docs;
     
    }
    
    public Calendar determineCalendarFrom(Date searchDateFrom) {
    	
    	if (searchDateFrom!=null)
    	{
    		Calendar calFrom = Calendar.getInstance();
    		calFrom.setTime(searchDateFrom);
    		return calFrom;
    	}
    	return null;
    }
    
    public Calendar determineCalendarTo(Date searchDateTo) {
    	
    	if (searchDateTo!=null)
    	{
    		Calendar calTo = Calendar.getInstance();
    		calTo.setTime(searchDateTo);
        	return calTo;
    	}
    	return null;
    }
    
    public DocumentDomain createDocument(DocumentDomain documentDomain)
    {
    	Document document = mapper.map(documentDomain, Document.class);
    	return mapper.map(repository.save(document), DocumentDomain.class);
     
    }
    
    public void updateDocument(DocumentDomain documentDomain)
    {
    	Document document = mapper.map(documentDomain, Document.class);
    	repository.saveAndFlush(document);
     
    }
    
    public DocumentDomain findDocumentById(Integer id) {
    	return mapper.map(repository.findOne(id), DocumentDomain.class);
    }
    
}