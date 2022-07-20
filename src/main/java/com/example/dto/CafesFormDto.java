package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafesFormDto {

	@NotBlank(message = "제목을 입력하세요.")
	@Size(max = 50)
	private String subject;
	
	@NotBlank(message = "내용을 입력하세요.")
	private String content;
	
}
