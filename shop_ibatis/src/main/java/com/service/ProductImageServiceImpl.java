package com.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductImageDAO;
import com.domain.ProductImage;
@Service("ProductImageService")
@Transactional
public class ProductImageServiceImpl implements ProductImageService{

	@Autowired
	private ProductImageDAO productImageDAO;

	/**
	 * 제품이미지 삭제
	 */
	public void deleteProductImage(ProductImage productImage) {
		// TODO Auto-generated method stub

	}

	public void deleteProductImage(int tid) {
		//ProductImage productImage	= productImageDAO.getProductImageByTid(tid);
		//if(productImage != null)	deleteProductImage(productImage);
		productImageDAO.deleteProductImage(tid);
	}

	public void deleteProductImageByProduct(int pid) {
		List<ProductImage> productImageList	= productImageDAO.getProductImageByProduct(pid);
		Iterator<ProductImage> iterator		= productImageList.iterator();

		while(iterator.hasNext())
		  {
			ProductImage productImage = iterator.next();
			 deleteProductImage(productImage.getTid());
		  }
	}

	/**
	 * 제품이미지 저장
	 */
	public void saveProductImage(ProductImage productImage) {
		productImageDAO.saveProductImage(productImage);

	}

}
