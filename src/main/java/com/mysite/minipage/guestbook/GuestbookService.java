package com.mysite.minipage.guestbook;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysite.minipage.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GuestbookService {
	//Repository와 Controller 사이에 들어오는 Service
	
	//Repository(DAO)와 연결시키기
	private final GuestbookRepository guestbookRepository;
	
//	//전체조회
//	public List<Guestbook> getList(){
//		return this.guestbookRepository.findAll();
//	}
	
	//페이징 전체조회
	public Page<Guestbook> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("guestDate"));
		Pageable pageable = PageRequest.of(page, 2,Sort.by(sorts));
		return this.guestbookRepository.findAll(pageable);
	}

	
	//방명록 등록 저장하기
	public void create(String guestContent, String guestName) {
		Guestbook guestbook = new Guestbook();
		
		guestbook.setGuestDate(LocalDateTime.now());
		guestbook.setGuestContent(guestContent);
		guestbook.setGuestName(guestName);
		
		this.guestbookRepository.save(guestbook);	//저장소에 save
	}
	
	public Guestbook getGuestbook(Integer guestId) {
		Optional<Guestbook> guestbook = this.guestbookRepository.findById(guestId);//db안에서 선택한 id값
		if(guestbook.isPresent()) {
			return guestbook.get();//있으면 있는 id값 return
		}
		else {
			throw new DataNotFoundException("guestbook not found");//데이터가 없을 경우 예외
		}
		
	}
	
	//삭제
	public void delete(Guestbook guestbook) {
		this.guestbookRepository.delete(guestbook);
	}
	
	
}