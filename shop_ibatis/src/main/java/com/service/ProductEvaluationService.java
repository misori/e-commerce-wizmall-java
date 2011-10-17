package com.service;

import java.util.List;

import com.domain.ProductEvaluation;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Pondol
 *
 */
public interface ProductEvaluationService {
	public List<ProductEvaluation> getProductEvaluationList() throws DataAccessException;
	public List<ProductEvaluation> getProductEvaluationListByPid(Integer pid) throws DataAccessException;
	public ProductEvaluation getProductEvaluationListByTid(Integer tid) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveProductEvaluation(ProductEvaluation productEvaluation);

	/**
	 * 데이타를 업데이트
	 */
	public void updateProductEvaluation(ProductEvaluation productEvaluation);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteProductEvaluation(int tid);
	public void deleteProductEvaluationByPid(int pid);
}
