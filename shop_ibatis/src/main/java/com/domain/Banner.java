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


public class Banner extends BaseObject {
	private static final long serialVersionUID = 1L;
	public Banner() {

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
	 * BANNER_POS : 베너 포지션
	 */
	@Column(name = "BANNER_POS", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int banner_pos;
	public void setBanner_pos(int banner_pos) {
		this.banner_pos = banner_pos;
	}
	public int getBanner_pos() {
		return this.banner_pos;
	}

	/**
	 * ORDERNUM : 순서 플래그
	 */
	@Column(name = "ORDERNUM", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int ordernum;

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public int getOrdernum() {
		return this.ordernum;
	}

	/**
	 * SUBJECT : 베너 제목
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

	/**
	 * URL : target url
	 */
	@Column(name = "URL", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String url;
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return this.url;
	}

	/**
	 * TARGET : target(blank.., parent)
	 */
	@Column(name = "TARGET", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String target;
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTarget() {
		return this.target;
	}

	/**
	 * ATTACHED : 베너 이미지
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

	/**
	 * SHOWFLAG : display/hide flag
	 */
	@Column(name = "SHOWFLAG", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int showflag;

	public void setShowflag(int showflag) {
		this.showflag = showflag;
	}

	public int getShowflag() {
		return this.showflag;
	}

	/**
	 * CNT : 베너 클릭수
	 */
	@Column(name = "CNT", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int cnt;

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getCnt() {
		return this.cnt;
	}

	/**
	 * WDATE : 베너등록일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WDATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Date  wdate;

	public void setWdate(Date date) {
		this.wdate = date;
	}
	public Date getWdate() {
		return this.wdate;
	}
}
