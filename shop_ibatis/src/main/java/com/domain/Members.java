package com.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pondol
 *
 */
public class Members implements Serializable {
	//private static final long serialVersionUID = 1L;

	private static final long serialVersionUID = 1L;

	/**
	 */

	public Members() {
	}


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
	 * USER_ID
	 */
	@Column(name = "USER_ID", length = 15, nullable = false)//insertable=true updatable=false
	@Basic(fetch = FetchType.EAGER)
	String user_id;

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * USER_PWD
	 */
	@Column(name = "USER_PASSWD", length = 150, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_passwd;

	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}

	public String getUser_passwd() {
		return this.user_passwd;
	}


	/**
	 * USER_NAME
	 */
	@Column(name = "USER_NAME", length = 30, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_name;

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_name() {
		return this.user_name;
	}

	/**
	 * USER_GRANTSTA : 회원등록상태 (00 : 보류, 03:정상승인, 04:탈퇴)
	 */
	@Column(name = "USER_GRANTSTA", length = 2, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_grantsta;

	public void setUser_grantsta(String user_grantsta) {
		this.user_grantsta = user_grantsta;
	}

	public String getUser_grantsta() {
		return this.user_grantsta;
	}


	/**
	 * USER_POINT : 회원포인트
	 */
	@Column(name = "USER_POINT", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int user_point;

	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}

	public int getUser_point() {
		return this.user_point;
	}

	/**
	 * USER_GRADE : 회원등급
	 * 10:관리자회원 , 100: 일반회원
	 */
	@Column(name = "USER_GRADE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int user_grade;

	public void setUser_grade(int user_grade) {
		this.user_grade = user_grade;
	}

	public int getUser_grade() {
		return this.user_grade;
	}


	/**
	 * USER_EMAIL : 회원이메일
	 */
	@Column(name = "USER_EMAIL", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_email;

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_email() {
		return this.user_email;
	}


	/**
	 * USER_LOGIN_NUM : 회원 로그인 횟수
	 */
	@Column(name = "USER_LOGIN_NUM", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int user_login_num;

	public void setUser_login_num(int user_login_num) {
		this.user_login_num = user_login_num;
	}

	public int getUser_login_num() {
		return this.user_login_num;
	}


	/**
	 * USER_LOGIN_DATE : 회원 로그인일(최근 로그인)
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_LOGIN_DATE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Date user_login_date;

	public void setUser_login_date(java.util.Date date) {
		this.user_login_date = date;
	}
	public Date getUser_login_date() {
		return this.user_login_date;
	}


	/**
	 * USER_LOGIN_IP : 로그인 아이피
	 */
	@Column(name = "USER_LOGIN_IP", length = 15, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_login_ip;

	public void setUser_login_ip(String user_login_ip) {
		this.user_login_ip = user_login_ip;
	}

	public String getUser_login_ip() {
		return this.user_login_ip;
	}


	/**
	 * USER_REGDATE : 회원가입일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USER_REGDATE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Date user_regdate;

	public void setUser_regdate(java.util.Date date) {
		this.user_regdate = date;
	}
	public Date getUser_regdate() {
		return this.user_regdate;
	}








	// MembersGen 에 있는 내용을 가져옮


	/*
	 * USER_GENDER
	 * M:Man, W:Woman
	 */
	@Column(name = "USER_GENDER", length = 2, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_gender;

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_gender() {
		return this.user_gender;
	}


	/*
	 * USER_JUMIN1 : 회원주민번호 앞 6자리
	 */
	@Column(name = "USER_JUMIN1", nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	Integer user_jumin1;

	public void setUser_jumin1(Integer user_jumin1) {
		this.user_jumin1 = user_jumin1;
	}

	public Integer getUser_jumin1() {
		return this.user_jumin1;
	}

	/*
	 * USER_JUMIN1 : 회원주민번호뒤 7자리
	 */
	@Column(name = "USER_JUMIN2", nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	Integer user_jumin2;

	public void setUser_jumin2(int user_jumin2) {
		this.user_jumin2 = user_jumin2;
	}

	public Integer getUser_jumin2() {
		return this.user_jumin2;
	}


	/*
	 * USER_BIRTHDATE : yyyy/mm/dd
	 */
	@Column(name = "USER_BIRTHDATE", length = 10, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_birthdate;

	public void setUser_birthdate(String user_birthdate) {
		this.user_birthdate = user_birthdate;
	}

	public String getUser_birthdate() {
		return this.user_birthdate;
	}

	/*
	 * USER_BIRTHDATE : yyyy/mm/dd
	 */
	@Column(name = "USER_BIRTHTYPE", length = 1, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	int user_birthtype;

	public void setUser_birthtype(int user_birthtype) {
		this.user_birthtype = user_birthtype;
	}

	public int getUser_birthtype() {
		return this.user_birthtype;
	}


	/*
	 * USER_ZIP1 : xxx-xxx
	 */
	@Column(name = "USER_ZIP1", length = 7, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_zip1;

	public void setUser_zip1(String user_zip1) {
		this.user_zip1 = user_zip1;
	}

	public String getUser_zip1() {
		return this.user_zip1;
	}

	/*
	 * USER_ADDRESS1
	 */
	@Column(name = "USER_ADDRESS1", length = 100, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_address1;

	public void setUser_address1(String user_address1) {
		this.user_address1 = user_address1;
	}

	public String getUser_address1() {
		return this.user_address1;
	}


	/*
	 * USER_ADDRESS2
	 */
	@Column(name = "USER_ADDRESS2", length = 50, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_address2;

	public void setUser_address2(String user_address2) {
		this.user_address2 = user_address2;
	}

	public String getUser_address2() {
		return this.user_address2;
	}


	/*
	 * USER_TEL1 : 일반전화
	 */
	@Column(name = "USER_TEL1", length = 14, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_tel1;

	public void setUser_tel1(String user_tel1) {
		this.user_tel1 = user_tel1;
	}

	public String getUser_tel1() {
		return this.user_tel1;
	}


	/*
	 * USER_TEL2 : 휴대전화
	 */
	@Column(name = "USER_TEL2", length = 14, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String user_tel2;

	public void setUser_tel2(String user_tel2) {
		this.user_tel2 = user_tel2;
	}

	public String getUser_tel2() {
		return this.user_tel2;
	}



	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("tid=[").append(tid).append("] ");
		buffer.append("user_id=[").append(user_id).append("] ");
		buffer.append("user_name=[").append(user_name).append("] ");
		buffer.append("user_grantsta=[").append(user_grantsta).append("] ");
		buffer.append("user_grade=[").append(user_grade).append("] ");
		buffer.append("user_login_ip=[").append(user_login_ip).append("] ");
		buffer.append("user_login_date=[").append(user_login_date).append("] ");
		buffer.append("user_login_num=[").append(user_login_num).append("] ");

		return buffer.toString();
	}




}
