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
public class BoardMain extends BaseObject {
	private static final long serialVersionUID = 1L;
	public BoardMain() {

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
	 * BID : Board Id
	 */
	@Column(name = "BID", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String bid;
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBid() {
		return this.bid;
	}

	/**
	 * GID : Board Group ID
	 */
	@Column(name = "GID", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String gid;
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGid() {
		return this.gid;
	}

	/**
	 * TITLE : 보드 제목
	 */
	@Column(name = "TITLE", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String title;
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}


	/**
	 * B_GROUP : 보드 그룹
	 */
	@Column(name = "B_GROUP", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int b_group;

	public void setB_group(int b_group) {
		this.b_group = b_group;
	}

	public int getB_group() {
		return this.b_group;
	}


	/**
	 * W_DATE : 보드생성일
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


	/*
	 * BoardGroup의 Join 용 필드
	 */

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
}