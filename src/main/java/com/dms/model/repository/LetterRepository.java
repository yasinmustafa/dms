package com.dms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dms.model.entity.Letter;

public interface LetterRepository extends JpaRepository<Letter, Integer> {
  
}
