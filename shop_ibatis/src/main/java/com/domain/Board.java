package com.domain;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.util.BaseObject;

public class Board extends BaseObject {

	private static final long serialVersionUID = 1L;
	public Board() {

	}


	/*
	 * TID : AutoIncreament Value
	 */
	@Column(name = "TID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)//자동 증가일경우
	@Id// @Id와 @GeneratedValue는 해당 필드가 프라이머리 키로 사용되며 자동으로 생성되는 값을 가진다는 것
	Integer tid;

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getTid() {
		return this.tid;
	}

	/*
	 * CATEGORY : 카테고리
	 */
	@Column(name = "CATEGORY", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Integer category;

	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getCategory() {
		return this.category;
	}


	/*
	 * USER_ID :  글쓴이 아이디
	 */
	@Column(name = "USER_ID", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_id;

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id() {
		return this.user_id;
	}
	/*
	 * USER_NAME : 글쓴이 이름
	 */
	@Column(name = "USER_NAME", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_name;

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_name() {
		return this.user_name;
	}

	/*
	 * USER_PASSWD : 글쓴이 패스워드
	 */
	@Column(name = "USER_PASSWD", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_passwd;

	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}
	public String getUser_passwd() {
		return this.user_passwd;
	}

	/*
	 * SUBJECT : 글 제목
	 */
	@Column(name = "SUBJECT", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String subject;

	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return this.subject;
	}

	/*
	 * CONTENTS : 글내용
	 */
	@Column(name = "CONTENTS", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String contents;

	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getContents() {
		return this.contents;
	}

	/*
	 * THREAD : 엮인글 쓰레드
	 */
	@Column(name = "THREAD", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String thread;

	public void setThread(String thread) {
		this.thread = thread;
	}
	public String getThread() {
		return this.thread;
	}

	/*
	 * FID : 실제 글 순서
	 */
	@Column(name = "FID", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer fid;

	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Integer getFid() {
		return this.fid;
	}

	/*
	 * ATTACHED : COUNT
	 */
	@Column(name = "COUNT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer count;

	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCount() {
		return this.count;
	}

	/*
	 * ATTACHED : 첨부화일
	 */
	@Column(name = "ATTACHED", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String attached;

	public void setAttached(String attached) {
		this.attached = attached;
	}
	public String getAttached() {
		return this.attached;
	}

	/*
	 * USER_IP : 등록자 IP
	 */
	@Column(name = "USER_IP", length = 15, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_ip;

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getUser_ip() {
		return this.user_ip;
	}

	/*
	 * OP_VAL : 등록옵션
	 */
	@Column(name = "OP_VAL", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String op_val;

	public void setOp_val(String op_val) {
		this.op_val = op_val;
	}
	public String getOp_val() {
		return this.op_val;
	}

	/**
	 * W_DATE : 글 등록일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "W_DATE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Date w_date;

	public void setW_date(java.util.Date date) {
		this.w_date = date;
	}
	public Date getW_date() {
		return this.w_date;
	}


	/**
	 * bid, gid setting
	 */
	String bid;

	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBid() {
		return this.bid;
	}

	String gid;

	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGid() {
		return this.gid;
	}
}
