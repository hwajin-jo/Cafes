package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
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
	
	public Page<Community> getList(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return this.communityRepository.findAll(pageable);
	}
	
	public void delete(Community community) {
		this.communityRepository.delete(community);
	}
	
}
