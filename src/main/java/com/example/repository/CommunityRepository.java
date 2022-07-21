package com.example.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer>{
	
//	Page<Community> findAll(Pageable pageable);
}
