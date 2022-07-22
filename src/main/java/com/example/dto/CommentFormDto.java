package com.example.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFormDto {
	
    @NotEmpty(message = "댓글 내용을 입력해 주세요.")
    private String content;
}