package com.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductDAO;
import com.domain.Product;

/**
 *
 * @author Pondol
 *
 */
@Service("ProductService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;


	public ProductServiceImpl() {
	}


	public List<Product> getProductByOption(String op) {
		// TODO Auto-generated method stub
		return productDAO.getProductByOption(op);
	}


	public List<Product> getProductByOption(String op, int startResult,
			int maxRows) {
		// TODO Auto-generated method stub
		return productDAO.getProductByOption(op, startResult, maxRows);
	}


	public Product getProductByPrimaryKey(Integer tid)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return productDAO.getProductByPrimaryKey(tid);
	}


	public Integer countProductByOption(String op) throws DataAccessException {
		// TODO Auto-generated method stub
		return productDAO.countProductByOption(op);
	}


	public Integer countAllProduct(HashMap<String, String> params) {
		return productDAO.countAllProduct(params);
	}

	public void deleteProduct(Product product_1) {
		// TODO Auto-generated method stub

	}



	public List<Product> getAllProductList(HashMap<String, String> params) {
		return productDAO.getAllProductList(params);
	}


	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		Product is_product = productDAO.getProductByPrimaryKey(product.getTid());
		if (is_product != null) {//보존될 값은 새로 정의한다.
			is_product	= product;
			is_product.setTid(product.getTid());
			is_product.setHit(product.getHit());
			productDAO.updateProduct(is_product);
		} else {
			/*
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				date = cal.getTime();
				product.setWdate(cal.getTime());
			*/
			product.setHit(0);
			productDAO.saveProduct(product);
		}
		//productDAO.flush();
	}

	public void updateProductViewCount(Product product) {
		// TODO Auto-generated method stub

	}


	public void deleteProduct(Integer tid) {
		productDAO.deleteProduct(tid);
	}



}
