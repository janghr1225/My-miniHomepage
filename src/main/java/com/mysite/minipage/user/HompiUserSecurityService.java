package com.mysite.minipage.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HompiUserSecurityService implements UserDetailsService{

	private final HompiUserRepository hompiUserRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<HompiUser> _hompiUser = this.hompiUserRepository.findByusername(username);
		if(_hompiUser.isEmpty()) {
			throw new UsernameNotFoundException("미니홈피 이용자가 아닙니다");
		}
		HompiUser hompiUser = _hompiUser.get();
		List<GrantedAuthority> authorities = new ArrayList();
		if("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(HompiUserRole.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(HompiUserRole.HOMPIUSER.getValue()));
			
		}
		
		return new User(hompiUser.getUsername(),hompiUser.getPassword(), authorities);
	}

}