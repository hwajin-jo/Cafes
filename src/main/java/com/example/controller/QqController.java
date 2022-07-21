package com.example.controller;

import java.util.List;
import javax.security.auth.Subject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DataNotFoundException;
import com.example.dto.QqFormDto;
import com.example.entity.QnaQuestion;
import com.example.repository.QqRepository;
import com.example.service.MemberService;
import com.example.service.QqService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/qna")
@Controller
@RequiredArgsConstructor
public class QqController {

	private final QqService qqService;
	private final MemberService memberService;
	private final QqRepository qqRepository;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<QnaQuestion> questionList = this.qqService.getList();
		model.addAttribute("questionList",questionList);
		return "/qna/qnaList";
		
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) throws DataNotFoundException {
		QnaQuestion question = this.qqService.getQuestion(id);
		model.addAttribute("question",question);
		return "/qna/qnaDetail";
	}
	
	@GetMapping("/create")
	public String questionCreate( QqFormDto qqFormDto) {
		return "/qna/qnaForm";
	}
	
	@PostMapping("/create")
	public String questionCreate( @Valid QqFormDto qqFormDto,
									BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/qna/qnaForm";
		}
		return "redirect:/qna/list";
	}
						
	
}
