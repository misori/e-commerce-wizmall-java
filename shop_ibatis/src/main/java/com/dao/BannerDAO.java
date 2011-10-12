package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Banner;

public interface BannerDAO{
	public List<Banner> getAllBannerList() throws DataAccessException;
	public Banner getBannerByTid(Integer tid) throws DataAccessException;
	/**
	 * 데이타를 저장
	 */
	public void saveBanner(Banner banner);

	/**
	 * 데이타를 업데이트
	 */
	public void updateBanner(Banner banner);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteBanner(int tid);
}
