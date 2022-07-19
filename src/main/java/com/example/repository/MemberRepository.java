package com.example.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.entity.Member;

public interface MemberRepository extends JpaRepositoryImplementation<Member, Long>{

	Member findByEmail(String email);
}
