package com.example.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityFormDto {
	
	@NotEmpty(message="������ �ʼ��Դϴ�.")
    private String content;
	
    @NotEmpty(message="������ �ʼ��Դϴ�.")
    @Size(max=200)
    private String subject;

    
}
