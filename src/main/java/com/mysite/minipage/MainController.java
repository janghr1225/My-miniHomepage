package com.mysite.minipage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.minipage.user.HompiUserCreateForm;

@Controller
public class MainController {

	@RequestMapping("/")
	public String root() {
		
		 return "redirect:/hompiUser/index";
		
	}
	
	
	@GetMapping("/signup")
	public String signup(HompiUserCreateForm hompiUserCreateForm) {
		return "signup_form";
	}
}