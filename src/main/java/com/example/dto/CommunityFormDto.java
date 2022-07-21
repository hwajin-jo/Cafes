package com.example.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityFormDto {
	
	@NotEmpty(message="제목을 입력해 주세요.")
    private String content;
	
    @NotEmpty(message="내용을 입력해 주세요.")
    @Size(max=200)
    private String subject;

    
}
