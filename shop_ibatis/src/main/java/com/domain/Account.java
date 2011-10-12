package com.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.util.BaseObject;


public class Account extends BaseObject {
	private static final long serialVersionUID = 1L;
	public Account() {

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
	 * BANKNAME : 은행명
	 */
	@Column(name = "BANKNAME", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String bankname;
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankname() {
		return this.bankname;
	}


	/**
	 * ACCOUNT_NO : 계좌번호
	 */
	@Column(name = "ACCOUNT_NO", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String account_no;
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getAccount_no() {
		return this.account_no;
	}

	/**
	 * ACCOUNT_OWNER : 소유자명
	 */
	@Column(name = "ACCOUNT_OWNER", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String account_owner;
	public void setAccount_owner(String account_owner) {
		this.account_owner = account_owner;
	}
	public String getAccount_owner() {
		return this.account_owner;
	}
}
