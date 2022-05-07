package com.exam.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController");
		//System.out.println(user);
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user); // 1 성공 -1 실패
		return new ResponseDto<Integer>(HttpStatus.OK,result);
	}
}
