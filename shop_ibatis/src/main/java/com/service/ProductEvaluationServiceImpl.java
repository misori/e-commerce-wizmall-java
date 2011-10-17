package com.service;

import java.util.List;

import com.dao.ProductEvaluationDAO;
import com.domain.ProductEvaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pondol
 *
 */
@Service("ProductEvaluationService")
@Transactional
public class ProductEvaluationServiceImpl implements ProductEvaluationService{

	@Autowired
	private ProductEvaluationDAO productEvaluationDAO;


	public List<ProductEvaluation> getProductEvaluationList()
			throws DataAccessException {
		return productEvaluationDAO.getProductEvaluationList();
	}

	public List<ProductEvaluation> getProductEvaluationListByPid(Integer pid)
			throws DataAccessException {
		return productEvaluationDAO.getProductEvaluationListByPid(pid);
	}

	public ProductEvaluation getProductEvaluationListByTid(Integer tid)
			throws DataAccessException {
		return productEvaluationDAO.getProductEvaluationListByTid(tid);
	}

	public void saveProductEvaluation(ProductEvaluation productEvaluation) {
		ProductEvaluation is_productEvaluation	= productEvaluationDAO.getProductEvaluationListByTid(productEvaluation.getTid());

		if (is_productEvaluation != null) {
			productEvaluationDAO.updateProductEvaluation(productEvaluation);
		} else {
			productEvaluationDAO.saveProductEvaluation(productEvaluation);
		}


	}

	public void updateProductEvaluation(ProductEvaluation productEvaluation) {
		productEvaluationDAO.updateProductEvaluation(productEvaluation);

	}

	public void deleteProductEvaluation(int tid) {
		productEvaluationDAO.deleteProductEvaluation(tid);

	}

	public void deleteProductEvaluationByPid(int pid) {
		productEvaluationDAO.deleteProductEvaluationByPid(pid);

	}


}
