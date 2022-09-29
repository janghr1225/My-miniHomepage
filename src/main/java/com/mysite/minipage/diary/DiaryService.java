package com.mysite.minipage.diary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.minipage.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {

	private final DiaryRepository diaryRepository;
	
	// 목록 조회
	public Page<Diary> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("writeDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.diaryRepository.findAll(pageable);
	}
	
	public Diary getDiary(Integer id) {
		Optional<Diary> diary = this.diaryRepository.findById(id);
		
		if(diary.isPresent()) {
			return diary.get();
		} else {
			throw new DataNotFoundException("Diary Not Found");
		}
	}
	
	// 글 등록
	public void create(String content) {
		Diary diary = new Diary();
		diary.setContent(content);
		diary.setWriteDate(LocalDateTime.now());
		
		this.diaryRepository.save(diary);
	}
	
	// 글 수정
	public void modify(Diary diary, String content) {
		diary.setContent(content);
		diary.setWriteDate(LocalDateTime.now());
		
		this.diaryRepository.save(diary);
	}
	
	// 글 삭제
	public void delete(Diary diary) {
		this.diaryRepository.delete(diary);
	}

	
	
}