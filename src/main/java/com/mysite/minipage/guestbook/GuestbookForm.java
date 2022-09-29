package com.mysite.minipage.guestbook;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GuestbookForm {
	
	@NotEmpty(message="필수 입력입니다.")
	private String guestName;
	
	@NotEmpty(message="필수 입력입니다.")
	private String guestContent;
	
	
}