package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
import com.example.entity.QnaQuestion;
import com.example.repository.QqRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QqService {
	
	private final QqRepository qqRepository;

	public List<QnaQuestion> getList() {
		return this.qqRepository.findAll();
	}
	
	public QnaQuestion getQuestion(Integer id) throws DataNotFoundException {
		Optional<QnaQuestion> question = this.qqRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		}
		throw new DataNotFoundException("question not found");
	}
	
	public void create(String subject, String content) {
		QnaQuestion q = new QnaQuestion();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		this.qqRepository.save(q);
	}

}
