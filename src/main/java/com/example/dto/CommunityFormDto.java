package com.example.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityFormDto {
	
	@NotEmpty(message="내용은 필수입니다.")
    private String content;
	
    @NotEmpty(message="제목은 필수입니다.")
    @Size(max=200)
    private String subject;

    
}
