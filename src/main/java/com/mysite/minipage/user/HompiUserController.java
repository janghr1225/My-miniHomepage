package com.mysite.minipage.user;

import java.lang.reflect.Member;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
//@RequestMapping("/hompiUser")
public class HompiUserController {

	private final HompiUserService hompiUserService;
	
	@GetMapping("/hompiUser/signup")
	public String signup(HompiUserCreateForm hompiuserCreateForm) {
		return "signup_form";
	}
	
	
	@GetMapping("/hompiUser/login")
	public String login() {
		return "login_form";
	}
	
//쿠키생성 도전..
	public String login(@Valid @ModelAttribute HompiUserCreateForm hompiuserCreateForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "hompiUser/login_form";
        }

        HompiUser loginMember =  hompiUserService.login(hompiuserCreateForm.getUsername(), hompiuserCreateForm.getProfile());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "hompiUser/login_form";
        }

        //로그인 성공 처리

        //쿠키에 시간 정보를 주지 않으면 세션 쿠기(브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("username", loginMember.getUsername());
        response.addCookie(idCookie);
        System.out.println(idCookie);
        return "redirect:/";

    }


	@GetMapping("/hompiUser/index")
	public String layout() {
		return "index";
	}
	//원본
	@PostMapping("/hompiUser/signup")
	public String signup(@Valid HompiUserCreateForm hompiuserCreateForm, BindingResult bindingResult,Principal principal) {
	
		if(bindingResult.hasErrors()) {
			
			return "signup_form";
			
		}
		if(!hompiuserCreateForm.getPassword1().equals(hompiuserCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2","passwordIncorrect","2개의 패스워드가 불일치합니다.");
			return "signup_form";
		}
		
		try {
			
			hompiUserService.create(hompiuserCreateForm.getUsername(), hompiuserCreateForm.getEmail(),hompiuserCreateForm.getPassword1(),hompiuserCreateForm.getProfile());
			
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed","이미 등록된 사용자입니다.");
			return "signup_form";
			
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed",e.getMessage());
			return "signup_form";
			
		}
		
		return "redirect:/";
	}
}