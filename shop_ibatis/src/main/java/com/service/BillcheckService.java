package com.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Billcheck;

public interface BillcheckService {
	public List<Billcheck> getBillcheckList() throws DataAccessException;
	public Billcheck getBillcheckByTid(Integer tid) throws DataAccessException;
	public Billcheck getBillcheckByOrderId(String oid) throws DataAccessException;
	/**
	 * 데이타를 저장
	 */
	public void saveBillcheck(Billcheck billcheck);

	/**
	 * 데이타를 업데이트
	 */
	public void updateBillcheck(Billcheck billcheck);

	/**
	 * 데이타를 삭제한다.
	 */
	public void deleteBillcheck(int tid);
}
