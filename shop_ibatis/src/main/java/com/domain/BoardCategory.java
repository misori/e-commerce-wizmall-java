package com.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.util.BaseObject;

public class BoardCategory extends BaseObject {
	private static final long serialVersionUID = 1L;
	public BoardCategory() {

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
	 * ORDERNUM : 카테고리 고유값 (이부분이 실제적으로 BOARD의  CATEGORY이다)
	 */
	@Column(name = "ORDERNUM", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int ordernum;
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public int getOrdernum() {
		return this.ordernum;
	}

	/**
	 * CATNAME : 파일명
	 */
	@Column(name = "CATNAME", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String catname;
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public String getCatname() {
		return this.catname;
	}
}