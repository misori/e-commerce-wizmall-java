package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CategoryDAO;
import com.domain.Category;

/**
 *
 * @author Pondol
 *
 */
@Service("CategoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryDAO categoryDAO;


	//public CategoryServiceImpl() {
	//}
	public List<Category> getAllCategoryList() {
		// TODO Auto-generated method stub
		return categoryDAO.getAllCategoryList();
	}
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

	public List<Category> getCategoryPerLength(Integer length) {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryPerLength(length);
	}

	@Transactional
	public void saveCategory(Category category) {
		Category is_category = categoryDAO.getCategoryByPrimaryKey(category.getTid());
		if (is_category != null) {
			//is_category.setTid(null);
			is_category.setTid(category.getTid());
			is_category.setCat_name(category.getCat_name());
			is_category.setCat_no(category.getCat_no());
			categoryDAO.updateCategory(is_category);

		} else {
			categoryDAO.saveCategory(category);
		}

		//상기에서 store() 나 flush()는 JpaDao 로 부터 상속 받는 값이다.
	}



	/**
	 *  최대 정렬순서 가져오기
 */
	@Transactional
	public Integer getMaxCatOrder(Integer comlen, String ccode, Integer strlen){
		return categoryDAO.getMaxCatOrder(comlen, ccode, strlen);
	}

	/**
	 * 최대 cat_no 가져오기
*/
	@Transactional
	public String getMaxCatno(Integer comlen, String ccode, Integer strlen){

		return categoryDAO.getMaxCatno(comlen, ccode, strlen);
	}

	/**
	 * 카테고리를 삭제한다.(non-Javadoc)
	 * @see com.service.CategoryService#delCategory(java.lang.String)
*/
	@Transactional
	public void deleteCategory(Category category){
		categoryDAO.deleteCategory(category);

	}
	public void deleteCategory(String cat_no) {
		categoryDAO.deleteCategory(cat_no);

	}
	public List<Category> getAllCategoryList(int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		//return categoryDAO.getAllCategoryList(startResult, maxRows);
		return categoryDAO.getAllCategoryList();
	}
	public List<Category> getCategoryList(HashMap<String, String> params)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryList(params);
	}

	public Category getCategoryByPrimaryKey(Integer tid)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryByPrimaryKey(tid);
	}



}
