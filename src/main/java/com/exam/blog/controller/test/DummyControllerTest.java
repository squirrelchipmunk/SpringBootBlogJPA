package com.exam.blog.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.blog.model.RoleType;
import com.exam.blog.model.User;
import com.exam.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired // DI 의존 주입
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	//public String join(String userName, String password, String email) { // key = value 형태   (form 태그)
	public String join(User user) {
		System.out.println("id: "+user.getId());
		System.out.println("userName: "+user.getUserName());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		System.out.println("role: "+user.getRole());
		System.out.println("joinDate: "+user.getJoinDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
