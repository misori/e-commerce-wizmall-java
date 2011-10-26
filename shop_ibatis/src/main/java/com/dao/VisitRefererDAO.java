package com.dao;

import java.util.List;


import com.domain.VisitReferer;

import org.springframework.dao.DataAccessException;


public interface VisitRefererDAO {
	public List<VisitReferer> getVisitRefererList() throws DataAccessException;
	public VisitReferer getVisitRefererByTid(Integer tid) throws DataAccessException;
	/**
	 * 데이타를 저장
	 */
	public void saveVisitReferer(VisitReferer visitReferer);

	/**
	 * 데이타를 업데이트
	 */
	public void updateVisitReferer(VisitReferer visitReferer);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteVisitReferer(int tid);
}
