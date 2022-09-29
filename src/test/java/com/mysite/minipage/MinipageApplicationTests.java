package com.mysite.minipage;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.minipage.guestbook.Guestbook;
import com.mysite.minipage.guestbook.GuestbookRepository;

@SpringBootTest
class MinipageApplicationTests {

	@Test
	void contextLoads() {
	}

	
	@Autowired	//GuestbookRepository를 여기 클래스에서 사용할 거야.
	GuestbookRepository guestbookRepository;

	@Test
	void testJpa() {
		
		Guestbook g1 = new Guestbook();
		g1.setGuestName("멋쟁이토마토");
		g1.setGuestContent("나는야 주스 될거야~ 나는야 케찹 될거야~");
		g1.setGuestDate(LocalDateTime.now());
		this.guestbookRepository.save(g1);
		
		Guestbook g2 = new Guestbook();
		g2.setGuestName("깡깡이");
		g2.setGuestContent("반가워~ 나는 깡깡이야 :)");
		g2.setGuestDate(LocalDateTime.now());
		this.guestbookRepository.save(g2);
		
		Guestbook g3 = new Guestbook();
		g3.setGuestName("깡깡이");
		g3.setGuestContent("방명록은 원래 아무말 하는 곳이랬어");
		g3.setGuestDate(LocalDateTime.now());
		this.guestbookRepository.save(g3);
		
		Guestbook g4 = new Guestbook();
		g4.setGuestName("우투더영투더우");
		g4.setGuestContent("동 투더 그 투더 라뮈 하-!");
		g4.setGuestDate(LocalDateTime.now());
		this.guestbookRepository.save(g4);
		
	}
}
