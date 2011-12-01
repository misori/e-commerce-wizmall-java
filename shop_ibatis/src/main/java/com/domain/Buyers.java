package com.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pondol
 *
 */
public class Buyers implements Serializable {

	private static final long serialVersionUID = 1L;

	public Buyers() {

	}
	/**
	 *  TID
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
	 * SNAME : 주문자 명
	 */
	@Column(name = "SNAME", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String sname;

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSname() {
		return this.sname;
	}

	/**
	 * SNAME : 주문자이메일
	 */
	@Column(name = "SEMAIL", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String semail;

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSemail() {
		return this.semail;
	}

	/**
	 * STEL1 : 주문자연락처 1
	 */
	@Column(name = "STEL1", length = 14, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String stel1;

	public void setStel1(String stel1) {
		this.stel1 = stel1;
	}

	public String getStel1() {
		return this.stel1;
	}

	/**
	 * STEL2 : 주문자연락처 2
	 */
	@Column(name = "STEL2", length = 14, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String stel2;

	public void setStel2(String stel2) {
		this.stel2 = stel2;
	}

	public String getStel2() {
		return this.stel2;
	}

	/**
	 * SZIP : 주문자우편번호
	 */
	@Column(name = "SZIP", length = 7, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String szip;

	public void setSzip(String szip) {
		this.szip = szip;
	}

	public String getSzip() {
		return this.szip;
	}

	/**
	 * SADDRESS1 : 주문자 어드레스1
	 */
	@Column(name = "SADDRESS1", length = 80, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String saddress1;

	public void setSaddress1(String saddress1) {
		this.saddress1 = saddress1;
	}

	public String getSaddress1() {
		return this.saddress1;
	}

	/**
	 * SADDRESS1 : 주문자 어드레스2
	 */
	@Column(name = "SADDRESS2", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String saddress2;

	public void setSaddress2(String saddress2) {
		this.saddress2 = saddress2;
	}

	public String getSaddress2() {
		return this.saddress2;
	}



	/**
	 * RNAME : 수취인 명
	 */
	@Column(name = "RNAME", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String rname;

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRname() {
		return this.rname;
	}


	/**
	 * RTEL1 : 수취인연락처 1
	 */
	@Column(name = "RTEL1", length = 14, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String rtel1;

	public void setRtel1(String rtel1) {
		this.rtel1 = rtel1;
	}

	public String getRtel1() {
		return this.rtel1;
	}

	/**
	 * RTEL2 : 수취인연락처 2
	 */
	@Column(name = "RTEL2", length = 14, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String rtel2;

	public void setRtel2(String rtel2) {
		this.rtel2 = rtel2;
	}

	public String getRtel2() {
		return this.rtel2;
	}

	/**
	 * RZIP : 수취인우편번호
	 */
	@Column(name = "RZIP", length = 7, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String rzip;

	public void setRzip(String rzip) {
		this.rzip = rzip;
	}

	public String getRzip() {
		return this.rzip;
	}

	/**
	 * RADDRESS1 : 수취인 어드레스1
	 */
	@Column(name = "RADDRESS1", length = 80, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String raddress1;

	public void setRaddress1(String raddress1) {
		this.raddress1 = raddress1;
	}

	public String getRaddress1() {
		return this.raddress1;
	}

	/**
	 * RADDRESS2 : 수취인 어드레스2
	 */
	@Column(name = "RADDRESS2", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String raddress2;

	public void setRaddress2(String raddress2) {
		this.raddress2 = raddress2;
	}

	public String getRaddress2() {
		return this.raddress2;
	}

	/**
	 * MESSAGE : 간단 코멘트
	 */
	@Column(name = "MESSAGE", length = 255, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	/**
	 * DELIVERER : 택배사코드 deliverer 테이블의 tid 참조
	 */
	@Column(name = "DELIVERER", length = 3, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int deliverer;

	public void setDeliverer(int deliverer) {
		this.deliverer = deliverer;
	}

	public int getDeliverer() {
		return this.deliverer;
	}

	/**
	 * INVOICENO : 택배 송장 번호
	 */
	@Column(name = "INVOICENO", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String invoiceno;

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getInvoiceno() {
		return this.invoiceno;
	}



	/**
	 * PAYMETHOD : 결제 방법
	 */
	@Column(name = "PAYMETHOD", length = 15, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String paymethod;

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getPaymethod() {
		return this.paymethod;
	}


	/**
	 * BANKINFO : 결제은행
	 */
	@Column(name = "BANKINFO", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String bankinfo;

	public void setBankinfo(String bankinfo) {
		this.bankinfo = bankinfo;
	}

	public String getBankinfo() {
		return this.bankinfo;
	}

	/**
	 * INPUTER : 송금인
	 */
	@Column(name = "INPUTER", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String inputer;

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public String getInputer() {
		return this.inputer;
	}

	/**
	 * AMOUNTPOINT : 포인트 결제 금액
	 */
	@Column(name = "AMOUNTPOINT", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int amountpoint;

	public void setAmountpoint(int amountpoint) {
		this.amountpoint = amountpoint;
	}

	public int getAmountpoint() {
		return this.amountpoint;
	}

	/**
	 * AMOUNTONLINE : 무통장입금 결제 금액
	 */
	@Column(name = "AMOUNTONLINE", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int amountonline;

	public void setAmountonline(int amountonline) {
		this.amountonline = amountonline;
	}

	public int getAmountonline() {
		return this.amountonline;
	}


	/**
	 * AMOUNTPG : PG 결제 금액
	 */
	@Column(name = "AMOUNTPG", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int amountpg;

	public void setAmountpg(int amountpg) {
		this.amountpg = amountpg;
	}

	public int getAmountpg() {
		return this.amountpg;
	}

	/**
	 * TOTALAMOUNT : 총 결제 금액
	 */
	@Column(name = "TOTALAMOUNT", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int totalamount;

	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}

	public int getTotalamount() {
		return this.totalamount;
	}


	/**
	 * ORDERID : 주문 고유 코드
	 */
	@Column(name = "ORDERID", length = 11, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String orderid;

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderid() {
		return this.orderid;
	}


	/**
	 * ORDERSTATUS : 주문진행상태
	 */
	@Column(name = "ORDERSTATUS", length = 10, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int orderstatus;

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	public int getOrderstatus() {
		return this.orderstatus;
	}

	/**
	 * MEMBERID : 주문자 아이디
	 */
	@Column(name = "MEMBERID", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String memberid;

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getMemberid() {
		return this.memberid;
	}


	/**
	 * EXPECTDATE : 입금예정일
	 */
	@Column(name = "EXPECTDATE", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String expectdate;

	public void setExpectdate(String expectdate) {
		this.expectdate = expectdate;
	}

	public String getExpectdate() {
		return this.expectdate;
	}


	/**
	 * PAYDATE : 결제일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYDATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Date paydate;

	public void setPaydate(java.util.Date date) {
		this.paydate = date;
	}
	public Date getPaydate() {
		return this.paydate;
	}


	/**
	 * BUYDATE : 구매일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BUYDATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Date buydate;

	public void setBuydate(java.util.Date date) {
		this.buydate = date;
	}
	public Date getBuydate() {
		return this.buydate;
	}
}
