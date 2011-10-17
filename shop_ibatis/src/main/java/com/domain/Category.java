package com.domain;

import com.util.BaseObject;


/**
 *
 * @author Pondol
 *
 */
public class Category extends BaseObject {
	private static final long serialVersionUID = 1L;
	public Category() {

	}

	/*
	 * TID : AutoIncreament Value
	 */
	Integer tid;
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getTid() {
		return this.tid;
	}


	/*
	 * CAT_ORDER
	 */
	Integer cat_order;
	public void setCat_order(Integer cat_order) {
		this.cat_order = cat_order;
	}
	public Integer getCat_order() {
		return this.cat_order;
	}

	/*
	 * CAT_FLAG
	 */
	String cat_flag;
	public void setCat_flag(String cat_flag) {
		this.cat_flag = cat_flag;
	}
	public String getCat_flag() {
		return this.cat_flag;
	}


	/*
	 * CAT_NO
	 */
	String cat_no;
	public void setCat_no(String cat_no) {
		this.cat_no = cat_no;
	}
	public String getCat_no() {
		return this.cat_no;
	}

	/*
	 * CAT_NAME
	 */
	String cat_name;
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getCat_name() {
		return this.cat_name;
	}

	/*
	 * CAT_SKIN_VIEW : 카테고리별 viewer 스킨
	 */
	String cat_skin_view;
	public void setCat_skin_view(String cat_skin_view) {
		this.cat_skin_view = cat_skin_view;
	}
	public String getCat_skin_view() {
		return this.cat_skin_view;
	}

	/*
	 * CAT_SKIN : 카테고리별 리스트 스킨
	 */
	String cat_skin;
	public void setCat_skin(String cat_skin) {
		this.cat_skin = cat_skin;
	}
	public String getCat_skin() {
		return this.cat_skin;
	}



	/*
	 * CAT_TOP
	 */
	String cat_top;
	public void setCat_top(String cat_top) {
		this.cat_top = cat_top;
	}
	public String getCat_top() {
		return this.cat_top;
	}

	/*
	 * CAT_BOTTOM
	 */
	String cat_bottom;
	public void setCat_bottom(String cat_bottom) {
		this.cat_bottom = cat_bottom;
	}
	public String getCat_bottom() {
		return this.cat_bottom;
	}

	/*
	 * CAT_IMG//카테고리별 메뉴출력 이미지
	 */
	String cat_img;
	public void setCat_img(String cat_img) {
		this.cat_img = cat_img;
	}
	public String getCat_img() {
		return this.cat_img;
	}

	/*
	 * PCNT //카테고리별 등록된 제품수
	 */
	Integer pcnt;
	public void setPcnt(Integer pcnt) {
		this.pcnt = pcnt;
	}
	public Integer getPcnt() {
		return this.pcnt;
	}

}
