package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Buyers;

/**
 *
 * @author Pondol
 *
 */
public interface BuyerService {
	/**
	 * Load an existing Member entity
	 *
	 */
//	public Set<Buyers> getBuyerByOrderId();


	/**
	 * Save an existing Member entity
	 *
	 */
	public void saveBuyers(Buyers buyers);

	/**
	 * Delete an existing Member entity
	 *@deprecated
	 */
	public void deleteBuyers(Buyers buyers);

	/**
	 * delete Buyers
	 * @param oid
	 */
	public void deleteBuyers(String oid);

	/**
	 *
	 * @return
	 * @throws DataAccessException
	 */
	public Integer countAllBuyerList(HashMap<String, String> params) throws DataAccessException;
	public Integer countBuyerList(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 관리자단에서 전체 주문 리스트를 볼때 사용
	 * @return
	 * @throws DataAccessException
	 */
	public List<Buyers> getAllBuyerList(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 일반 개인페이지에서 개인별 주문리스트 등을 볼때 사용
	 * @param params
	 * @return
	 * @throws DataAccessException
	 */
	public List<Buyers> getBuyerList(HashMap<String, String> params) throws DataAccessException;


	/**
	 *
	 * @param tid
	 * @return
	 * @throws DataAccessException
	 */
	public Buyers getBuyerByOrderId(String tid) throws DataAccessException;

	/**
	 *
	 * @param tid
	 * @return
	 * @throws DataAccessException
	 */
	public Buyers getBuyerByTid(Integer tid) throws DataAccessException;
}
