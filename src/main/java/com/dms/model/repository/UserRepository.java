package com.dms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dms.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List <User> findUsersByNameAndPassword(String name, String password);
  
}
