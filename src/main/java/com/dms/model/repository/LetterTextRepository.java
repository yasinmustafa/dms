package com.dms.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dms.model.entity.LetterText;

public interface LetterTextRepository extends JpaRepository<LetterText, Integer> {
  
}
