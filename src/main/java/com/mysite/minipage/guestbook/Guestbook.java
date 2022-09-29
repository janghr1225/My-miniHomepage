package com.mysite.minipage.guestbook;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter	//setter,getter 코드 없어도 만들어지도록 어노테이션 사용
@Entity
public class Guestbook {
	
	@Id	//고유한 id라는 어노테이션 - id필드는 primary키가 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//자동으로 하나씩 증가되는 값이 됨
	private Integer guestId;
	
	@Column(length = 100)	//자료크기:100 
	private String guestName;
	
	@Column(columnDefinition = "TEXT")	//자료크기를 제한할 수 없는 경우에 columnDefinition
	private String guestContent;
	
	@Column(length = 100)	//자료크기:100
	private String imageFile;
	
	//어노테이션 없어도 자동으로 컬럼으로 만들어짐
	private LocalDateTime guestDate;
	
}
