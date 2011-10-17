package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Product;

/**
 *
 * @author Pondol
 *
 */
public interface ProductService {


	public List<Product> getAllProductList(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 전체 게시물 수를 가져온다.
	 * @return
 */
	public Integer countAllProduct(HashMap<String, String> params);

	/**
	 * 게시 상품의 상세 정보를 가져온다.
	 * @param tid
	 * @return
	 * @throws DataAccessException
	 */
	public Product getProductByPrimaryKey(Integer tid) throws DataAccessException;

	/**
	 * 옵션별 검색 count 가져오기
	 */
	public Integer countProductByOption(String op) throws DataAccessException;

	/**
	 * 옵션상품별 상품가져오기
	 */
	public List<Product> getProductByOption(String op);
	public List<Product> getProductByOption(String op, int startResult, int maxRows);

	/**
	 * form 을 통해 넘어온 데이타를 저장
	 * @param product
*/
	public void saveProduct(Product product);

	/**
	 * 게시물을 삭제한다.
	 * @param product_1
 */
	public void deleteProduct(Product product);
	public void deleteProduct(Integer tid);

	/**
	 * view count를 올린다.
	 * @param product
 */
	public void updateProductViewCount(Product product);

}
