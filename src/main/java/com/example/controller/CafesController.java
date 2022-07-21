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
	//Ä«ï¿½ï¿½ ï¿½ï¿½ï¿½
=======
	//Ä«Æä ¸ñ·Ï
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	@RequestMapping("/cafes/list")
	public String list(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesUser";
	}
	
<<<<<<< HEAD

	//Ä«ï¿½ï¿½ ï¿½Ò°ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	//Ä«ï¿½ï¿½ ï¿½ï¿½ï¿½ - ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
=======
	//Ä«Æä ¸ñ·Ï - °ü¸®ÀÚ
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	@RequestMapping("/admin/list")
	public String listAdmin(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesList";
	}
	
<<<<<<< HEAD
	//Ä«ï¿½ï¿½ ï¿½Ò°ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½

=======
	//Ä«Æä ¼Ò°³±Û »ó¼¼ ÆäÀÌÁö
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	@RequestMapping("/cafes/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailUser";
	}
	
<<<<<<< HEAD
	// Ä«ï¿½ï¿½ ï¿½Ò°ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ - ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
=======
	// Ä«Æä ¼Ò°³±Û »ó¼¼ ÆäÀÌÁö - °ü¸®ÀÚ
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
	@RequestMapping("/admin/detail/{id}")
	public String detailAdmin(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailAdmin";
	}
	
<<<<<<< HEAD
	//Ä«ï¿½ï¿½ ï¿½Ò°ï¿½ï¿½ï¿½ ï¿½Û¼ï¿½ - ï¿½ï¿½ï¿½ï¿½ï¿½Ú¸ï¿½ ï¿½Û¼ï¿½ ï¿½ï¿½ï¿½ï¿½
=======
	//Ä«Æä ¼Ò°³±Û ÀÛ¼º - °ü¸®ÀÚ¸¸ ÀÛ¼º °¡´É
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
	//Ä«Æä ¼Ò°³±Û ¼öÁ¤
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
