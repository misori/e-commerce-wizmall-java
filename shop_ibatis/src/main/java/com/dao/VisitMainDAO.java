package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.VisitMain;

public interface VisitMainDAO {
	public List<VisitMain> getVisitMainList() throws DataAccessException;
	public VisitMain getVisitMainByTid(Integer tid) throws DataAccessException;
	public VisitMain getVistMainByDate(Date date) throws DataAccessException;
	/**
	 * 데이타를 저장
	 */
	public void saveVisitMain(VisitMain visitMain);

	/**
	 * 데이타를 업데이트
	 */
	public void updateVisitMain(VisitMain visitMain);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteVisitMain(int tid);
}
