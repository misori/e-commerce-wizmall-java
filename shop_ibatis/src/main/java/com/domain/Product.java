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

/**
 *
 * @author Pondol
 *
 */
public class Product extends BaseObject {
	private static final long serialVersionUID = 1L;


	public Product() {
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
	 * NAME : 제품명
	 */
	@Column(name = "NAME", length = 100, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String name;

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}





	/**
	 * PRICE : 제품가격(제품판매가격)
	 */
	@Column(name = "PRICE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer price;

	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getPrice() {
		return this.price;
	}

	/**
	 * PRICE1 : 제품가격(소비자가격)
	 */
	@Column(name = "PRICE1", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer price1;

	public void setPrice1(Integer price1) {
		this.price1 = price1;
	}
	public Integer getPrice1() {
		return this.price1;
	}


	/**
	 * POINT : 제품포인트
	 */
	@Column(name = "POINT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer point;

	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getPoint() {
		return this.point;
	}

	/**
	 * INPUTPRICE : 입고가(원가)
	 */
	@Column(name = "INPUTPRICE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer inputprice;

	public void setInputprice(Integer inputprice) {
		this.inputprice = inputprice;
	}
	public Integer getInputprice() {
		return this.inputprice;
	}

	/**
	 * UNIT : 단위(개, 박스..)
	 */
	@Column(name = "UNIT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String unit;

	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnit() {
		return this.unit;
	}



	/**
	 * MODEL : 제품 모델
	 */
	@Column(name = "MODEL", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String model;

	public void setModel(String model) {
		this.model = model;
	}
	public String getModel() {
		return this.model;
	}

	/**
	 * BRAND : 브랜드
	 */
	@Column(name = "BRAND", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String brand;

	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrand() {
		return this.brand;
	}


	/**
	 * COMPNAME : 제조사(원산지)
	 */
	@Column(name = "COMPNAME", length = 50, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String compname;

	public void setCompname(String compname) {
		this.compname = compname;
	}
	public String getCompname() {
		return this.compname;
	}

	/**
	 * REGOPTION : 등록옵션  - "|"으로 각각 분리
	 */
	@Column(name = "REGOPTION", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String regoption;

	public void setRegoption(String regoption) {
		this.regoption = regoption;
	}
	public String getRegoption() {
		return this.regoption;
	}


	/**
	 * VENDOR : 공급처
	 */
	@Column(name = "VENDOR", length = 15, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String vendor;

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getVendor() {
		return this.vendor;
	}


	/**
	 * ATTACHED : 첨부화일
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
	 * DESCRIPTION1 : 제품 설명
	 */
	@Column(name = "DESCRIPTION1", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String description1;

	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public String getDescription1() {
		return this.description1;
	}

	/**
	 * DESCRIPTION2 : 배송정보
	 */
	@Column(name = "DESCRIPTION2", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String description2;

	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getDescription2() {
		return this.description2;
	}


	/**
	 * DESCRIPTION3 : 간단한 정보
	 */
	@Column(name = "DESCRIPTION3", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String description3;

	public void setDescription3(String description3) {
		this.description3 = description3;
	}
	public String getDescription3() {
		return this.description3;
	}


	/**
	 * CATEGORY : 제품 카테고리
	 */
	@Column(name = "CATEGORY", length = 15, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String category;

	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return this.category;
	}


	/**
	 * TEXTTYPE : 텍스트타입("|"로 각각 구분)
	 */
	@Column(name = "TEXTTYPE", length = 20, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String texttype;

	public void setTexttype(String texttype) {
		this.texttype = texttype;
	}
	public String getTexttype() {
		return this.texttype;
	}

	/**
	 * NONE : 1:품절, 0:default
	 */
	@Column(name = "NONE", length = 1, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	int none;

	public void setNone(int none) {
		this.none = none;
	}
	public int getNone() {
		return this.none;
	}

	/**
	 * WDATE : 제품 등록일
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WDATE", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Date wdate;

	public void setWdate(java.util.Date date) {
		this.wdate = date;
	}
	public Date getWdate() {
		return this.wdate;
	}


	/**
	 * HIT : 제품 조회수
	 */
	@Column(name = "HIT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer hit;

	public void setHit (Integer hit) {
		this.hit = hit;
	}
	public Integer getHit() {
		return this.hit;
	}

	/**
	 * REGID : 등록자아이디
	 */
	@Column(name = "REGID", length = 7, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String regid;

	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegid() {
		return this.regid;
	}

	/**
	 * REF_TID : 상품을 복사할때 부모 ID
	 */
	@Column(name = "REF_TID", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer ref_tid;

	public void setRef_tid (Integer ref_tid) {
		this.ref_tid = ref_tid;
	}
	public Integer getRef_tid() {
		return this.ref_tid;
	}

	/**
	 * SIMILARPD : 관련상품 ("|"으로 분리등록)
	 */
	@Column(name = "SIMILARPD", length = 30, nullable = true)
	@Basic(fetch = FetchType.EAGER)
	String similarpd;

	public void setSimilarpd(String similarpd) {
		this.similarpd = similarpd;
	}
	public String getSimilarpd() {
		return this.similarpd;
	}

	/**
	 * TMPOUTPUT : 임시 출고 갯수(판매는 되었지만 출고는 되지 않은 갯수)
	 */
	@Column(name = "TMPOUTPUT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer tmpoutput;

	public void setTmpoutput(Integer tmpoutput) {
		this.tmpoutput = tmpoutput;
	}
	public Integer getTmpoutput() {
		return this.tmpoutput;
	}

	/**
	 * TMPOUTPUT : 임시 출고 갯수(판매는 되었지만 출고는 되지 않은 갯수)
	 */
	@Column(name = "OUTPUT", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer output;

	public void setOutput(Integer output) {
		this.output = output;
	}
	public Integer getOutput() {
		return this.output;
	}

	/**
	 * STOCK : 현재 재고 갯수
	 */
	@Column(name = "STOCK", nullable = true)
	@Basic(fetch = FetchType.EAGER)
	Integer stock;

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getStock() {
		return this.stock;
	}
}
