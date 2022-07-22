package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
import com.example.entity.Comment;
import com.example.entity.Community;
import com.example.entity.Member;
import com.example.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.Optional;

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
    
    public Comment getComment(Integer commentNo) {
        Optional<Comment> comment = this.commentRepository.findById(commentNo);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    
    public void modify(Comment comment, String content) {
        comment.setContent(content);
        this.commentRepository.save(comment);
    }
    
    public void delete(Comment comment) {
    	this.commentRepository.delete(comment);
    }

}
