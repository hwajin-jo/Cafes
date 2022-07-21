package com.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
import com.example.entity.Member;
import com.example.entity.QnaQuestion;
import com.example.repository.QqRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QqService {
	
	private final QqRepository qqRepository;

	public Page<QnaQuestion> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
<<<<<<< HEAD
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
=======
		Pageable pageable = PageRequest.of(page, 7, Sort.by(sorts));
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
		return this.qqRepository.findAll(pageable);
	}
	
	public QnaQuestion getQuestion(Integer id) throws DataNotFoundException {
		Optional<QnaQuestion> question = this.qqRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		}
		throw new DataNotFoundException("question not found");
	}
	
	public void create(String subject, String content, Member member) {
		QnaQuestion q = new QnaQuestion();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(member);
		this.qqRepository.save(q);
	}
	
	public void modify(QnaQuestion question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.qqRepository.save(question);
	}
	
	public void delete(QnaQuestion qnaQuestion) {
		this.qqRepository.delete(qnaQuestion);
	}
<<<<<<< HEAD
=======
	
	public void vote(QnaQuestion question, Member member) {
		question.getVoter().add(member);
		this.qqRepository.save(question);
	}
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250

}
