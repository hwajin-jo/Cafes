package com.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
import com.example.entity.Member;
import com.example.entity.QnaAnswer;
import com.example.entity.QnaQuestion;
import com.example.repository.QqRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QqService {
	
	private final QqRepository qqRepository;
	
	private Specification<QnaQuestion> search(String kw){
		return new Specification<QnaQuestion>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<QnaQuestion> q, CriteriaQuery<?> query,
										 CriteriaBuilder cb) {
				query.distinct(true); //중복제거
				Join<QnaQuestion, Member> u1 = q.join("author", JoinType.LEFT);
				Join<QnaQuestion, QnaAnswer> a = q.join("answerList", JoinType.LEFT);
				Join<QnaAnswer, Member> u2 = a.join("author", JoinType.LEFT);
				return cb.or(cb.like(q.get("subject"), "%" + kw + "%"),
					   cb.like(q.get("content"), "%" + kw + "%"),
//					   cb.like(u1.get("name"), "%" + kw + "%"),
//					   cb.like(u2.get("name"), "%" + kw + "%"),
					   cb.like(q.get("content"), "%" + kw + "%"));
				
			}
		};
	}

	public Page<QnaQuestion> getList(int page, String kw){
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Specification<QnaQuestion> spec = search(kw);
		return this.qqRepository.findAll(spec, pageable);
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
	
	public void vote(QnaQuestion question, Member member) {
		question.getVoter().add(member);
		this.qqRepository.save(question);
	}
	
	

}
