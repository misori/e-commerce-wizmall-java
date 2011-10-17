package com.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Product;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("ProductDAO")
@Transactional
public class ProductDAOImpl extends SqlMapClientDaoSupport implements ProductDAO {//BoardDAO(interface) 이므로 이 값들을 상속 받아야 한다.


	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	/**
	 * JPQL Query - getAllProductList
	 * 상품리스트 가져오기
	 *
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Product> getAllProductList(HashMap<String, String> params) throws DataAccessException {
		//System.out.println("getAllProductList:"+params);
		if(params.get("s_order") != null){
			String[] orderSplit 	= params.get("s_order").split("@");
			params.put("orderKey",orderSplit[0]);
			params.put("orderValue",orderSplit[1]);
		}

		List<Product> product = getSqlMapClientTemplate().queryForList("getAllProductList", params);
        return product;
	}


	/**
	 * (non-Javadoc)
	 * @see com.dao.BoardDAO#getBoardByPrimaryKey(java.lang.Integer)
	 */

	public Product getProductByPrimaryKey(Integer tid) throws DataAccessException {
		// TODO Auto-generated method stub
		return getProductByPrimaryKey(tid, -1, -1);
	}

	public Product getProductByPrimaryKey(Integer tid, int startResult, int maxRows) throws DataAccessException {
		Product product = (Product)getSqlMapClientTemplate().queryForObject("getProductByPrimaryKey", tid);
		return product;
	}

	/**
	 * 총 제품수 구하기
	 */


	@Transactional
	public Integer countAllProduct(HashMap<String, String> params)  throws DataAccessException {
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("countAllProduct", params)).intValue();
		return cnt;
	}

	/**
	 * 옵션상품별 상품가져오기
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Product> getProductByOption(String op) throws DataAccessException {
		//return getProductByOption(op);
		List<Product> product = getSqlMapClientTemplate().queryForList("getProductByOption", "%"+op+"%");

        return product;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Product> getProductByOption(String op, int startResult, int maxRows) throws DataAccessException {
		List<Product> product = getSqlMapClientTemplate().queryForList("getProductByOption", "%"+op+"%");

        return product;
	}

	/**
	 * 옵션별 검색 결과 count 가져오기
	 */

	public Integer countProductByOption(String op)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("countProductByOption", op)).intValue();
        return cnt;
	}


	/**
	 * MAX TID 구하기
	 */
	public Integer getProductMaxTid()  throws DataAccessException {
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("getProductMaxTid")).intValue();
        return cnt;
	}

	public void deleteProduct(int tid) {
		getSqlMapClientTemplate().delete("deleteProduct", tid);

	}

	public void saveProduct(Product product) {
		getSqlMapClientTemplate().insert("saveProduct", product);

	}

	public void updateProduct(Product product) {
		//System.out.println("updateProduct:"+product);
		getSqlMapClientTemplate().update("updateProduct", product);

	}


}
