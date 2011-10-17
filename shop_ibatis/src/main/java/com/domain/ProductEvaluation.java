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

public class ProductEvaluation extends BaseObject {
	private static final long serialVersionUID = 1L;
	public ProductEvaluation() {

	}

	/**
	 * TID : AutoIncreament Value
	 */
	@Column(name = "TID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)//자동 증가일경우
	@Id
	Integer tid;

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getTid() {
		return this.tid;
	}


	/**
	 * PID : 상품 TID
	 */
	@Column(name = "PID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int pid;

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPid() {
		return this.pid;
	}


	/**
	 * USER_ID : 작성자 아이디
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


	/**
	 * USER_NAME : 작성자 명
	 */
	@Column(name = "USER_NAME", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_name;
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_name() {
		return this.user_name;
	}


	/**
	 * PASSWD : 작성시 패스워드
	 */
	@Column(name = "PASSWD", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String passwd;
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd() {
		return this.passwd;
	}

	/**
	 * EMAIL : 이메일
	 */
	@Column(name = "EMAIL", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String email;
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}

	/**
	 * GRADE : 상품평가 값
	 */
	@Column(name = "GRADE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int grade;

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGrade() {
		return this.grade;
	}

	/**
	 * SUBJECT : 제목
	 */
	@Column(name = "SUBJECT", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String subject;
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return this.subject;
	}


	/**
	 * CONTENTS : 내용(주로 이부분을 활용)
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

	/**
	 * TXTTYPE : 텍스트 타입(0:txt, 1:html)
	 */
	@Column(name = "TXTTYPE", length = 5, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String txttype;
	public void setTxttype(String txttype) {
		this.txttype = txttype;
	}
	public String getTxttype() {
		return this.subject;
	}


	/**
	 * COUNT : 본횟수(필요없음)
	 */
	@Column(name = "COUNT", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int count;

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return this.count;
	}


	/**
	 * IP : 등록아이피
	 */
	@Column(name = "IP", length = 15, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String ip;
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return this.ip;
	}


	/**
	 * W_DATE : 등록일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "W_DATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Date  w_date;

	public void setW_date(Date date) {
		this.w_date = date;
	}
	public Date getW_date() {
		return this.w_date;
	}
}
