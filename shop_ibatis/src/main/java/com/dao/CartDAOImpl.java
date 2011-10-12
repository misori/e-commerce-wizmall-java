package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Cart;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("CartDAO")
@Transactional
public class CartDAOImpl extends SqlMapClientDaoSupport implements CartDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cart> getAllCartList() throws DataAccessException {
		List<Cart> cart = getSqlMapClientTemplate().queryForList("getAllCartList");
        return cart;
	}

	@Transactional
	public List<Cart> getAllCartList(int startResult, int maxRows) throws DataAccessException {
        return getAllCartList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cart> getCartList(String oid) throws DataAccessException {
		List<Cart> cart = getSqlMapClientTemplate().queryForList("getCartList", oid);
        return cart;
	}

	public Cart getCartItem(int tid) throws DataAccessException {
		Cart cart = (Cart)getSqlMapClientTemplate().queryForObject("getCartItem", tid);
		return cart;
	}

	public Integer getPriceByOrderId(String oid) throws DataAccessException {
		return ((Integer)super.getSqlMapClientTemplate().queryForObject("getPriceByOrderId", oid));//.intValue();
	}


	public void deleteCart(int tid) {
		getSqlMapClientTemplate().delete("deleteCart", tid);
	}

	public void deleteCart(String oid) {
		getSqlMapClientTemplate().delete("deleteCartByOid", oid);

	}
	public void saveCart(Cart cart) {
		getSqlMapClientTemplate().insert("saveCart", cart);

	}


	public void updateCart(Cart cart) {
		getSqlMapClientTemplate().update("updateCart", cart);

	}




}