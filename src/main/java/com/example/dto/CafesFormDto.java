package com.example.dto;

import java.time.LocalDate;

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
	
	@NotBlank(message = "부제목을 입력하세요.")
	@Size(max = 100)
	private String subtitle;
	
	@NotBlank(message = "내용을 입력하세요.")
	private String content;
	
	private String cafeImage;
	
	private String address;
	
}
