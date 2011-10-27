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

public class VisitReferer extends BaseObject {
	private static final long serialVersionUID = 1L;
	public VisitReferer() {

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
	 * WDATE : 등록일
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

	/**
	 * HIT : 클릭수
	 */
	@Column(name = "HIT", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int hit;

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getHit() {
		return this.hit;
	}

	/**
	 * REFERER : reference url
	 */
	@Column(name = "REFERER", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String referer;
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getReferer() {
		return this.referer;
	}

	/**
	 * IP : ip
	 */
	@Column(name = "IP", length = 40, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String ip;
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return this.ip;
	}


}
