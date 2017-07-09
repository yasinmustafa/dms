package com.dms.business.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import com.dms.business.service.PersonService;
import com.dms.business.service.TypeService;
import com.dms.model.domain.PersonDomain;
import com.dms.model.domain.TypeDomain;

@ManagedBean(name="refDataView")
@ApplicationScoped
public class RefDataView implements Serializable {
     
	private static final long serialVersionUID = 1L;

	private List<SelectItem> peopleAndCompanies;
	Map <Integer, PersonDomain> allPeople = new HashMap <Integer, PersonDomain>();
	private List<SelectItem> types;
	Map <Integer, TypeDomain> allTypes = new HashMap <Integer, TypeDomain>();

    @ManagedProperty("#{personService}")
    private PersonService personService;
    
    @ManagedProperty("#{typeService}")
    private TypeService typeService;
 
    @PostConstruct
    public void init() {
    	allPeople = new HashMap <Integer, PersonDomain>();
    	addPersons();
    	addTypes();
    }
    
    protected void addTypes() {
    	typeService.findTypes();
    	allTypes = typeService.findAllTypes();
    	types = typeService.getSelectItemsType();
    }
    
    protected void addPersons() {
    	
    	SelectItemGroup personGroup = new SelectItemGroup("People");
    	SelectItemGroup companyGroup = new SelectItemGroup("Companies");
    	
    	personService.findPersons();
    	personService.findCompanies();
    	allPeople = personService.findAllPeople();
    	personGroup.setSelectItems(personService.getSelectItemsPerson());
    	companyGroup.setSelectItems(personService.getSelectItemsCompany());
    	
    	peopleAndCompanies = new ArrayList<SelectItem>();
        peopleAndCompanies.add(personGroup);
        peopleAndCompanies.add(companyGroup);
    }
    
	public List<SelectItem> getPeopleAndCompanies() {
		return peopleAndCompanies;
	}

	public void setPeopleAndCompanies(List<SelectItem> peopleAndCompanies) {
		this.peopleAndCompanies = peopleAndCompanies;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	public List<SelectItem> getTypes() {
		return types;
	}

	public void setTypes(List<SelectItem> types) {
		this.types = types;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
     
 
}