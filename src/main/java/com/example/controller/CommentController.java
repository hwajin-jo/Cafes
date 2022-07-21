package com.example.controller;

import java.security.Principal;

import javax.validation.Valid;

<<<<<<< HEAD
import org.springframework.security.access.prepost.PreAuthorize;
=======
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.CommentFormDto;
import com.example.entity.Community;
import com.example.entity.Member;
import com.example.service.CommentService;
import com.example.service.CommunityService;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/community")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommunityService communityService;
    private final CommentService commentService;
    private final MemberService memberService;

<<<<<<< HEAD
    @PreAuthorize("isAuthenticated()")
=======
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
    @PostMapping("/create/{boardNo}")
    public String createAnswer(Model model, @PathVariable("boardNo") Integer boardNo, 
    										@Valid CommentFormDto commentForm, 
    										BindingResult bindingResult, 
    										Principal principal) {
    	Community community = this.communityService.getCommunity(boardNo);
<<<<<<< HEAD
    	Member member = this.memberService.getMember(principal.getName());
=======
    	//Member member = this.memberService.getMember(principal.getName());
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("community", community);
    		return "community/community_detail";
    	}
<<<<<<< HEAD
        this.commentService.create(community, commentForm.getContent(), member);
=======
        this.commentService.create(community, commentForm.getContent()/*, member*/);
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
        return String.format("redirect:/community/detail/%s", boardNo);
    }
}
