package com.example.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.entity.Member;
import com.example.entity.QnaAnswer;
import com.example.entity.QnaQuestion;
import com.example.repository.QaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QaService {

	private final QaRepository qaRepository;
	
	public void create(QnaQuestion question, String content, Member author) {
		QnaAnswer answer = new QnaAnswer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.qaRepository.save(answer);
	}
	
}
