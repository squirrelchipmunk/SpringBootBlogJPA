package com.exam.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
