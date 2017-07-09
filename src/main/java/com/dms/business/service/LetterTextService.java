package com.dms.business.service;
 
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.dao.LetterTextDAO;
import com.dms.model.domain.LetterTextDomain;

@Service("letterTextService")
@Transactional(readOnly = true)
public class LetterTextService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
    LetterTextDAO letterTextDAO;
    
	@Transactional(readOnly = false)
    public LetterTextDomain createLetterText(LetterTextDomain letterText) {
    	return letterTextDAO.createLetterText(letterText);
    }

	public LetterTextDAO getLetterTextDAO() {
		return letterTextDAO;
	}

	public void setLetterTextDAO(LetterTextDAO letterTextDAO) {
		this.letterTextDAO = letterTextDAO;
	}
 

}