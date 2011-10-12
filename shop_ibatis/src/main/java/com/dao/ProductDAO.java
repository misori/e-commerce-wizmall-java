package com.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Cart;
import com.domain.Product;

public interface ProductDAO {
	/**
	 * JPQL Query - getAllProductList
	 *
*/

	public List<Product> getAllProductList(HashMap<String, String> params) throws DataAccessException;

	//public Set<Product> getAllProductList1() throws DataAccessException;
	/**
	 * JPQL Query - getProductByPrimaryKey
	 *
 */
	public Product getProductByPrimaryKey(Integer tid) throws DataAccessException;
	public Product getProductByPrimaryKey(Integer tid, int startResult, int maxRows) throws DataAccessException;

	/**
	 * 총 상품수 구하기
*/
	public Integer countAllProduct(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 옵션상품별 상품가져오기
	 */
	public List<Product> getProductByOption(String op) throws DataAccessException;
	public List<Product> getProductByOption(String op, int startResult, int maxRows) throws DataAccessException;

	/**
	 * 옵션별 검색 count 가져오기
	 */
	public Integer countProductByOption(String op) throws DataAccessException;

	/**
	 * MAX TID 구하기
*/
	public Integer getProductMaxTid() throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveProduct(Product product);

	/**
	 * 데이타를 업데이트
	 */
	public void updateProduct(Product product);

	/**
	 * 데이타를 삭제한다.
	 */
	public void deleteProduct(int tid);
}
