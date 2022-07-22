package com.example.controller;

import java.security.Principal;
import java.util.List;

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
	public String list(Model model, 
					   @RequestParam(value="page", defaultValue="0") int page, 
					   @RequestParam(value="kw", defaultValue="") String kw) {
		Page<Community> paging = this.communityService.getList(page, kw);
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		return "community/community_list";
	}
	
	@RequestMapping(value="/detail/{boardNo}")
	public String detail(Model model, @PathVariable("boardNo") Integer boardNo, CommentFormDto commentForm) {
		Community community = this.communityService.getCommunity(boardNo);
		model.addAttribute("community", community);
		return "community/community_detail";
	}
	
	//게시글 작성Get
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
    public String communityCreate(CommunityFormDto communityForm) {
        return "community/write";
    }
	
	//게시글 작성Post
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
	
	//게시글 수정Get
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{boardNo}")
    public String communityModify(CommunityFormDto communityFormDto, @PathVariable("boardNo") Integer boardNo, Principal principal) {
		Community community = this.communityService.getCommunity(boardNo);
        if(!community.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        communityFormDto.setSubject(community.getSubject());
        communityFormDto.setContent(community.getContent());
        return "community/write";
    }
	
	//게시글 수정Post
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{boardNo}")
    public String communityModify(CommunityFormDto communityFormDto, 
    							  BindingResult bindingResult,
    							  Principal principal,
    							  @PathVariable("boardNo") Integer boardNo) {
		if (bindingResult.hasErrors()) {
            return "community/write";
        }
        Community community = this.communityService.getCommunity(boardNo);
        if(!community.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        this.communityService.modify(community, communityFormDto.getSubject(), communityFormDto.getContent());
        return String.format("redirect:/community/detail/%s", boardNo);
    }
	
	//게시글 삭제 코드 Get
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{boardNo}")
    public String communityDelete(Principal principal, @PathVariable("boardNo") Integer boardNo) {
        Community community = this.communityService.getCommunity(boardNo);
        if (!community.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.communityService.delete(community);
        return "redirect:/community/list";
    }
	
	
}
