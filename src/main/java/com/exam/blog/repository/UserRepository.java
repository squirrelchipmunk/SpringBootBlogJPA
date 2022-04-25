package com.exam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.blog.model.User;

// DAO
// 자동으로 bean 등록
// @Repository 생략
public interface UserRepository extends JpaRepository<User, Integer>{ // user를 관리하는 repository이며 pk가 int
		
}
