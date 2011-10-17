package com.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.util.BaseObject;

/**
 *
 * @author Pondol
 *
 */
public class Deliverer extends BaseObject {
	private static final long serialVersionUID = 1L;
	public Deliverer() {
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
	 * D_NAME : 택배업체명
	 */
	@Column(name = "D_NAME", length = 45, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String d_name;
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getD_name() {
		return this.d_name;
	}


	/**
	 * D_CODE : 택배업체코드
	 */
	@Column(name = "D_CODE", length = 45, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String d_code;
	public void setD_code(String d_code) {
		this.d_code = d_code;
	}
	public String getD_code() {
		return this.d_code;
	}


	/**
	 * D_URL : 택배업체 홈페이지
	 */
	@Column(name = "D_URL", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String d_url;
	public void setD_url(String d_url) {
		this.d_url = d_url;
	}
	public String getD_url() {
		return this.d_url;
	}


	/**
	 * D_INQUIRE_URL : 배송조회 URL
	 */
	@Column(name = "D_INQUIRE_URL", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String d_inquire_url;
	public void setD_inquire_url(String d_inquire_url) {
		this.d_inquire_url = d_inquire_url;
	}
	public String getD_inquire_url() {
		return this.d_inquire_url;
	}

	/**
	 * D_METHOD : 배송조회 방법(GET, POST)
	 */
	@Column(name = "D_METHOD", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String d_method;
	public void setD_method(String d_method) {
		this.d_method = d_method;
	}
	public String getD_method() {
		return this.d_method;
	}
}
