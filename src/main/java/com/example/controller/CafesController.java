package com.example.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.CafesFormDto;
import com.example.entity.Cafes;
import com.example.service.CafesService;

import lombok.RequiredArgsConstructor;

//@RequestMapping("/cafes")
@Controller
@RequiredArgsConstructor
public class CafesController {

	private final CafesService cafesService;
	
	//카페 목록
	@RequestMapping("/cafes/list")
	public String list() {
		return "cafes/cafesUser";
	}
	
	//카페 소개글 상세 페이지
	@RequestMapping("/cafes/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailUser";
	}
	
	// 카페 소개글 상세 페이지 - 관리자
	@RequestMapping("/admin/detail/{id}")
	public String detailAdmin(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailAdmin";
	}
	
	//카페 소개글 작성 - 관리자만 작성 가능
	@RequestMapping("/admin/create")
	public String cafesCreate() {
		return "cafes/cafeWrite";
	}
	
	
	
}
