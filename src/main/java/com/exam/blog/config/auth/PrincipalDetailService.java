package com.exam.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.blog.model.User;
import com.exam.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	// 로그인 할 때 스프링이 username, password 변수 2개를 가로챔
	// password 부분 처리는 알아서.
	// username이 DB에 있는지만 확인하면 됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 로그인 진행될 때 자동 실행
		User principal = userRepository.findByUsername(username)
				.orElseThrow( ()-> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :  "+username);
				});
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저 정보가 저장 > 이 과정이 없으면 기본 user와 패스워드값으로만 사용가능
	}
}
