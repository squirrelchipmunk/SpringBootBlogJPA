package com.exam.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
		name= "BOARD_SEQ"						// 생성기 이름
		,sequenceName = "BOARD_SEQ"	    // 시퀀스 이름
		,initialValue = 1							// 초기값
		,allocationSize = 1						// 증가값
	)
public class Board {

	/* 테이블 생성 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BOARD_SEQ")
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인 됨

	//@ColumnDefault("0")
	private int count;

	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "userId") // FK 필드 : userId
	private User user; // db는 오브젝트를 저장할 수 없고 FK 사용.
						// jpa orm에서는 가능.
	
														// mappedBy = 필드명     (Reply 클래스의 필드명)
														// mappedBy 연관관계의 주인이 아니다 >> DB에 컬럼을 만들지 않는다
														// fetch = FetchType.LAZY  : 필요하면 가져온다
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"board"}) // Reply 안에서 board의 getter 호출을 무시 > 무한참조 방지!   !!getter 주의!!
	private List<Reply> replys;

	@CreationTimestamp
	private Timestamp createDate;

}
