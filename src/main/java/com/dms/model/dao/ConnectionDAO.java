package com.dms.model.dao;
 
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.domain.ConnectionDomain;
import com.dms.model.entity.Connection;
import com.dms.model.repository.ConnectionRepository;

@Component("connectionDAO")
public class ConnectionDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager transactionManager;
    
    @Autowired
    private ConnectionRepository repository;
    
    @Autowired 
    Mapper mapper;
        
    public ConnectionDomain createConnection(ConnectionDomain ConnectionDomain)
    {
    	Connection Connection = mapper.map(ConnectionDomain, Connection.class);
    	return mapper.map(repository.save(Connection), ConnectionDomain.class);
     
    }
    
    public ConnectionDomain findConnectionById(Integer id)
    {
    	return mapper.map(repository.findOne(id), ConnectionDomain.class);
    }
    
    
}