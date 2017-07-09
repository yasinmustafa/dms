package com.dms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dms.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	public List <Person> findPersonsByCompanyInd(Integer companyInd);
  
}
