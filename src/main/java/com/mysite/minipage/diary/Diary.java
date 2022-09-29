package com.mysite.minipage.diary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

import com.mysite.minipage.user.HompiUser;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Diary {
	
	// 지우면 안됨!! Failed to initialize JPA EntityManagerFactory: No identifier specified for entity 오류남
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@CreatedDate
	private LocalDateTime writeDate;
	
	@Column(length = 2000)
	private String content;
	
	@OneToOne
	private HompiUser author;
}