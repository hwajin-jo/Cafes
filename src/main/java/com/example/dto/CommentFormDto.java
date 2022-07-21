package com.example.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFormDto {
	
<<<<<<< HEAD
    @NotEmpty(message = "ëŒ“ê¸€ ë‚´ìš©ì„ ìž…ë ¥í•´ ì£¼ì„¸ìš”.")
=======
    @NotEmpty(message = "³»¿ëÀº ÇÊ¼öÀÔ´Ï´Ù.")
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
    private String content;
}