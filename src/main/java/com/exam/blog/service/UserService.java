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
	
	// 전체가 트랜잭션으로 묶여 원자성 유지됨. 프록시 객체가 생성되어 자동으로 commit 또는 rollback. isolation 옵션으로 격리 수준 설정 가능.
	@Transactional 
	public void 회원가입(User user) {
		userRepository.save(user);
	}
}


// 오라클 DB 격리 수준 : Read Commit : commit된 부분을 read  >> Phantom Read(데이터가 보였다 안 보였다)/부정합 >> 
// MySql DB 격리 수준 : Repeatable Read : 부정합 발생하지 않음 >> undo에 변경 로그가 남고 자기 트랜잭션 번호보다 낮은 undo 로그를 select함
//								     └ 트랜잭션이 시작되기 전 커밋된 내용에 대해 select 