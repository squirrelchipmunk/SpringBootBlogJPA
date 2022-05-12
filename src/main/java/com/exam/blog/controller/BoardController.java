package com.exam.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.exam.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"/", ""})
	public String index(@AuthenticationPrincipal PrincipalDetail Principal) {
		System.out.println("로그인 사용자 아아디 :  "+ Principal.getUsername());
		return "index";
	}
}
