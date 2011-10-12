package com.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.util.BaseObject;


public class ProductImage extends BaseObject {
	private static final long serialVersionUID = 1L;

	public ProductImage(){

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
	 * PID : 제품코드 (product.TID)
	 */
	@Column(name = "PID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Integer pid;

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getPid() {
		return this.pid;
	}

	/**
	 * OPFLAG : 어디에 사용할 이미지인가? default : M - Mall 용
	 */
	@Column(name = "OPFLAG", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String opflag;

	public void setOpflag(String opflag) {
		this.opflag = opflag;
	}
	public String getOpflag() {
		return this.opflag;
	}


	/**
	 * ORDERID : 이미지 정렬순서
	 */
	@Column(name = "ORDERID", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer orderid;

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getOrderid() {
		return this.orderid;
	}


	/**
	 * FILENAME : 파일명
	 */
	@Column(name = "FILENAME", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String filename;

	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilename() {
		return this.filename;
	}

	/**
	 * IMGNAME : 파일에 별도 이미지 설명이 들어가 있는 경우
	 */
	@Column(name = "IMGNAME", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String imgname;

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getImgname() {
		return this.imgname;
	}
}
