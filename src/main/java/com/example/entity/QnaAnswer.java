package com.example.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.ManyToMany;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="q_answer")
@Getter
@Setter
public class QnaAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private QnaQuestion question; 
	
	@ManyToOne
	private Member author;
	
	private LocalDateTime modifyDate;

	
}
