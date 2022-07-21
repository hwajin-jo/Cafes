package com.example.service;

import java.io.Console;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
import com.example.entity.Member;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public Member getMember(String name) {
        Optional<Member> member = this.memberRepository.findByname(name);

        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
		
    }
}
