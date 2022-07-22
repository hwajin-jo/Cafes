package com.example.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cafesList")
@Getter
@Setter
public class Cafes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 50)
	private String subject;
	
	@Column(length = 100)
	private String subtitle;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDate createDate;
	
	private LocalDate modifyDate;
	
	private String fileName;
	private String filePath;
	
	private String address;
	
}
