package com.example.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
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
	
	public QnaAnswer getAnswer(Integer id) {
		Optional<QnaAnswer> answer = this.qaRepository.findById(id);
		if(answer.isPresent()) {
			return answer.get();
		}else {
			throw new DataNotFoundException("answer not found");
		}
		
	}
	
	public void modify(QnaAnswer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.qaRepository.save(answer);
	}
	
	public void delete(QnaAnswer qnaAnswer) {
		this.qaRepository.delete(qnaAnswer);
	}
	
}
