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




public class Cart extends BaseObject {
	private static final long serialVersionUID = 1L;
	public Cart() {

	}

	/*
	 * left join 용 데이타 생성

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="pid", insertable=false, updatable=false)
	private Product product;

	public Product getProduct() {
		return this.product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
*/
/*
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="tid")//producttable.tid


	public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
*/
	/*
	 * TID : AutoIncreament Value
	 */
	@Column(name = "TID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@GeneratedValue(strategy=GenerationType.AUTO)//자동 증가일경우
	@Id
	// private Key tid;
	Integer cart_tid;
	/* TID */
	public void setTid(Integer tid) {
		this.cart_tid = tid;
	}
	public Integer getTid() {
		return this.cart_tid;
	}

	//주문 코드
	@Basic(fetch = FetchType.EAGER)
	String oid;
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOid() {
		return this.oid;
	}

	/* 주문 상품 */
	@Column(name = "PID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Integer pid;

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getPid() {
		return this.pid;
	}

	/* 주문갯수 */
	@Column(name = "QTY", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Integer qty;

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getQty() {
		return this.qty;
	}

	/* PRICE : 가격 */
	@Column(name = "PRICE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Integer price;

	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getPrice() {
		return this.price;
	}

	/* TPRICE : 총가격  QTY * PRICE 검증을 위해 별도로 처리*/
	@Column(name = "TPRICE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Integer tprice;

	public void setTprice(Integer tprice) {
		this.tprice = tprice;
	}
	public Integer getTprice() {
		return this.tprice;
	}


	/* POINT : 포인트 */
	@Column(name = "POINT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer point;

	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getPoint() {
		return this.point;
	}

	/* TPOINT : 총포인트 QTY * PRICE 검증을 위해 별도로 처리*/
	@Column(name = "TPOINT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer tpoint;

	public void setTpoint(Integer tpoint) {
		this.tpoint = tpoint;
	}
	public Integer getTpoint() {
		return this.tpoint;
	}

	/* OSTATUS : 주문 상태 (개별 배송일 경우에 대비해서 */
	@Column(name = "OSTATUS", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer ostatus;

	public void setOstatus(Integer ostatus) {
		this.ostatus = ostatus;
	}
	public Integer getOstatus() {
		return this.ostatus;
	}

	/* DELIVERER : 배송업체 (개별 배송일 경우에 대비해서 */
	@Column(name = "DELIVERER", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer deliverer;

	public void setDeliverer(Integer deliverer) {
		this.deliverer = deliverer;
	}
	public Integer getDeliverer() {
		return this.deliverer;
	}

	/* INVOICENO : 송장번호 (개별 배송일 경우에 대비해서 */
	@Column(name = "INVOICENO", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String invoiceno;
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public String getInvoiceno() {
		return this.invoiceno;
	}

	/* WDATE : 글 등록일 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WDATE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	Date wdate;
	public void setWdate(java.util.Date date) {
		this.wdate = date;
	}
	public Date getWdate() {
		return this.wdate;
	}

	/* join Product 속성 가져오기 */
	/**
	 * NAME : 제품명
	 */
	@Column(name = "NAME", length = 100, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String name;

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	/**
	 * ATTACHED : 첨부화일
	 */
	@Column(name = "ATTACHED", length = 100, nullable = true, insertable = false, updatable = false)
	@Basic(fetch = FetchType.EAGER)
	String attached;

	public void setAttached(String attached) {
		this.attached = attached;
	}
	public String getAttached() {
		return this.attached;
	}
}
