package com.example.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QqFormDto {
	
    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=100)
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
	
}
