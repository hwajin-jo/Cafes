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
	
<<<<<<< HEAD
	//ī�� ���
=======
	//ī�� ���
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	@RequestMapping("/cafes/list")
	public String list(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesUser";
	}
	
<<<<<<< HEAD

	//ī�� �Ұ��� �� ������
	//ī�� ��� - ������
=======
	//ī�� ��� - ������
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	@RequestMapping("/admin/list")
	public String listAdmin(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesList";
	}
	
<<<<<<< HEAD
	//ī�� �Ұ��� �� ������

=======
	//ī�� �Ұ��� �� ������
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	@RequestMapping("/cafes/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailUser";
	}
	
<<<<<<< HEAD
	// ī�� �Ұ��� �� ������ - ������
=======
	// ī�� �Ұ��� �� ������ - ������
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	@RequestMapping("/admin/detail/{id}")
	public String detailAdmin(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailAdmin";
	}
	
<<<<<<< HEAD
	//ī�� �Ұ��� �ۼ� - �����ڸ� �ۼ� ����
=======
	//ī�� �Ұ��� �ۼ� - �����ڸ� �ۼ� ����
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
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
	
<<<<<<< HEAD
	
=======
	//ī�� �Ұ��� ����
	@GetMapping("/admin/modify/{id}")
	public String questionModify(CafesFormDto cafesFormDto, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		
		cafesFormDto.setSubject(cafes.getSubject());
		cafesFormDto.setSubtitle(cafes.getSubtitle());
		cafesFormDto.setContent(cafes.getContent());
		cafesFormDto.setCafeImage(cafes.getCafeImage());
		cafesFormDto.setAddress(cafes.getAddress());
		
		return "cafes/cafesWrite";
	}
	@PostMapping("/admin/modify/{id}")
	public String questionModify(@Valid CafesFormDto cafesFormDto,
								 BindingResult bindingResult,
								 @PathVariable("id") Long id) {
		if(bindingResult.hasErrors())	return "cafes/cafeWrite";
		
		Cafes cafes = this.cafesService.getCafes(id);
		
		this.cafesService.modify(cafes, cafesFormDto.getSubject(), cafesFormDto.getSubtitle(), cafesFormDto.getContent(),
								cafesFormDto.getCafeImage(), cafesFormDto.getAddress());
		
		return String.format("redirect:/admin/list/detail/%s", id);
	}
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	
	
}
