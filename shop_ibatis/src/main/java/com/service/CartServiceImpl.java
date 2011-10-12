package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CartDAO;
import com.domain.Cart;

@Service("CartService")
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;


	public CartServiceImpl() {
	}

	//전체 리스트 가져오기
	@Transactional
	public List<Cart> getAllCartList(){
		return cartDAO.getAllCartList();
	}

	/* 사용자 카트 정보 가져오기 */
	@Transactional
	public List<Cart> getCartList(String oid){
		return cartDAO.getCartList(oid);
	}

	@Transactional
	public void delCartItem(Cart cart){

	}

	public void deleteCart(int tid) {
		cartDAO.deleteCart(tid);

	}

	@Transactional
	public void saveCart(Cart cart) {
		//System.out.println("cart.getTid():"+cart.getTid());

		if (cart.getTid() != null) {
			//is_cart	= product;
			//is_cart.setTid(product.getTid());
			//product = productDAO.store(is_product);
			Cart is_cart = cartDAO.getCartItem(cart.getTid());
			cartDAO.updateCart(cart);
		} else {
			cartDAO.saveCart(cart);
		}
	}

	public List<Cart> getAllCartList(int startResult, int maxRows)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Cart getCartItem(int tid) throws DataAccessException {
		// TODO Auto-generated method stub
		return cartDAO.getCartItem(tid);
	}

	public Integer getPriceByOrderId(String oid) throws DataAccessException {
		// TODO Auto-generated method stub
		return cartDAO.getPriceByOrderId(oid);
	}

}
