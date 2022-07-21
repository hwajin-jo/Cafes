package com.example.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer>{
	
<<<<<<< HEAD
	Page<Community> findAll(Pageable pageable);
=======
//	Page<Community> findAll(Pageable pageable);
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
}
