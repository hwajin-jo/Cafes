package com.example.service;

import java.time.LocalDateTime;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
import com.example.entity.Comment;
import com.example.entity.Community;
import com.example.entity.Member;
import com.example.repository.CommunityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityService {
	
	private final CommunityRepository communityRepository;
	
	public List<Community> getList(){
		return this.communityRepository.findAll();
	}
	
	public Community getCommunity(Integer boardNo) {
		Optional<Community> community = this.communityRepository.findById(boardNo);
		if(community.isPresent()) {
			return community.get();
		}else {
			throw new DataNotFoundException("community not found");
		}
	}
	
	public void create(String subject, String content, Member name) {
        Community community = new Community();
        community.setSubject(subject);
        community.setContent(content);
        community.setCreateDate(LocalDateTime.now());
        community.setAuthor(name);
        this.communityRepository.save(community);
    }
	
	public void modify(Community community, String subject, String content) {
		community.setSubject(subject);
		community.setContent(content);
        this.communityRepository.save(community);
    }
	
	public Page<Community> getList(int page, String kw){
		Pageable pageable = PageRequest.of(page, 10);
		Specification<Community> spec = search(kw);
		return this.communityRepository.findAll(spec, pageable);
	}
	
	public void delete(Community community) {
		this.communityRepository.delete(community);
	}
	
	//검색 부분
	private Specification<Community> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Community> c, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<Community, Member> u1 = c.join("author", JoinType.LEFT);
                Join<Community, Comment> a = c.join("commentList", JoinType.LEFT);
                Join<Comment, Member> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(c.get("subject"), "%" + kw + "%"), // 제목 
                        cb.like(c.get("content"), "%" + kw + "%"),      // 내용 
                        cb.like(u1.get("name"), "%" + kw + "%"),    // 질문 작성자 
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용 
                        cb.like(u2.get("name"), "%" + kw + "%"));   // 답변 작성자 
            }
        };
    }
	
}
