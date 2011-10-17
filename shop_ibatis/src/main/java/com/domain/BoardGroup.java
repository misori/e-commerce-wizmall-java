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
public class BoardGroup extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


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
  	 * G_NAME : 그룹 명
  	 */
  	@Column(name = "G_NAME", length = 30, nullable = true)
  	@Basic(fetch = FetchType.EAGER)
  	String g_name;
  	public void setG_name(String g_name) {
  		this.g_name = g_name;
  	}
  	public String getG_name() {
  		return this.g_name;
  	}


	/**
	 * G_CODE : 그룹코드 (BoardMain 의 GID와 동일)
	 */
	@Column(name = "G_CODE", length = 20, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	String g_code;
	public void setG_code(String g_code) {
		this.g_code = g_code;
	}
	public String getG_code() {
		return this.g_code;
	}

	/**
	 * W_DATE : 그룹등록일
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
