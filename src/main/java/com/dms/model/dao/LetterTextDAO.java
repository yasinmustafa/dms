package com.dms.model.dao;
 
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.domain.LetterTextDomain;
import com.dms.model.entity.LetterText;
import com.dms.model.repository.LetterTextRepository;

@Component("letterTextDAO")
public class LetterTextDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager transactionManager;
    
    @Autowired
    private LetterTextRepository repository;
    
    @Autowired 
    Mapper mapper;
        
    public LetterTextDomain createLetterText(LetterTextDomain LetterTextDomain)
    {
    	LetterText LetterText = mapper.map(LetterTextDomain, LetterText.class);
    	return mapper.map(repository.save(LetterText), LetterTextDomain.class);
     
    }
    
    
}