package com.mysite.minipage.diary;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RequestMapping("/diary")
@RequiredArgsConstructor
@Controller
public class DiaryController {
	
	private final DiaryService diaryService;
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Diary> paging = this.diaryService.getList(page);
		
		model.addAttribute("paging", paging);
		
		return "diary";
	}
	
	/* 글 등록 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/list/create")
	public String diaryCreate(DiaryForm diaryForm) {
		return "diary_form";

	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/list/create")
	public String diaryCreate(@Valid DiaryForm diaryForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "diary_form";

		}
		this.diaryService.create(diaryForm.getContent());
		
		return "redirect:/diary/list";
	}
	
	/* 글 수정 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String diaryModify(DiaryForm diaryForm, @PathVariable("id") Integer id, Principal principal) {
		Diary diary = this.diaryService.getDiary(id);
//		if(!diary.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//		}
		
		diaryForm.setContent(diary.getContent());
		
		return "diary_form";

	 }

	@PostMapping("/modify/{id}")
	public String diaryModify(@Valid DiaryForm diaryForm, BindingResult bindingResult,
			Principal principal, @PathVariable("id") Integer id)
	{
		if(bindingResult.hasErrors()) {

			return "diary_form";
		}
		
		Diary diary = this.diaryService.getDiary(id);
//		if(!diary.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//		}
		
		this.diaryService.modify(diary, diaryForm.getContent());
		
		return String.format("redirect:/diary/list", id);
	}
	
	/* 글 삭제 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String diaryDelete(Principal principal, @PathVariable("id") Integer id) {
		Diary diary = this.diaryService.getDiary(id);
//		if(!diary.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//		}
		
		this.diaryService.delete(diary);
		
		return "redirect:/diary/list";
	}

}