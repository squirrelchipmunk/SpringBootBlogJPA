package com.exam.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.blog.model.User;
import com.exam.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. >> IoC
@Service 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional // 전체가 트랜잭션으로 묶여 원자성 유지됨.
	public void 회원가입(User user) {
		userRepository.save(user);
	}
}
