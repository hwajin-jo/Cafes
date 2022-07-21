package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Community c = new Community();
        c.setSubject(subject);
        c.setContent(content);
        c.setCreateDate(LocalDateTime.now());
        c.setAuthor(name);
        this.communityRepository.save(c);
    }
}
