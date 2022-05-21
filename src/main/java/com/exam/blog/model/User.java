package com.exam.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@DynamicInsert // null인 필드 제외
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
@SequenceGenerator(
			name= "USER_SEQ"						// 생성기 이름
			,sequenceName = "USER_SEQ"	    // 시퀀스 이름
			,initialValue = 1							// 초기값
			,allocationSize = 1						// 증가값
		)
public class User {

	/* 테이블 생성 */

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ") // 프로젝트에서 연결된 DB의 넘버링 전략 >> 오라클 : 시퀀스, mysql : auto increment
	private int id; // 시퀀스 

	@Column(nullable = false, length = 200, unique=true)
	private String username; // id

	@Column(nullable = false, length = 200)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	// @ColumnDefault("'user'") // 사용하지 않고 세터 처리
	@Enumerated(EnumType.STRING)
	private RoleType role; // enum을 쓰는 게 좋음 >> 도메인을 설정하여 잘못된 값 입력 방지

	private String oauth; // kakao, blog
	
	@CreationTimestamp // 현재 시간 자동 입력
	private Timestamp joinDate;

}
