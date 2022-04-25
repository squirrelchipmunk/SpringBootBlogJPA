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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Board {

	/* 테이블 생성 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인 됨

	@ColumnDefault("0")
	private int count;

	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "userId") // FK 필드 : userId
	private User user; // db는 오브젝트를 저장할 수 없고 FK 사용.
						// jpa orm에서는 가능.
	
														// mappedBy = 필드명     (Reply 클래스의 필드명)
														// mappedBy 연관관계의 주인이 아니다 >> DB에 컬럼을 만들지 않는다
														// fetch = FetchType.LAZY  : 필요하면 가져온다
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) 
	private List<Reply> reply;

	@CreationTimestamp
	private Timestamp createDate;

	
	
	/* 생성자 게터 세터 */
	public Board() {
		super();
	}

	public Board(int id, String title, String content, int count, User user, List<Reply> reply, Timestamp createDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.count = count;
		this.user = user;
		this.reply = reply;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + ", count=" + count + ", user=" + user
				+ ", reply=" + reply + ", createDate=" + createDate + "]";
	}

}
