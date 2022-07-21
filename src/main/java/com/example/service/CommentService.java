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


<<<<<<< HEAD
    public void create(Community community, String content, Member name) {
=======
    public void create(Community community, String content/*, Member author*/) {
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setCommunity(community);
<<<<<<< HEAD
        comment.setAuthor(name);
=======
        //comment.setAuthor(author);
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
        this.commentRepository.save(comment);
    }


	
}
