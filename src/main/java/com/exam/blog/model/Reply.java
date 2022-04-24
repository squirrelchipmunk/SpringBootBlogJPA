package com.exam.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Reply {

	/* 테이블 생성 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	
	
	/* 생성자 게터 세터 */
	
	public Reply() {
		super();
	}

	public Reply(int id, String content, Board board, User user, Timestamp createDate) {
		super();
		this.id = id;
		this.content = content;
		this.board = board;
		this.user = user;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
				+ createDate + "]";
	}

}
