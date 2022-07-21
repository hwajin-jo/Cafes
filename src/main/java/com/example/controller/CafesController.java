package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.CafesFormDto;
import com.example.entity.Cafes;
import com.example.service.CafesService;

import lombok.RequiredArgsConstructor;

//@RequestMapping("/cafes")
@Controller
@RequiredArgsConstructor
public class CafesController {

	private final CafesService cafesService;
	
	//ī�� ���
	@RequestMapping("/cafes/list")
	public String list(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesUser";
	}
	

	//ī�� �Ұ��� �� ������
	//ī�� ��� - ������
	@RequestMapping("/admin/list")
	public String listAdmin(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesList";
	}
	
	//ī�� �Ұ��� �� ������

	@RequestMapping("/cafes/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailUser";
	}
	
	// ī�� �Ұ��� �� ������ - ������
	@RequestMapping("/admin/detail/{id}")
	public String detailAdmin(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailAdmin";
	}
	
	//ī�� �Ұ��� �ۼ� - �����ڸ� �ۼ� ����
	@RequestMapping("/admin/create")
	public String cafesCreate() {
		return "cafes/cafeWrite";
	}
	@GetMapping("/admin/create")
	public String cafesCreate(CafesFormDto cafesFormDto) {
		return "cafes/cafeWrite";
	}
	@PostMapping("/admin/create")
	public String cafesCreate(@Valid CafesFormDto cafesFormDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors())	return "cafes/cafeWrite";
		this.cafesService.create(cafesFormDto.getSubject(), cafesFormDto.getSubtitle(), cafesFormDto.getContent(),
								cafesFormDto.getCafeImage(), cafesFormDto.getAddress());
		return "redirect:/admin/list";
	}
	
	
	
	
}
