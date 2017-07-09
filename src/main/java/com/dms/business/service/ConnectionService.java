package com.dms.business.service;
 
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.dao.ConnectionDAO;
import com.dms.model.domain.ConnectionDomain;

@Service("connectionService")
@Transactional(readOnly = true)
public class ConnectionService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
    ConnectionDAO connectionDAO;
    
	@Transactional(readOnly = false)
    public ConnectionDomain createConnection(ConnectionDomain connection) {
    	return connectionDAO.createConnection(connection);
    }

	public ConnectionDAO getConnectionDAO() {
		return connectionDAO;
	}

	public void setConnectionDAO(ConnectionDAO connectionDAO) {
		this.connectionDAO = connectionDAO;
	}
 
	public ConnectionDomain findConnectionById(Integer id)
    {
    	return connectionDAO.findConnectionById(id);
    }

}