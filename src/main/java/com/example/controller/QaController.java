package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DataNotFoundException;
import com.example.entity.QnaQuestion;
import com.example.service.QaService;
import com.example.service.QqService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/qnaanswer")
@RequiredArgsConstructor
@Controller
public class QaController {

	private final QqService qqService;
	private final QaService qaService;
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id,
								@RequestParam String content) throws DataNotFoundException {
		QnaQuestion question = this.qqService.getQuestion(id);
		this.qaService.create(question, content);
		return String.format("redirect:/qna/detail/%s", id);
	}
	
}
