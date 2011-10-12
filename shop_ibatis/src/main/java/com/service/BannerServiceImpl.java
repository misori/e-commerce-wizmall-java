package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BannerDAO;
import com.domain.Banner;

@Service("BannerService")
@Transactional
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerDAO bannerDAO;

	public List<Banner> getAllBannerList() {
		// TODO Auto-generated method stub
		return bannerDAO.getAllBannerList();
	}

	public void saveBanner(Banner banner) {
		Banner is_banner	= bannerDAO.getBannerByTid(banner.getTid());

		if (is_banner != null) {
			bannerDAO.updateBanner(banner);
		} else {
			bannerDAO.saveBanner(banner);
		}
	}

	/**
	 * @deprecated
	 */
	public void deleteBanner(Banner banner) {
		// TODO Auto-generated method stub

	}

	public void deleteBanner(Integer tid) {
		bannerDAO.deleteBanner(tid);

	}

	public Banner getBannerByTid(Integer tid) {
		// TODO Auto-generated method stub
		return bannerDAO.getBannerByTid(tid);
	}

}
