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

import com.example.dto.CommentFormDto;
import com.example.entity.Comment;
import com.example.entity.Community;
import com.example.entity.Member;
import com.example.service.CommentService;
import com.example.service.CommunityService;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommunityService communityService;
    private final CommentService commentService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{commentNo}")
    public String createAnswer(Model model, @PathVariable("commentNo") Integer commentNo, 
    										@Valid CommentFormDto commentForm, 
    										BindingResult bindingResult, 
    										Principal principal) {
    	Community community = this.communityService.getCommunity(commentNo);
    	Member member = this.memberService.getMember(principal.getName());
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("community", community);
    		return "community/community_detail";
    	}
        this.commentService.create(community, commentForm.getContent(), member);
        return String.format("redirect:/community/detail/%s", commentNo);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{commentNo}")
    public String commentModify(CommentFormDto commentFormDto, 
    						   @PathVariable("commentNo") Integer commentNo, 
    						   Principal principal) {
        Comment comment = this.commentService.getComment(commentNo);
        if (!comment.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        commentFormDto.setContent(comment.getContent());
        return "community/comment_form";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{commentNo}")
    public String commentModify(@Valid CommentFormDto commentFormDto, 
    						   BindingResult bindingResult,
    						   @PathVariable("commentNo") Integer commentNo, 
    						   Principal principal) {
        if (bindingResult.hasErrors()) {
            return "community/comment_form";
        }
        Comment comment = this.commentService.getComment(commentNo);
        if (!comment.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.commentService.modify(comment, commentFormDto.getContent());
        return String.format("redirect:/community/detail/%s", comment.getCommunity().getBoardNo());
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{commentNo}")
    public String answerDelete(Principal principal, @PathVariable("commentNo") Integer commentNo) {
        Comment comment = this.commentService.getComment(commentNo);
        if (!comment.getAuthor().getName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.commentService.delete(comment);
        return String.format("redirect:/community/detail/%s", comment.getCommunity().getBoardNo());
    }
}
