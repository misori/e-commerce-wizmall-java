package com.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

//import com.domain.BoardFile;
import com.domain.Category;

//import org.skyway.spring.util.dao.JpaDao;
//import org.springframework.dao.DataAccessException;
/**
 *
 * @author Pondol
 *
 */
public interface CategoryDAO {


	//public List<Category> getAllCategoryList() throws DataAccessException;
	public List<Category> getAllCategoryList();
	//public List<Category> getAllCategoryList(int startResult, int maxRows) throws DataAccessException;
	public List<Category> getCategoryPerLength(Integer length);
	public List<Category> getCatListByCatNo(String cat_no) throws DataAccessException;
	public Category getCategoryByPrimaryKey(Integer tid) throws DataAccessException;


	/**
	 * JPQL Query - getAllCategoryList
	 * 특정 카테고리의 바로 하위 카테고리 리스트를 가져온다.
	 * @param lenght:현재코드의 길이(이부분은 생략되어도 될 것 같음)
	 * @param code: 현재 코드
	 */
	public List<Category> getCategoryList(HashMap<String, String> params) throws DataAccessException;


	/**
	 *  최대 정렬순서 가져오기
	 */
	public Integer getMaxCatOrder(Integer comlen, String ccode, Integer strlen) throws DataAccessException;

	/**
	 * 최대 cat_no 가져오기
	 */
	public String getMaxCatno(Integer comlen, String ccode, Integer strlen) throws DataAccessException;

	/**
	 * form 을 통해 넘어온 데이타를 저장
	 * @param category
	 */
	public void saveCategory(Category category);

	/**
	 * 데이타를 업데이트
	 */
	public void updateCategory(Category category);

	/**
	 * 게시물을 삭제한다.
	 * @param cat_no
	 */
	public void deleteCategory(String cat_no);
	public void deleteCategory(Category category);


}
