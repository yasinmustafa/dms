package com.dms.business.service;
 
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.dao.DocumentDAO;
import com.dms.model.domain.DocumentDetails;
import com.dms.model.domain.DocumentDomain;
import com.dms.model.domain.SearchParams;

@Service("documentService")
@Transactional(readOnly = true)
public class DocumentService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
    DocumentDAO documentDAO;
	
	public List <DocumentDetails> findDocumentDetails(SearchParams searchParams)
    {
		if (searchParams==null || allPropertiesNull(searchParams))
		{
			return documentDAO.findDocumentDetails();
		} else {
			return documentDAO.findDocumentDetailsBySearchParams(searchParams);
		}
    }
	
	public boolean allPropertiesNull(SearchParams bean) {
		PropertyDescriptor [] descriptors = PropertyUtils.getPropertyDescriptors(bean);
		Object value = null;
		for ( int i = 0; i < descriptors.length; i++ ) {
		     try {
				value = PropertyUtils.getSimpleProperty(bean, descriptors[i].getName());
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				e.printStackTrace();
				continue;
			}
		     if (value !=null)
		    	 return false;
		     
		 }        
		return true;
	}
    
    public List <DocumentDetails> findDocumentDetails()
    {
    	return documentDAO.findDocumentDetails();
    }
    
    public DocumentDomain findDocumentById(Integer id)
    {
    	return documentDAO.findDocumentById(id);
    }
    
    @Transactional(readOnly = false)
    public DocumentDomain createDocument(DocumentDomain document) {
    	return documentDAO.createDocument(document);
    }
    
    @Transactional(readOnly = false)
    public void updateDocument(DocumentDomain document) {
    	documentDAO.updateDocument(document);
    }

	public DocumentDAO getDocumentDAO() {
		return documentDAO;
	}

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
 

}