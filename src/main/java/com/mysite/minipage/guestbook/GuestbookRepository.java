package com.mysite.minipage.guestbook;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook, Integer> {
	//레퍼지토리 - jpaRepository를 상속 받아서 repository로 그대로 사용 가능

	 Page<Guestbook> findAll(Pageable pageable);	//페이징
}