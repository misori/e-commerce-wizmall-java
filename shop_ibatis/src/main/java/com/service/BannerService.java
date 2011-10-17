package com.service;

import java.util.List;

import com.domain.Banner;

/**
 *
 * @author Pondol
 *
 */
public interface BannerService {
	public List<Banner> getAllBannerList();


	public Banner getBannerByTid(Integer tid);

	/**
	 * Save an existing Account entity
	 *
	 */
	public void saveBanner(Banner banner);

	/**
	 * Delete an existing Account entity
	 *	@deprecated
	 */
	public void deleteBanner(Banner banner);

	public void deleteBanner(Integer tid);
}
