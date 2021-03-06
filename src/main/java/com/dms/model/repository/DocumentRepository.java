package com.dms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dms.model.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
  
}
