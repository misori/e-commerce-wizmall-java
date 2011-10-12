package com.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;

import com.util.BaseObject;


public class Zipcode extends BaseObject {
	private static final long serialVersionUID = 1L;
	public Zipcode() {

	}

	@Column(name = "ZIPCODE", length = 7, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	String zipcode;
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getZipcode() {
		return this.zipcode;
	}

	@Column(name = "SIDO", length = 10, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	String sido;
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSido() {
		return this.sido;
	}

	@Column(name = "GUGUN", length = 50, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	String gugun;
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getGugun() {
		return this.gugun;
	}


	@Column(name = "DONG", length = 50, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	String dong;
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getDong() {
		return this.dong;
	}


	@Column(name = "BUNJI", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String bunji;

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	public String getBunji() {
		return this.bunji;
	}
}
