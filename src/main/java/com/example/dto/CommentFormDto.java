package com.example.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFormDto {
	
<<<<<<< HEAD
    @NotEmpty(message = "댓글 내용을 입력해 주세요.")
=======
    @NotEmpty(message = "������ �ʼ��Դϴ�.")
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
    private String content;
}