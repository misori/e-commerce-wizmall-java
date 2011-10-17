package com.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Buyers;

/**
 *
 * @author Pondol
 *
 */
public interface BuyerDAO {
	public Buyers getBuyerByOrderId(String tid) throws DataAccessException;
	public Buyers getBuyerByTid(Integer tid) throws DataAccessException;

	/**
	 *
	 * @return
	 * @throws DataAccessException
	 *
	 */
	public List<Buyers> getAllBuyerList(HashMap<String, String> params) throws DataAccessException;
	public List<Buyers> getBuyerList(HashMap<String, String> params) throws DataAccessException;
	/**
	 *  count 가져오기
	 */
	public Integer countAllBuyerList(HashMap<String, String> params) throws DataAccessException;
	public Integer countBuyerList(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 게시물을 삭제한다.
	 * @param oid
	 */
	public void deleteBuyers(String oid);

	/**
	 * 저장한다.
	 * @param buyers
	 */
	public void saveBuyers(Buyers buyers);

	/**
	 * update 시킨다.
	 * @param member
	 */
	public void updateBuyers(Buyers buyers);
}
