package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Cafes;
import com.example.repository.CafesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CafesService {

	private final CafesRepository cafesRepository;
	
	public List<Cafes> getList() {
		return this.cafesRepository.findAll();
	}
	
	public Cafes getCafes(Long id) {
		Optional<Cafes> oc = this.cafesRepository.findById(id);
		
		if(oc.isPresent())	return oc.get();
		else	return oc.get();	//추가 구현
	}
	
	//게시글 생성
	public void create(String subject, String subtitle, String content, String cafeImage, String address) {
		Cafes cafes = new Cafes();
		
		cafes.setSubject(subject);
		cafes.setSubtitle(subtitle);
		cafes.setContent(content);
		cafes.setCafeImage(cafeImage);
		cafes.setAddress(address);
		cafes.setCreateDate(LocalDate.now());
		
		this.cafesRepository.save(cafes);
	}

	//게시글 수정
	public void modify(Cafes cafes, String subject, String subtitle, String content, String cafeImage, String address) {
		cafes.setSubject(subject);
		cafes.setSubtitle(subtitle);
		cafes.setContent(content);
		cafes.setCafeImage(cafeImage);
		cafes.setAddress(address);
		cafes.setModifyDate(LocalDate.now());
		
		this.cafesRepository.save(cafes);
	}
	
	//게시글 삭제
	public void delete(Cafes cafes) {
        this.cafesRepository.delete(cafes);
    }
	
}
