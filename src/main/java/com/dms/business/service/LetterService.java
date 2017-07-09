package com.dms.business.service;
 
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.dao.LetterDAO;
import com.dms.model.domain.LetterDomain;

@Service("letterService")
@Transactional(readOnly = true)
public class LetterService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
    LetterDAO letterDAO;
    
	@Transactional(readOnly = false)
    public LetterDomain createLetter(LetterDomain letter) {
    	return letterDAO.createLetter(letter);
    }

	public LetterDAO getLetterDAO() {
		return letterDAO;
	}

	public void setLetterDAO(LetterDAO letterDAO) {
		this.letterDAO = letterDAO;
	}
 
	public LetterDomain findLetterById(Integer letId)
    {
    	return letterDAO.findLetterById(letId);
    }

}