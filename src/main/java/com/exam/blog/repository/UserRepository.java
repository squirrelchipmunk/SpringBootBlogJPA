package com.exam.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.blog.model.User;

// DAO
// 자동으로 bean 등록
// @Repository 생략
public interface UserRepository extends JpaRepository<User, Integer>{ // user를 관리하는 repository이며 pk가 int
	
	// Select * from user where username = 1?;
	Optional<User> findByUsername(String username);
}





//JPA Naming 전략

// select * from user where username = ?1 and password = ?2
//User findByUsernameAndPassword(String username, String password);
	
	
//	@Query(value = "select * from users where username = ?1 and password = ?2", nativeQuery = true)
//	User login(String username, String Password);