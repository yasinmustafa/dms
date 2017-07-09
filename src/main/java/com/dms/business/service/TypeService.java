package com.dms.business.service;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.dao.TypeDAO;
import com.dms.model.domain.TypeDomain;

@Service("typeService")
@Transactional(readOnly = true)
public class TypeService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
    TypeDAO typeDAO;
	
	List <TypeDomain> types = new ArrayList <TypeDomain> ();
	Map <Integer, TypeDomain> allTypes = new HashMap <Integer, TypeDomain>();
	Map <String, TypeDomain> allTypesByName = new HashMap <String, TypeDomain>();
	List <SelectItem> selectItemsType;
	
	public List <TypeDomain> findTypes() {
		if (types.isEmpty())
		{
			types = typeDAO.findTypes();
			Collections.sort(types);
		}
		return types;
	}
	
	public Map <Integer, TypeDomain> findAllTypes() {
		if (!allTypes.isEmpty())
		{
			return allTypes;
		}
		for (TypeDomain type : types)
    	{
    		this.allTypes.put(type.getTypId(), type);
    	}
		
		return allTypes;
	}
	
	public Map <String, TypeDomain> findTypesByName() {
		if (!allTypesByName.isEmpty())
		{
			return allTypesByName;
		}
		for (TypeDomain type : types)
    	{
    		this.allTypesByName.put(type.getName(), type);
    	}
		
		return allTypesByName;
	}
	
	public List <SelectItem> getSelectItemsType() {
		if (selectItemsType!=null && !selectItemsType.isEmpty())
		{
			return selectItemsType;
		}
		selectItemsType = new ArrayList <SelectItem> ();
    	for (TypeDomain type : types)
    	{
    		String name = type.getName();
    		selectItemsType.add(new SelectItem(type.getTypId(), name));
    		this.allTypes.put(type.getTypId(), type);
    	}
    	return selectItemsType;
	}
	

}