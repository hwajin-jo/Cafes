package com.example.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityFormDto {
	
<<<<<<< HEAD
	@NotEmpty(message="ì œëª©ì„ ìž…ë ¥í•´ ì£¼ì„¸ìš”.")
    private String content;
	
    @NotEmpty(message="ë‚´ìš©ì„ ìž…ë ¥í•´ ì£¼ì„¸ìš”.")
=======
	@NotEmpty(message="³»¿ëÀº ÇÊ¼öÀÔ´Ï´Ù.")
    private String content;
	
    @NotEmpty(message="Á¦¸ñÀº ÇÊ¼öÀÔ´Ï´Ù.")
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
    @Size(max=200)
    private String subject;

    
}
