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
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.CafesFormDto;
import com.example.entity.Cafes;
import com.example.service.CafesService;

import lombok.RequiredArgsConstructor;

//@RequestMapping("/cafes")
@Controller
@RequiredArgsConstructor
public class CafesController {

	private final CafesService cafesService;
	
	//移댄럹 紐⑸줉
	@RequestMapping("/cafes/list")
	public String list(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesUser";
	}
	
	//移댄럹 紐⑸줉 - 愿�由ъ옄
	@RequestMapping("/admin/list")
	public String listAdmin(Model model) {
		List<Cafes> cafes = cafesService.getList();
		model.addAttribute("cafes", cafes);
		return "cafes/cafesList";
	}
	
	//移댄럹 �냼媛쒓� �긽�꽭 �럹�씠吏�
	@RequestMapping("/cafes/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailUser";
	}
	
	// 移댄럹 �냼媛쒓� �긽�꽭 �럹�씠吏� - 愿�由ъ옄
	@RequestMapping("/admin/detail/{id}")
	public String detailAdmin(Model model, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		model.addAttribute("cafes", cafes);
		return "cafes/cafeDetailAdmin";
	}
	
	//移댄럹 �냼媛쒓� �옉�꽦 - 愿�由ъ옄留� �옉�꽦 媛��뒫
	@RequestMapping("/admin/create")
	public String cafesCreate() {
		return "cafes/cafeWrite";
	}
	@GetMapping("/admin/create")
	public String cafesCreate(CafesFormDto cafesFormDto) {
		return "cafes/cafeWrite";
	}
	@PostMapping("/admin/create")
	public String cafesCreate(@Valid CafesFormDto cafesFormDto, MultipartFile file, BindingResult bindingResult) {
		if(bindingResult.hasErrors())	return "cafes/cafeWrite";
		this.cafesService.create(cafesFormDto.getSubject(), cafesFormDto.getSubtitle(), cafesFormDto.getContent(),
								file, cafesFormDto.getAddress());
		return "redirect:/admin/list";
	}
	
	//移댄럹 �냼媛쒓� �닔�젙
	@GetMapping("/admin/modify/{id}")
	public String cafesModify(CafesFormDto cafesFormDto, MultipartFile file, @PathVariable("id") Long id) {
		Cafes cafes = this.cafesService.getCafes(id);
		
		cafesFormDto.setSubject(cafes.getSubject());
		cafesFormDto.setSubtitle(cafes.getSubtitle());
		cafesFormDto.setContent(cafes.getContent());
//		cafesFormDto.setCafeImage(cafes.getCafeImage());
		cafesFormDto.setAddress(cafes.getAddress());
		
		return "cafes/cafeWrite";
	}
	@PostMapping("/admin/modify/{id}")
	public String cafesModify(@Valid CafesFormDto cafesFormDto,
								 MultipartFile file,
								 BindingResult bindingResult,
								 @PathVariable("id") Long id) {
		if(bindingResult.hasErrors())	return "cafes/cafeWrite";
		
		Cafes cafes = this.cafesService.getCafes(id);
		
		this.cafesService.modify(cafes, cafesFormDto.getSubject(), cafesFormDto.getSubtitle(), cafesFormDto.getContent(),
								file, cafesFormDto.getAddress());
		
		return String.format("redirect:/admin/detail/%s", id);
	}
	
	//移댄럹 �냼媛쒓� �궘�젣
	@GetMapping("/admin/delete/{id}")
    public String cafesDelete(@PathVariable("id") Long id) {
        Cafes cafes = this.cafesService.getCafes(id);
        this.cafesService.delete(cafes);
        return "redirect:/admin/list";
    }
	

	
	
}