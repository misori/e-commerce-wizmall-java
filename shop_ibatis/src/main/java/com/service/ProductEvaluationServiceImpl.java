package com.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductEvaluationDAO;
import com.domain.ProductEvaluation;

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

		//아이피 세팅
		try {
			InetAddress ip = InetAddress.getLocalHost();
			String user_ip	= ip.getHostAddress();
			productEvaluation.setIp(user_ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if (productEvaluation.getTid() != null) {
			ProductEvaluation is_productEvaluation	= productEvaluationDAO.getProductEvaluationListByTid(productEvaluation.getTid());
			productEvaluationDAO.updateProductEvaluation(is_productEvaluation);
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
