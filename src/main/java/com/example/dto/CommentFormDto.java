package com.example.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFormDto {
	
    @NotEmpty(message = "내용은 필수입니다.")
    private String content;
}