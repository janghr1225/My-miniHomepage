package com.mysite.minipage.diary;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiaryForm {
	
	@NotEmpty(message = "내용은 필수 항목입니다.")
	@Size(max = 2000)
	private String content;
}