package com.dms.business.service;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.dao.PersonDAO;
import com.dms.model.domain.PersonDomain;

@Service("personService")
@Transactional(readOnly = true)
public class PersonService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
    PersonDAO personDAO;
	
	List <PersonDomain> persons = new ArrayList <PersonDomain> ();
	List <PersonDomain> companies = new ArrayList <PersonDomain> ();
	Map <Integer, PersonDomain> allPeople = new HashMap <Integer, PersonDomain>();
	Map <String, PersonDomain> allPeopleByFirstName = new HashMap <String, PersonDomain>();
	
	SelectItem[] selectItemsPerson;
	SelectItem[] selectItemsCompany;
	
	public List <PersonDomain> findPersons() {
		if (persons.isEmpty())
		{
			persons = personDAO.findPersons();
		}
		return persons;
	}
	
	public List <PersonDomain> findCompanies() {
		if (companies.isEmpty())
		{
			companies = personDAO.findCompanies();
		}
		return companies;
	}
	
	public Map <Integer, PersonDomain> findAllPeople() {
		if (!allPeople.isEmpty())
		{
			return allPeople;
		}
		for (PersonDomain person : persons)
    	{
    		this.allPeople.put(person.getPerId(), person);
    	}
		for (PersonDomain company : companies)
    	{
    		this.allPeople.put(company.getPerId(), company);
    	}
		return allPeople;
	}
	
	public Map <String, PersonDomain> findAllPeopleByFirstName() {
		if (!allPeopleByFirstName.isEmpty())
		{
			return allPeopleByFirstName;
		}
		for (PersonDomain person : persons)
    	{
    		this.allPeopleByFirstName.put(person.getFirstName(), person);
    	}

		return allPeopleByFirstName;
	}
	
	public SelectItem[] getSelectItemsPerson() {
		if (selectItemsPerson!=null && selectItemsPerson.length>0)
		{
			return selectItemsPerson;
		}
		selectItemsPerson = new SelectItem[persons.size()];
    	int i = 0;
    	for (PersonDomain person : persons)
    	{
    		String name = person.getFirstName() + " " + person.getSurname();
    		selectItemsPerson[i] = new SelectItem(person.getPerId(), name);
    		this.allPeople.put(person.getPerId(), person);
    		i++;
    	}
    	return selectItemsPerson;
	}
	
	public SelectItem[] getSelectItemsCompany() {
		if (selectItemsCompany != null && selectItemsCompany.length > 0) {
			return selectItemsCompany;
		}
		selectItemsCompany = new SelectItem[companies.size()];
		int i = 0;
		for (PersonDomain company : companies) {
			String name = company.getCompanyName();
			selectItemsCompany[i] = new SelectItem(company.getPerId(), name);
			this.allPeople.put(company.getPerId(), company);
			i++;
		}
		return selectItemsCompany;
	}

}