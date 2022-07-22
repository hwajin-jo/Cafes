package com.example.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public void create(String subject, String subtitle, String content, MultipartFile file, String address) {
		Cafes cafes = new Cafes();
		
		String fileName = null;
		if(file != null) {
			// 이미지 저장 위치
			String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";
	
			// 저장될 파일 이름 생성
			UUID uuid = UUID.randomUUID();
			fileName = uuid + "_" + file.getOriginalFilename();
	
			File saveFile = new File(projectPath, fileName);
			try {	//이미지 저장
				file.transferTo(saveFile);
			} catch (IllegalStateException e) {	e.printStackTrace();
			} catch (IOException e) {	e.printStackTrace();	}
		}
		cafes.setSubject(subject);
		cafes.setSubtitle(subtitle);
		cafes.setContent(content);
//		cafes.setCafeImage(cafeImage);
		cafes.setFileName(fileName);
		cafes.setFilePath("/img/" + fileName);
		cafes.setAddress(address);
		cafes.setCreateDate(LocalDate.now());
		
		this.cafesRepository.save(cafes);
	}

	//게시글 수정
	public void modify(Cafes cafes, String subject, String subtitle, String content, MultipartFile file, String address) {
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";

		UUID uuid = UUID.randomUUID();
		String fileName = uuid + "_" + file.getOriginalFilename();

		File saveFile = new File(projectPath, fileName);
		try {	//이미지 저장
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {	e.printStackTrace();
		} catch (IOException e) {	e.printStackTrace();	}
		
		cafes.setSubject(subject);
		cafes.setSubtitle(subtitle);
		cafes.setContent(content);
//		cafes.setCafeImage(cafeImage);
		cafes.setFileName(fileName);
		cafes.setFilePath("/img/" + fileName);
		cafes.setAddress(address);
		cafes.setModifyDate(LocalDate.now());
		
		this.cafesRepository.save(cafes);
	}
	
	//게시글 삭제
	public void delete(Cafes cafes) {
        this.cafesRepository.delete(cafes);
    }
	
}
