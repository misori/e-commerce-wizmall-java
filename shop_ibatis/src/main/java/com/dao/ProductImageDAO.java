package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.ProductImage;

/**
 *
 * @author Pondol
 *
 */
public interface ProductImageDAO {
	public List<ProductImage> getProductImageByProduct(int pid) throws DataAccessException;
	public ProductImage getProductImageByTid(int tid) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveProductImage(ProductImage productImage);

	/**
	 * 데이타를 업데이트
	 */
	public void updateProductImage(ProductImage productImage);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteProductImage(int tid);

	//public Integer deleteProductImageByProduct(int pid) throws DataAccessException;
}
