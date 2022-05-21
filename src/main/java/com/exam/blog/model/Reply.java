package com.exam.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name= "REPLY_SEQ"						// 생성기 이름
		,sequenceName = "REPLY_SEQ"	    // 시퀀스 이름
		,initialValue = 1							// 초기값
		,allocationSize = 1						// 증가값
	)
public class Reply {

	/* 테이블 생성 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "REPLY_SEQ")
	private int id;

	@Column(nullable = false, length = 200)
	private String content;

	@ManyToOne // 여러 답글이 하나의 게시글에
	@JoinColumn(name = "boardId")
	private Board board;

	@ManyToOne // 여러 답글이 하나의 유저에
	@JoinColumn(name = "userId")
	private User user;

	@CreationTimestamp
	private Timestamp createDate;


}
