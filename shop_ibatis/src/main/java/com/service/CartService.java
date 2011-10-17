package com.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Cart;

/**
 *
 * @author Pondol
 *
 */
public interface CartService {


	public List<Cart> getAllCartList(int startResult, int maxRows) throws DataAccessException;
	public Cart getCartItem(int tid) throws DataAccessException;
	public Integer getPriceByOrderId(String oid) throws DataAccessException;

	//전체 리스트 가져오기
	public List<Cart> getAllCartList();

	/* 사용자 카트 정보 가져오기 */
	public List<Cart> getCartList(String oid);
	/**
	 * Save or Update Cart
	 *
	 */
	public void saveCart(Cart cart);

	/**
	 * 장바구니에서 Item을 삭제한다.
	 */
	public void deleteCart(int tid);


	/**
	 *
	 * @param category
	 * @deprecated : deleteCart로 대처
	 */
	public void delCartItem(Cart cart);
}
