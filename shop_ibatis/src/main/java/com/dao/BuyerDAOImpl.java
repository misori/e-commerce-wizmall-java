package com.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Buyers;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("BuyerDAO")
@Transactional
public class BuyerDAOImpl extends SqlMapClientDaoSupport implements BuyerDAO {

	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }


 	/**
	 * JPQL Query - getBuyerByOrderId
	 * oderId로 정보 가져오기
	 *
	 */
	public Buyers getBuyerByOrderId(String orderId)
			throws DataAccessException {
		Buyers buyers = (Buyers)getSqlMapClientTemplate().queryForObject("getBuyerByOrderId", orderId);
		return buyers;
	}

 	/**
	 * JPQL Query - getBuyerByOrderId
	 * tid로 정보 가져오기
	 *
	 */
	public Buyers getBuyerByTid(Integer tid)
			throws DataAccessException {
		Buyers buyers = (Buyers)getSqlMapClientTemplate().queryForObject("getBuyerByTid", tid);
		return buyers;
	}


	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Buyers> getAllBuyerList(HashMap<String, String> params) throws DataAccessException {
		if(params.get("s_order") != null){
			String[] orderSplit 	= params.get("s_order").split("@");
			params.put("orderKey",orderSplit[0]);
			params.put("orderValue",orderSplit[1]);
		}

		List<Buyers> buyers = getSqlMapClientTemplate().queryForList("getAllBuyerList", params);
        return buyers;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Buyers> getBuyerList(HashMap<String, String> params) throws DataAccessException {
		//System.out.println("getBuyerList:"+params);
		List<Buyers> buyers = getSqlMapClientTemplate().queryForList("getBuyerList", params);
        return buyers;
	}



	public Integer countAllBuyerList(HashMap<String, String> params) throws DataAccessException {
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("countAllBuyerList", params));//.intValue();
        return cnt;
	}

	public Integer countBuyerList(HashMap<String, String> params) throws DataAccessException {
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("countBuyerList", params));//.intValue();
        return cnt;
	}


	public void deleteBuyers(String oid) {
		getSqlMapClientTemplate().delete("deleteBuyers", oid);

	}


	public void saveBuyers(Buyers buyers) {
		getSqlMapClientTemplate().insert("saveBuyers", buyers);

	}


	public void updateBuyers(Buyers buyers) {
		getSqlMapClientTemplate().update("updateBuyers", buyers);

	}
}
