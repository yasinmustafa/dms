package com.dms.model.dao;
 
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.domain.LetterDomain;
import com.dms.model.entity.Letter;
import com.dms.model.repository.LetterRepository;

@Component("letterDAO")
public class LetterDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager transactionManager;
    
    @Autowired
    private LetterRepository repository;
    
    @Autowired 
    Mapper mapper;
        
    public LetterDomain createLetter(LetterDomain LetterDomain)
    {
    	Letter Letter = mapper.map(LetterDomain, Letter.class);
    	return mapper.map(repository.save(Letter), LetterDomain.class);
     
    }
    
    public LetterDomain findLetterById(Integer letId)
    {
    	return mapper.map(repository.findOne(letId), LetterDomain.class);
    }
    
    
}