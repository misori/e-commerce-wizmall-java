package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BuyerDAO;
import com.dao.CartDAO;
import com.domain.Buyers;

/**
 *
 * @author Pondol
 *
 */
@Service("BuyerService")
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerDAO buyerDAO;

	@Autowired
	private CartDAO cartDAO;
	/**
	 * 구매자관련 정보를 지운다. 이경우는 이것 외에도 cart에 저장된 정보도 지운다.
	 */


	public Integer countAllBuyerList(HashMap<String, String> params) throws DataAccessException {
		// TODO Auto-generated method stub
		return buyerDAO.countAllBuyerList(params);
	}

	public Integer countBuyerList(HashMap<String, String> params) throws DataAccessException {
		// TODO Auto-generated method stub
		return buyerDAO.countBuyerList(params);
	}

	public List<Buyers> getAllBuyerList(HashMap<String, String> params) throws DataAccessException {
		// TODO Auto-generated method stub
		return buyerDAO.getAllBuyerList(params);
	}


	public List<Buyers> getBuyerList(HashMap<String, String> params) throws DataAccessException {
		// TODO Auto-generated method stub
		return buyerDAO.getBuyerList(params);
	}


	public Buyers getBuyerByOrderId(String tid) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Buyers getBuyerByTid(Integer tid) throws DataAccessException {
		// TODO Auto-generated method stub
		return buyerDAO.getBuyerByTid(tid);
	}


	public void deleteBuyers(String oid) {
		buyerDAO.deleteBuyers(oid);
		cartDAO.deleteCart(oid);
		// TODO Auto-generated method stub

	}
	/**
	 * @deprecated
	 */
	public void deleteBuyers(Buyers buyers) {
		// TODO Auto-generated method stub

	}

	public void saveBuyers(Buyers buyers) {
		// TODO Auto-generated method stub
		Buyers is_buyer	= buyerDAO.getBuyerByOrderId(buyers.getOrderid());
		if (is_buyer != null) {//수정
			buyerDAO.updateBuyers(buyers);
		}else{
			buyerDAO.saveBuyers(buyers);
		}

	}



/*
	public Set<Buyers> getBuyerByOrderId() {
		// TODO Auto-generated method stub
		//return null;
		return buyerDAO.getBuyerByOrderId();
	}
*/





}
