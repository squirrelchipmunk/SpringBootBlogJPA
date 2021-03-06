package com.exam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.blog.model.RoleType;
import com.exam.blog.model.User;
import com.exam.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. >> IoC
@Service 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	// 전체가 트랜잭션으로 묶여 원자성 유지됨. 프록시 객체가 생성되어 자동으로 commit 또는 rollback. isolation 옵션으로 격리 수준 설정 가능.
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional
	public void 회원수정(User user) { // id , password, email
		// 수정 : JPA 영속성 컨텍스트에 User 객체를 영속화시키고 영속화된 객체를 수정
		
		//  1. 영속화시키기 위해 먼저 select
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패!!");
		});
		
		//유효성 체크
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) { // oauth가 kakao인 사람은 수정 불가.
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
		// 회원수정 함수 종료 >> 서비스 종료 >> 트랜잭션 종료 >> 자동 commit : 영속화된 객체의 변화가 감지되면 (더티체킹) DB에 UPDATE문 날림
		
	}

	@Transactional
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{ // 없으면 
			return new User(); // 리턴
		});
		return user;
	}

	/*
	@Transactional(readOnly = true) // select 할 때 트랜잭션 시작, 서비스 종료시 트랜잭션 종료 ( 정합성 유지 )
	public User 로그인(User user) {
		return userRepository.login(user.getUsername(), user.getPassword());
	}
	*/
	
}


// 오라클 DB 격리 수준 : Read Commit : commit된 부분을 read  >> Phantom Read(데이터가 보였다 안 보였다)/부정합 >> 
// MySql DB 격리 수준 : Repeatable Read : 부정합 발생하지 않음 >> undo에 변경 로그가 남고 자기 트랜잭션 번호보다 낮은 undo 로그를 select함
//								     └ 트랜잭션이 시작되기 전 커밋된 내용에 대해 select 


/* 스프링 트랜잭션 */
// 스프링 시작
// 톰캣 시작

// request
// web.xml
// context.mxl

//(1) 영속성 컨텍스트 시작
// Controller : 요청 분기
//(2) DB 연결 세션 생성 >> DB 조작 가능
//(3) 트랜잭션 시작 

// Service : 요청에 맞는 서비스 호출 : 검색한 객체를 객체화 시켜 영속성 컨텍스트에 저장

//(4)db 연결 세션 종료
//(5)트랜잭션 종료 commit

//controller : data(rest) or html

//(6)영속성 컨텍스트 종료 : Lazy Load >>프록시 객체를 통해 실제 객체를 얻을 수 있음.
// └ yml >> spring : jpa : open-in-view : true 설정으로 영속성을 컨트롤러까지 가져감.


// response