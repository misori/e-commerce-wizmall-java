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

public class Billcheck extends BaseObject {
	private static final long serialVersionUID = 1L;
	public Billcheck() {

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
	 * USER_ID : 신청자 아이디
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
	 * ORDERID : 주문번호
	 */
	@Column(name = "ORDERID", length = 13, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String orderid;
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderid() {
		return this.orderid;
	}

	/**
	 * PTYPE : 발행타입 : 1, 세금계산서 발행신청, 2, 현금영수증 발행신청
	 */
	@Column(name = "PTYPE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int ptype;

	public void setPtype(int ptype) {
		this.ptype = ptype;
	}

	public int getPtype() {
		return this.ptype;
	}


	/**
	 * CNUM : 사업자 등록 번호
	 */
	@Column(name = "CNUM", length = 12, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String cnum;
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	public String getCnum() {
		return this.cnum;
	}


	/**
	 * CNAME : 상호명
	 */
	@Column(name = "CNAME", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String cname;
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCname() {
		return this.cname;
	}


	/**
	 * CCEO : 대표자명
	 */
	@Column(name = "CNAME", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String cceo;
	public void setCceo(String cceo) {
		this.cceo = cceo;
	}
	public String getCceo() {
		return this.cceo;
	}


	/**
	 * CUPTAE : 업태
	 */
	@Column(name = "CUPTAE", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String cuptae;
	public void setCuptae(String cuptae) {
		this.cuptae = cuptae;
	}
	public String getCuptae() {
		return this.cuptae;
	}


	/**
	 * CUPJONG : 업종
	 */
	@Column(name = "CUPJONG", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String cupjong;
	public void setCupjong(String cupjong) {
		this.cupjong = cupjong;
	}
	public String getCupjong() {
		return this.cupjong;
	}


	/**
	 * CACHRECEIPT : 현금영수증번호
	 */
	@Column(name = "CACHRECEIPT", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String cachreceipt;
	public void setCachreceipt(String cachreceipt) {
		this.cachreceipt = cachreceipt;
	}
	public String getCachreceipt() {
		return this.cachreceipt;
	}


	/**
	 * CADDRESS1 : 사업장 주소
	 */
	@Column(name = "CADDRESS1", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String caddress1;
	public void setCaddress1(String caddress1) {
		this.caddress1 = caddress1;
	}
	public String getCaddress1() {
		return this.caddress1;
	}


	/**
	 * PRESULT : 결과값 , 1:신청
	 */
	@Column(name = "PRESULT", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	int presult;

	public void setPresult(int presult) {
		this.presult = presult;
	}

	public int getPresult() {
		return this.presult;
	}


	/**
	 * RDATE : 발급요청일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RDATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Date  rdate;

	public void setRdate(Date date) {
		this.rdate = date;
	}
	public Date getRdate() {
		return this.rdate;
	}


	/**
	 * PDATE : 발급요청일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PDATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Date  pdate;

	public void setPdate(Date date) {
		this.pdate = date;
	}
	public Date getPdate() {
		return this.pdate;
	}



}
