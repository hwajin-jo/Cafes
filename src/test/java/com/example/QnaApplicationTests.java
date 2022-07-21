package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.QnaAnswer;
import com.example.entity.QnaQuestion;
import com.example.repository.QaRepository;
import com.example.repository.QqRepository;

@SpringBootTest
class QnaApplicationTests {

	@Autowired
	QqRepository qqRepository;
	
	@Autowired
	QaRepository qaRepository;
	
	@Test
	void contextLoads() {
		
		QnaQuestion q1 = new QnaQuestion();
		q1.setSubject("테스트 제목1");
		q1.setContent("테스트 내용1");
		q1.setCreateDate(LocalDateTime.now());
		this.qqRepository.save(q1);
		
		QnaQuestion q2 = new QnaQuestion();
		q2.setSubject("테스트 제목2");
		q2.setContent("테스트 내용2");
		q2.setCreateDate(LocalDateTime.now());
		this.qqRepository.save(q2);
		
		Optional<QnaQuestion> oq = this.qqRepository.findById(1);
		assertTrue(oq.isPresent());
		QnaQuestion q = oq.get();
		
		QnaAnswer a = new QnaAnswer();
		a.setContent("테스트 답글");
		a.setQuestion(q);
		this.qaRepository.save(a);
		
		
		
	}

}