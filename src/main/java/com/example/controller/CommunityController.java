package com.example.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.CommentFormDto;
import com.example.dto.CommunityFormDto;
import com.example.entity.Community;
import com.example.entity.Member;
import com.example.service.CommunityService;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/community")
@RequiredArgsConstructor
@Controller
public class CommunityController {
	
	private final CommunityService communityService;
	private final MemberService memberService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Community> communityList = this.communityService.getList();
		model.addAttribute("communityList", communityList);
		return "community/community_list";
	}
	
	@RequestMapping(value="/detail/{boardNo}")
	public String detail(Model model, @PathVariable("boardNo") Integer boardNo, CommentFormDto commentForm) {
		Community community = this.communityService.getCommunity(boardNo);
		model.addAttribute("community", community);
		return "community/community_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
    public String communityCreate(CommunityFormDto communityForm) {
        return "community/write";
    }
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
    public String communityCreate(@Valid CommunityFormDto communityForm, 
    							  BindingResult bindingResult,
    							  Principal principal) {
		if (bindingResult.hasErrors()) {
            return "community/write";
        }
		Member member = this.memberService.getMember(principal.getName());
		this.communityService.create(communityForm.getSubject(), communityForm.getContent(), member);
        return "redirect:/community/list"; 
    }
	
	
}
