package com.exam.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.exam.blog.config.auth.PrincipalDetail;
import com.exam.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({"/", ""})
	public String index(Model model, @PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) { 
		//System.out.println("로그인 사용자 아아디 :  "+ Principal.getUsername());
		model.addAttribute("boards", boardService.글목록(pageable)); // Page<> 타입
		return "index";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetail principal) {
		model.addAttribute("board", boardService.글상세보기(id));
		System.out.println(boardService.글상세보기(id).getUser().getId());
		System.out.println(principal.getUser().getId());
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
