package com.exam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.blog.model.Board;
import com.exam.blog.model.User;
import com.exam.blog.repository.BoardRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. >> IoC
@Service 
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// 전체가 트랜잭션으로 묶여 원자성 유지됨. 프록시 객체가 생성되어 자동으로 commit 또는 rollback. isolation 옵션으로 격리 수준 설정 가능.
	@Transactional
	public void 글쓰기(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow( ()-> {
					return new IllegalArgumentException("글 상세보기 실패 :  아이디를 찾을 수 없습니다.");
				});
	}

	@Transactional
	public void 삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id) // 영속화 시키기
				.orElseThrow( ()-> {
					return new IllegalArgumentException("글 찾기 실패");
				});
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료 시 (service 종료) 트랜잭션이 종료 > 더티체킹 - 자동 업데이트(db flush)
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