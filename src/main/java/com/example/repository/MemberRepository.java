package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.entity.Member;

public interface MemberRepository extends JpaRepositoryImplementation<Member, Long>{

	Member findByEmail(String email);
	
	Optional<Member> findByname(String name);
}
