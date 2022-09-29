package com.mysite.minipage.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HompiUserRepository extends JpaRepository<HompiUser, Long>{
	Optional<HompiUser> findByusername(String username);
}