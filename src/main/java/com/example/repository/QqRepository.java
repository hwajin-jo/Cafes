package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.QnaQuestion;

public interface QqRepository extends JpaRepository<QnaQuestion, Integer> {

	QnaQuestion findBySubject(String subject);
	QnaQuestion findByContent(String content);
	QnaQuestion findBySubjectAndContent(String subject, String content);
	List<QnaQuestion> findBySubjectLike(String subject);
	Page<QnaQuestion> findAll(Pageable page);
	Page<QnaQuestion> findAll(Specification<QnaQuestion> spec, Pageable pageable);
	
}
