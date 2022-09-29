package com.mysite.minipage.user;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HompiUserCreateForm {

	@Size(min=3, max=15,message="아이디는 3 ~15로 지어주세요.")
	@NotEmpty(message="사용자 아이디는 필수 항목입니다.")
	private String username;
	
	@NotEmpty(message="비밀번호는 필수 항목입니다.")
	private String password1;	//비밀번호
	
	@NotEmpty(message="비밀번호 확인은 필수 항목입니다.")
	private String password2;	//비밀번호확인

	@NotEmpty(message="이메일은 필수 항목입니다.")
	@Email
	private String email;
	
	@Column(columnDefinition = "TEXT")		//게시판 홈페이지에 등록될 자기소개
	private String profile;
}