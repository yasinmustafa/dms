package com.dms.business.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.dao.UserDAO;
import com.dms.model.domain.UserDomain;

@Service("userService")
@Transactional(readOnly = true)
public class UserService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDomain findUserById(Integer id) {
		return userDAO.findUserById(id);
	}

	public List<UserDomain> findByNameAndPassword(String name, String password) {

		return userDAO.findByNameAndPassword(name, password);
	}

}