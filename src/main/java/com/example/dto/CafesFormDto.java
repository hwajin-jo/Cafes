package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafesFormDto {

	@NotBlank(message = "������ �Է��ϼ���.")
	@Size(max = 50)
	private String subject;
	
	@NotBlank(message = "������ �Է��ϼ���.")
	private String content;
	
}
