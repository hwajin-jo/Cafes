package com.example.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberFormDto {

	@NotBlank(message = "�̸��� �ʼ� �Է� ���Դϴ�.")
	private String name;
	
	@NotEmpty(message = "�̸����� �ʼ� �Է� ���Դϴ�.")
	@Email(message = "�̸��� �������� �Է����ּ���.")
	private String email;
	
	@NotEmpty(message ="��й�ȣ�� �ʼ� �Է� ���Դϴ�.")
	@Length(min=8, max = 16, message = "��й�ȣ�� 8�� �̻�, 16�� ���Ϸ� �Է����ּ���")
	private String password;
	
	@NotEmpty(message = "��ȭ��ȣ�� �ʼ� �Է� ���Դϴ�.")
	private String phone;

	
}
