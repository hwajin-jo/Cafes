package com.example.entity;

import java.time.LocalDateTime;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.Set;
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
=======
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250


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
	
<<<<<<< HEAD

=======
	@ManyToMany
    Set<Member> voter;
	
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
}
