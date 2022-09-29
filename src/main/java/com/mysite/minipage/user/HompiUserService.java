package com.mysite.minipage.user;

import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.minipage.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HompiUserService {

	private final HompiUserRepository hompiUserRepository;
	
	//컨트롤러가 이쪽으로 넘김
	public HompiUser create(String username, String email, String password,String profile) {
		HompiUser user = new HompiUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setProfile(profile);
		//암호화하는 클래스
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		this.hompiUserRepository.save(user);
		return user;		
	}
	
	public HompiUser getUser(String username) {
		Optional<HompiUser> hompiUser = this.hompiUserRepository.findByusername(username);
		if(hompiUser.isPresent()) {
			return hompiUser.get();
		}else {
			throw new DataNotFoundException("hompiUser not found!!!!!!!!!");
		}
	}


	public HompiUser login(String username, String profile) {
		Optional<HompiUser> hompiUser = this.hompiUserRepository.findByusername(username);
		if(hompiUser.isPresent()) {
			return hompiUser.get();
		}else {
			throw new DataNotFoundException("hompiUser not found!!!!!!!!!");
		}
		
	}


}