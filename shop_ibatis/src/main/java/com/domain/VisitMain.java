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

public class VisitMain  extends BaseObject {
	private static final long serialVersionUID = 1L;
	public VisitMain() {

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
	 * UNIQUE_COUNTER : 중복을 제외한 카운트 수
	 */
	@Column(name = "UNIQUE_COUNTER", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int unique_counter;

	public void setUnique_counter(int unique_counter) {
		this.unique_counter = unique_counter;
	}

	public int getUnique_counter() {
		return this.unique_counter;
	}

	/**
	 * PAGEVIEW : 모든 페이지 view(중복 포함)
	 */
	@Column(name = "PAGEVIEW", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int pageview;

	public void setPageview(int pageview) {
		this.pageview = pageview;
	}

	public int getPageview() {
		return this.pageview;
	}

}
