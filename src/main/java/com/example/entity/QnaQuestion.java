package com.example.entity;

import java.time.LocalDateTime;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Set;
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.persistence.ManyToMany;
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="q_question")
@Getter
@Setter
public class QnaQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 100)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<QnaAnswer> answerList;
	
	@ManyToOne
	private Member author;
	
	private LocalDateTime modifyDate;

<<<<<<< HEAD
=======
	@ManyToMany
    Set<Member> voter;
>>>>>>> 9b9716e8f763cfdc5d52edab049355f75af99250

}
