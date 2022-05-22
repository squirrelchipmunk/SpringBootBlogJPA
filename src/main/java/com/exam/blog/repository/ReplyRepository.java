package com.exam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.exam.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	@Modifying // object를 리턴할 수 없음 >> update된 행(int) 반환
	@Query(value="insert into reply( id ,userId, content,  boardId, createDate) values ( reply_seq.nextval, ?1, ?2, ?3, sysdate)", nativeQuery = true) // 네이티브 쿼리 > 내가 작성한 쿼리가 실행
	int mSave(int userId, String content, int boardId); // 인터페이스는 public 생략 가능, 
}
