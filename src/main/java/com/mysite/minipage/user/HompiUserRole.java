package com.mysite.minipage.user;

import lombok.Getter;

@Getter
public enum HompiUserRole {
	ADMIN("ROLE_ADMIN"),
	HOMPIUSER("ROLE_HOMPIUSER");
	
	private String value;

	HompiUserRole(String value) {	
		this.value = value;
	}
	
	

}