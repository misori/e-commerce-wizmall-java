package com.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;


public class MembersGen  implements Serializable {

	private static final long serialVersionUID = 1L;


	/*
	 * USER_ID
	 */
	@Column(name = "USER_ID", length = 15, nullable = true)//false
	@Basic(fetch = FetchType.EAGER)
	@Id
	String user_id;

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return this.user_id;
	}


	/*
	 * USER_GENDER
	 * M:Man, W:Woman
	 */
	@Column(name = "USER_GENDER", length = 2, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_gender;

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_gender() {
		return this.user_gender;
	}


	/*
	 * USER_JUMIN1 : 회원주민번호 앞 6자리
	 */
	@Column(name = "USER_JUMIN1", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int user_jumin1;

	public void setUser_jumin1(int user_jumin1) {
		this.user_jumin1 = user_jumin1;
	}

	public int getUser_jumin1() {
		return this.user_jumin1;
	}

	/*
	 * USER_JUMIN1 : 회원주민번호뒤 7자리
	 */
	@Column(name = "USER_JUMIN2", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int user_jumin2;

	public void setUser_jumin2(int user_jumin2) {
		this.user_jumin2 = user_jumin2;
	}

	public int getUser_jumin2() {
		return this.user_jumin2;
	}


	/*
	 * USER_BIRTHDATE : yyyy/mm/dd
	 */
	@Column(name = "USER_BIRTHDATE", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_birthdate;

	public void setUser_birthdate(String user_birthdate) {
		this.user_birthdate = user_birthdate;
	}

	public String getUser_birthdate() {
		return this.user_birthdate;
	}

	/*
	 * USER_BIRTHDATE : yyyy/mm/dd
	 */
	@Column(name = "USER_BIRTHTYPE", length = 1, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int user_birthtype;

	public void setUser_birthtype(int user_birthtype) {
		this.user_birthtype = user_birthtype;
	}

	public int getUser_birthtype() {
		return this.user_birthtype;
	}


	/*
	 * USER_ZIP1 : xxx-xxx
	 */
	@Column(name = "USER_ZIP1", length = 7, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_zip1;

	public void setUser_zip1(String user_zip1) {
		this.user_zip1 = user_zip1;
	}

	public String getUser_zip1() {
		return this.user_zip1;
	}

	/*
	 * USER_ADDRESS1
	 */
	@Column(name = "USER_ADDRESS1", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_address1;

	public void setUser_address1(String user_address1) {
		this.user_address1 = user_address1;
	}

	public String getUser_address1() {
		return this.user_address1;
	}


	/*
	 * USER_ADDRESS2
	 */
	@Column(name = "USER_ADDRESS2", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_address2;

	public void setUser_address2(String user_address2) {
		this.user_address2 = user_address2;
	}

	public String getUser_address2() {
		return this.user_address2;
	}


	/*
	 * USER_TEL1 : 일반전화
	 */
	@Column(name = "USER_TEL1", length = 14, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_tel1;

	public void setUser_tel1(String user_tel1) {
		this.user_tel1 = user_tel1;
	}

	public String getUser_tel1() {
		return this.user_tel1;
	}


	/*
	 * USER_TEL2 : 휴대전화
	 */
	@Column(name = "USER_TEL2", length = 14, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_tel2;

	public void setUser_tel2(String user_tel2) {
		this.user_tel2 = user_tel2;
	}

	public String getUser_tel2() {
		return this.user_tel2;
	}

	/*
	 * USER_TEL3 : 회사전화
	 */
	@Column(name = "USER_TEL3", length = 14, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_tel3;

	public void setUser_tel3(String user_tel3) {
		this.user_tel3 = user_tel3;
	}

	public String getUser_tel3() {
		return this.user_tel3;
	}

	/*
	 * USER_FAX : 팩스번호
	 */
	@Column(name = "USER_FAX", length = 14, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String user_fax;

	public void setUser_fax(String user_fax) {
		this.user_fax = user_fax;
	}

	public String getUser_fax() {
		return this.user_fax;
	}


	public MembersGen() {
	}
}
