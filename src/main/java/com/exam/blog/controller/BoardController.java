package com.exam.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"/", ""})
	public String index(/*@AuthenticationPrincipal PrincipalDetail Principal */) { // 세션
		//System.out.println("로그인 사용자 아아디 :  "+ Principal.getUsername());
		return "index";
	}
	
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
