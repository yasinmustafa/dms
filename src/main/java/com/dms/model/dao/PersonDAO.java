package com.dms.model.dao;
 
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.domain.PersonDomain;
import com.dms.model.entity.Person;
import com.dms.model.repository.PersonRepository;
import com.dms.utils.DbUtils;

@Component("personDAO")
public class PersonDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager transactionManager;
    
    @Autowired
    private PersonRepository repository;
    
    @Autowired 
    Mapper mapper;
        
    public PersonDomain createPerson(PersonDomain personDomain)
    {
    	Person Person = mapper.map(personDomain, Person.class);
    	return mapper.map(repository.save(Person), PersonDomain.class);
     
    }
    
    public List <PersonDomain> findPersons()
    {
    	List <Person> persons = repository.findPersonsByCompanyInd(0);
    	return DbUtils.map(mapper, persons, PersonDomain.class);
    }
    
    public List <PersonDomain> findCompanies()
    {
    	List <Person> companies = repository.findPersonsByCompanyInd(1);
    	return DbUtils.map(mapper, companies, PersonDomain.class);
    }
    
    
}