package com.mysite.minipage.guestbook;

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

@RequestMapping("/guestbook")	//mapping주소 중 공통 부분이니까 클래스 자체 mapping주소로 만들어둠
@RequiredArgsConstructor	//생성자 주입을 위한.
@Controller
public class GuestbookController {
	
	//Service�� Controller ����(Controller - Service - Repository)(Service - Repository부분은 Service.java에 있겠쥬?)
	private final GuestbookService guestbookService;
	private final GuestbookRepository guestbookRepository;
	
	//전체 글 조회하기
//	@RequestMapping("/list")
//	public String list(Model model) {
//		List<Guestbook> guestbookList = this.guestbookRepository.findAll();
//		model.addAttribute("guestbookList",guestbookList);
//		return "guestbook_list";
//	}
	
	//페이징 처리 전체 글 조회하기
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		 Page<Guestbook> paging = this.guestbookService.getList(page);
		 model.addAttribute("paging", paging);
//	     return "guestbook_list";
//	     return "guestbook_list2";
	     return "guestbook_list3";
	}
	
	//방명록 등록
	@GetMapping("/create")
	public String createGuestbook(GuestbookForm guestbookForm) {
		return "guestbook_form";
	}
	
	@PostMapping("/create")
	public String createGuestbook(@Valid GuestbookForm guestbookForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/guestbook/list";
		}
		
		this.guestbookService.create(guestbookForm.getGuestContent(),guestbookForm.getGuestName());
		
		return "redirect:/guestbook/list";
	}
	
	//삭제하기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{guestId}")
	public String deleteGuestbook(Principal principal,@PathVariable("guestId") Integer guestId) {
		Guestbook guestbook = this.guestbookService.getGuestbook(guestId);
//		if(!guestbook.getGuestName().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//		}
		this.guestbookService.delete(guestbook);
		
		return "redirect:/guestbook/list";
	}
	
}