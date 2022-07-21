package com.example.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.DataNotFoundException;
import com.example.dto.QaFormDto;
import com.example.entity.Member;
import com.example.entity.QnaQuestion;
import com.example.service.MemberService;
import com.example.service.QaService;
import com.example.service.QqService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/qnaanswer")
@RequiredArgsConstructor
@Controller
public class QaController {

	private final QqService qqService;
	private final QaService qaService;
	private final MemberService memberService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id,
								@Valid QaFormDto qaFormDto,
								BindingResult bindingResult,
								Principal principal) throws DataNotFoundException {
		
		QnaQuestion question = this.qqService.getQuestion(id);
		Member member = this.memberService.getMember(principal.getName());
		if(bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "/qna/qnaDetail";
        }
		this.qaService.create(question, qaFormDto.getContent(), member);
		return String.format("redirect:/qna/detail/%s", id);
	}
	
}
