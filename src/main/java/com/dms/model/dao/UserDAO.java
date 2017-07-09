package com.dms.model.dao;
 
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.domain.UserDomain;
import com.dms.model.entity.User;
import com.dms.model.repository.UserRepository;
import com.dms.utils.DbUtils;

@Component("userDAO")
public class UserDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager transactionManager;
    
    @Autowired
    private UserRepository repository;
    
    @Autowired 
    Mapper mapper;
        
    public UserDomain findUserById(Integer id)
    {
    	return mapper.map(repository.findOne(id), UserDomain.class);
    }
    
    public List <UserDomain> findByNameAndPassword(String name, String password)
    {
    	List <User> persons = repository.findUsersByNameAndPassword(name, password);
    	return DbUtils.map(mapper, persons, UserDomain.class);
    }
    
    
}