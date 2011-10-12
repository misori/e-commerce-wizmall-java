package com.service;

import java.io.Serializable;
import java.util.List;
//import java.util.Set;

import com.domain.Category;
//import com.domain.Product;

public interface CategoryService extends Serializable{

	//전체 리스트 가져오기
	public List<Category> getAllCategoryList();

	//전체 리스트 가져오기
	//public Set<Category> getAllCategoryList();

	//form 을 통해 넘어온 데이타를 저장
	//public void saveCategory(Category category);

	/*
	 *  최대 정렬순서 가져오기
	 */
	//public Integer getMaxCatOrder(Integer comlen, String ccode, Integer strlen);

	/*
	 * 최대 cat_no 가져오기
	 */
	//public String getMaxCatno(Integer comlen, String ccode, Integer strlen);

	//게시물을 삭제한다.
	//public void delCategory(String cat_no);
	//public void delCategory(Category category);

}
