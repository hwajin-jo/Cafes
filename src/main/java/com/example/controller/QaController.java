package com.example.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.DataNotFoundException;
import com.example.dto.QaFormDto;
import com.example.entity.Member;
import com.example.entity.QnaAnswer;
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
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String answerModify(QaFormDto qaFormDto,
								@PathVariable("id") Integer id,
								Principal principal) {
		QnaAnswer qnaAnswer = this.qaService.getAnswer(id);
		
		if (!qnaAnswer.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
		qaFormDto.setContent(qnaAnswer.getContent());
		return "/qna/answerForm";
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid QaFormDto qaFormDto,
							   BindingResult bindingResult,
							   @PathVariable("id") Integer id,
							   Principal principal) {
		if(bindingResult.hasErrors()) {
			return "/qnaanswer/answerForm";
		}
		QnaAnswer answer = this.qaService.getAnswer(id);
		if (!answer.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
		this.qaService.modify(answer, qaFormDto.getContent());
		return String.format("redirect:/qna/detail/%s", answer.getQuestion().getId());
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String answerDelete(Principal principal,
							   @PathVariable("id") Integer id) {
		QnaAnswer answer = this.qaService.getAnswer(id);
		if (!answer.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	}
		this.qaService.delete(answer);
		return String.format("redirect:/qna/detail/%s", answer.getQuestion().getId());
	}
}
