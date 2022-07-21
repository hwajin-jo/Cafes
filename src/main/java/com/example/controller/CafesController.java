package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	//Ä«ï¿½ï¿½ ï¿½ï¿½ï¿½
	@RequestMapping("/cafes/list")
	public String list(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesUser";
	}
	
<<<<<<< HEAD
	//Ä«ï¿½ï¿½ ï¿½Ò°ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
=======
	//Ä«Æä ¸ñ·Ï - °ü¸®ÀÚ
	@RequestMapping("/admin/list")
	public String listAdmin(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesList";
	}
	
	//Ä«Æä ¼Ò°³±Û »ó¼¼ ÆäÀÌÁö
>>>>>>> aa5dd956232a81f4f6f16393a8f40b1655f18892
	@RequestMapping("/cafes/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailUser";
	}
	
	// Ä«ï¿½ï¿½ ï¿½Ò°ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ - ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	@RequestMapping("/admin/detail/{id}")
	public String detailAdmin(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailAdmin";
	}
	
	//Ä«ï¿½ï¿½ ï¿½Ò°ï¿½ï¿½ï¿½ ï¿½Û¼ï¿½ - ï¿½ï¿½ï¿½ï¿½ï¿½Ú¸ï¿½ ï¿½Û¼ï¿½ ï¿½ï¿½ï¿½ï¿½
	@RequestMapping("/admin/create")
	public String cafesCreate() {
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
