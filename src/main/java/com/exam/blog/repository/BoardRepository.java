package com.exam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exam.blog.model.Board;

// DAO
// 자동으로 bean 등록
// @Repository 생략
public interface BoardRepository extends JpaRepository<Board, Integer>{ 
	
}





