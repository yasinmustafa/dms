package com.dms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dms.model.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
	
}
