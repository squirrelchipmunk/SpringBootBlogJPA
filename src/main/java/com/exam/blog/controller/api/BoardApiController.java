package com.exam.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.blog.dto.ResponseDto;
import com.exam.blog.model.Board;

@RestController
public class BoardApiController {

	
	/*
	@Autowired
	private HttpSeession session;
	*/
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board) {
		System.out.println("BoardApiController");
		
		
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
