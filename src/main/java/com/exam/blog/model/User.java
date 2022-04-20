package com.exam.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // 프로젝트에서 연결된 DB의 넘버링 전략 >> 오라클 : 시퀀스, mysql : auto increment
	private int id; // 시퀀스
	
	@Column(nullable=false, length=40)
	private String userName; // id
	
	@Column(nullable=false, length=200)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	@ColumnDefault("'user'") // 큰따옴표 사이에 작은따옴표 : 문자 표시
	private String role; // enum을 쓰는 게 좋음 >> 도메인을 설정하여 잘못된 값 입력 방지
	
	@CreationTimestamp // 현재 시간 자동 입력
	private Timestamp joinDate;
	
	
}
