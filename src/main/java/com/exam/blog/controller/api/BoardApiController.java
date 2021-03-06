package com.exam.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.blog.config.auth.PrincipalDetail;
import com.exam.blog.dto.ResponseDto;
import com.exam.blog.model.Board;
import com.exam.blog.model.Reply;
import com.exam.blog.service.BoardService;

@RestController
public class BoardApiController {

	
	@Autowired
	private BoardService boardService;
	
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("BoardApiController");
		
		boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 성공이면 200, 1
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	//데이터를 받을 때 컨트롤러에서 별도의 dto를 만들어서 받는 게 좋다
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody Reply reply,
															@AuthenticationPrincipal PrincipalDetail principal, 
															@PathVariable int boardId) {
		boardService.댓글쓰기(reply, principal.getUser(), boardId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replySave(@PathVariable int replyId) {
		boardService.댓글삭제(replyId);
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
