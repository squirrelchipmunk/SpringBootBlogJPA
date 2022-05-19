package com.exam.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.blog.dto.ResponseDto;
import com.exam.blog.model.RoleType;
import com.exam.blog.model.User;
import com.exam.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	/*
	@Autowired
	private HttpSeession session;
	*/
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController");
		
		user.setRole(RoleType.USER);
		userService.회원가입(user); // 1 성공 -1 실패
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.회원수정(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	/*
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		System.out.println("UserApiController : login()");
		User principal = userService.로그인(user); // principal : 접근 주체
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	*/
	
	
}
