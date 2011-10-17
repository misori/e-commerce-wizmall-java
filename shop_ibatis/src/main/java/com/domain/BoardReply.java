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

/**
 *
 * @author Pondol
 *
 */
public class BoardReply extends BaseObject {

	private static final long serialVersionUID = 1L;
	public BoardReply() {

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
	 * FLAG : 일반리플(1), 엮인글보기(2), 추천비추천용
	 */
	@Column(name = "FLAG", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer flag;

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getFlag() {
		return this.flag;
	}

	/*
	 * MID : Board Tid
	 */
	@Column(name = "MID", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer mid;

	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getMid() {
		return this.mid;
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
	 * USER_EMAIL :
	 */
	@Column(name = "USER_EMAIL", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_email;

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_email() {
		return this.user_email;
	}

	/*
	 * USER_URL :
	 */
	@Column(name = "USER_URL", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_url;

	public void setUser_url(String user_url) {
		this.user_url = user_url;
	}
	public String getUser_url() {
		return this.subject;
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
	 * COUNT :
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
	 * RECCOUNT :
	 */
	@Column(name = "RECCOUNT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer reccount;

	public void setReccount(Integer reccount) {
		this.reccount = reccount;
	}
	public Integer getReccount() {
		return this.reccount;
	}


	/*
	 * NONRECCOUNT :
	 */
	@Column(name = "NONRECCOUNT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer nonreccount;

	public void setNonreccount(Integer nonreccount) {
		this.nonreccount = nonreccount;
	}
	public Integer getNonreccount() {
		return this.nonreccount;
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