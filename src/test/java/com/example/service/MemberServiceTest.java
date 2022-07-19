package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.MemberFormDto;
import com.example.entity.Member;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member createMember() {
		MemberFormDto memberFormDto = new MemberFormDto();
		memberFormDto.setEmail("test@email.com");
		memberFormDto.setName("ȫ�浿");
		memberFormDto.setPhone("010-1212-3434");
		memberFormDto.setPassword("1234");
		return Member.createMember(memberFormDto, passwordEncoder);
	}
	
	@Test
	@DisplayName("ȸ������ �׽�Ʈ")
	public void saveMemberTest() {
		Member member = createMember();
		Member savedMember = memberService.saveMember(member);
		
		assertEquals(member.getEmail(), savedMember.getEmail());
		assertEquals(member.getName(), savedMember.getName());
		assertEquals(member.getAddress(), savedMember.getAddress());
		assertEquals(member.getPassword(), savedMember.getPassword());
		assertEquals(member.getRole(), savedMember.getRole());
		
	}
	
	@Test
	@DisplayName("�ߺ� ȸ�� ���� �׽�Ʈ")
	public void saveDuplicateMemberTest() {
		Member member1 = createMember();
		Member member2 = createMember();
		memberService.saveMember(member1);
		
		Throwable e = assertThrows(IllegalStateException.class, () -> {
			memberService.saveMember(member2);});
		
		assertEquals("�̹� ���Ե� ȸ���Դϴ�.", e.getMessage());
	}

}
