package com.exam.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.exam.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({"/", ""})
	public String index(/*@AuthenticationPrincipal PrincipalDetail Principal */
								Model model) { // 세션
		//System.out.println("로그인 사용자 아아디 :  "+ Principal.getUsername());
		model.addAttribute("boards", boardService.글목록());
		return "index";
	}
	
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
