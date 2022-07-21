package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.entity.Comment;
import com.example.entity.Community;
import com.example.entity.Member;
import com.example.repository.CommentRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public void create(Community community, String content, Member name) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setCommunity(community);
        comment.setAuthor(name);
        this.commentRepository.save(comment);
    }


	
}
