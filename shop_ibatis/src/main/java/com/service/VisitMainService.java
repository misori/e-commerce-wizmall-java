package com.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.VisitMain;

public interface VisitMainService {
	public List<VisitMain> getVisitMainList() throws DataAccessException;
	public VisitMain getVisitMainTotal() throws DataAccessException;
	public VisitMain getVistMainMax() throws DataAccessException;
	public VisitMain getVistMainMin() throws DataAccessException;
	public VisitMain getVisitMainByTid(Integer tid) throws DataAccessException;
	public VisitMain getVistMainByDate(Date date) throws DataAccessException;
	public VisitMain getVistMainByTerm(HashMap<String, String> day_term) throws DataAccessException;
	public VisitMain getVistMainByMonth(String date) throws DataAccessException;
	public VisitMain getVistMainByYear(String date) throws DataAccessException;


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
