package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Deliverer;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("DelivererDAO")
@Transactional
public class DelivererDAOImpl extends SqlMapClientDaoSupport implements DelivererDAO {

	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }


	@SuppressWarnings("unchecked")
	public List<Deliverer> getAllDelivererList() throws DataAccessException {
		List<Deliverer> deliverer = getSqlMapClientTemplate().queryForList("getAllDelivererList");
        return deliverer;
	}

	public Deliverer getDelivererByTid(Integer tid) throws DataAccessException {
		Deliverer deliverer = (Deliverer)getSqlMapClientTemplate().queryForObject("getDelivererByTid", tid);
		return deliverer;
	}


	public void deleteDeliverer(int tid) {
		getSqlMapClientTemplate().delete("deleteDeliverer", tid);

	}


	public void saveDeliverer(Deliverer deliverer) {
		getSqlMapClientTemplate().insert("saveDeliverer", deliverer);

	}


	public void updateDeliverer(Deliverer deliverer) {
		getSqlMapClientTemplate().update("updateDeliverer", deliverer);

	}

}