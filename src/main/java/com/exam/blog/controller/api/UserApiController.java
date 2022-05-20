package com.exam.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController");
		
		userService.회원가입(user); // 1 성공 -1 실패
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.회원수정(user);
		// 트랜잭션 종료 > db에 값은 변경됐지만 세션값은 변경되지 않아 updateForm에 바로 반영되지 않음 > 세션값 변경 필요
		
		
		/* 예전엔 됐는데 막혀버린 듯하다..
		Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(auth);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		*/
		
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword() )); // 강제 로그인 처리
		SecurityContextHolder.getContext().setAuthentication(auth);
		// 세션이 자동으로 등록됨
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
/*
 1. 사용자 로그인 요청 (username, password)
 2. 인증 필터를 거쳐 UsernamePasswordAuthenticationToken 생성하여 AuthenticationManeger에 전달 ( Authentication 객체 생성하기 위해 )
 3. AuthenticationManeger가 username을 UserDetailService(PrincipalDetailService)에 전달
 4. UserDetailService가 DB에 username을 찾고 있으면 AuthenticationManeger가 password를 암호화 (BCrypt)해서 데이터베이스와 일치하는지 확인
 5. Authentication 객체 생성해서 세션의 시큐리티 컨텍스트에 저장
 */
	
	
	
	
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
