package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Cart;

public interface CartDAO {
	public List<Cart> getAllCartList() throws DataAccessException;
	public List<Cart> getAllCartList(int startResult, int maxRows) throws DataAccessException;
	public List<Cart> getCartList(String oid) throws DataAccessException;
	public Cart getCartItem(int tid) throws DataAccessException;
	public Integer getPriceByOrderId(String oid) throws DataAccessException;
	/**
	 * 데이타를 저장
	 */
	public void saveCart(Cart cart);

	/**
	 * 데이타를 업데이트
	 */
	public void updateCart(Cart cart);


	/**
	 * 데이타를 삭제한다.
	 * @param tid
	 */
	public void deleteCart(int tid);

	/**
	 * 데이타를 삭제한다.
	 * @param oid
	 */
	public void deleteCart(String oid);
}