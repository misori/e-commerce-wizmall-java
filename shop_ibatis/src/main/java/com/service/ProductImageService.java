package com.service;

import com.domain.ProductImage;

/**
 *
 * @author Pondol
 *
 */
public interface ProductImageService {

	/**
	 * Save an existing Account entity
	 *
	 */
	public void saveProductImage(ProductImage productImage);

	/**
	 * Delete an existing Account entity
	 *
	 */
	public void deleteProductImage(ProductImage productImage);
	public void deleteProductImage(int tid);
	public void deleteProductImageByProduct(int pid);
}
