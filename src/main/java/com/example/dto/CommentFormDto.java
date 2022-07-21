package com.example.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFormDto {
	
    @NotEmpty(message = "������ �ʼ��Դϴ�.")
    private String content;
}