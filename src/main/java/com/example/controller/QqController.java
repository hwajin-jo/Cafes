package com.example.controller;

import java.security.Principal;
import java.util.List;
import javax.security.auth.Subject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.DataNotFoundException;
import com.example.dto.QaFormDto;
import com.example.dto.QqFormDto;
import com.example.entity.Member;
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
	public String list(Model model,
					   @RequestParam(value="page",defaultValue = "0") int page) {
		Page<QnaQuestion> paging = this.qqService.getList(page);
		model.addAttribute("paging",paging);
		return "/qna/qnaList";
		
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id,
						 QaFormDto qaFormDto) throws DataNotFoundException {
		QnaQuestion question = this.qqService.getQuestion(id);
		model.addAttribute("question",question);
		return "/qna/qnaDetail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate( QqFormDto qqFormDto) {
		return "/qna/qnaForm";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate( @Valid QqFormDto qqFormDto,
								  BindingResult bindingResult,
								  Principal principal) {
		if(bindingResult.hasErrors()) {
			return "/qna/qnaForm";
		}
		Member member = this.memberService.getMember(principal.getName());
		this.qqService.create(qqFormDto.getSubject(), qqFormDto.getContent(), member);
		return "redirect:/qna/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QqFormDto qqFormDto,
								 @PathVariable("id") Integer id,
								 Principal principal) {
		
		QnaQuestion question = this.qqService.getQuestion(id);
		
		if(!question.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
		qqFormDto.setSubject(question.getSubject());
		qqFormDto.setContent(question.getContent());
		return "/qna/qnaForm";
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify( @Valid QqFormDto qqFormDto,
								  BindingResult bindingResult,
								  Principal principal,
								  @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "qna/qnaForm";
		}
		
		QnaQuestion qnaQuestion = this.qqService.getQuestion(id);
		
		if (!qnaQuestion.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.qqService.modify(qnaQuestion, qqFormDto.getSubject(), qqFormDto.getContent());
		return String.format("redirect:/qna/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal,
								 @PathVariable("id") Integer id) {
		QnaQuestion qnaQuestion = this.qqService.getQuestion(id);
		
		if (!qnaQuestion.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
		this.qqService.delete(qnaQuestion);
		return "redirect:/qna/list";
		
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String questionVote(Principal principal,
							   @PathVariable("id") Integer id) {
		QnaQuestion question = this.qqService.getQuestion(id);
		Member member = this.memberService.getMember(principal.getName());
		this.qqService.vote(question, member);
		return String.format("redirect:/qna/detail/%s", id);
	}
	
}
