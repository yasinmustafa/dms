package com.dms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.dms.model.entity.Connection;

public interface ConnectionRepository extends RevisionRepository<Connection, Integer, Integer>, JpaRepository<Connection, Integer> {
  
}
