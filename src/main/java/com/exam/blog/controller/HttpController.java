package com.exam.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.blog.model.Member;

@RestController // 응답: Data
public class HttpController {

	// 브라우저 요청은 get만 가능
	@GetMapping("/http/get")
	public String getTest(@ModelAttribute Member m) { // MessageConverter
		return "get 요청 "+m.getId()+", "+m.getUserName()+", "+m.getPassword()+", "+m.getEmail();
	}

	
	// 나머지는 postman 프로그램에서 확인
	@PostMapping("/http/post")		// raw > text/plain  |  application/json
	public String postTest(@RequestBody Member m) {
		return "post 요청"+m.getId()+", "+m.getUserName()+", "+m.getPassword()+", "+m.getEmail();
	}

	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}

	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
