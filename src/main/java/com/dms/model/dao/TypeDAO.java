package com.dms.model.dao;
 
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.domain.TypeDomain;
import com.dms.model.entity.Type;
import com.dms.model.repository.TypeRepository;
import com.dms.utils.DbUtils;

@Component("typeDAO")
public class TypeDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager transactionManager;
    
    @Autowired
    private TypeRepository repository;
    
    @Autowired 
    Mapper mapper;
        
    public TypeDomain createType(TypeDomain personDomain)
    {
    	Type Type = mapper.map(personDomain, Type.class);
    	return mapper.map(repository.save(Type), TypeDomain.class);
     
    }
    
    public List <TypeDomain> findTypes()
    {
    	List <Type> types = repository.findAll();
    	return DbUtils.map(mapper, types, TypeDomain.class);
    }
    
    
}