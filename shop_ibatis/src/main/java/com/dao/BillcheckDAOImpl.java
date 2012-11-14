package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Billcheck;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository("BillcheckDAO")
@Transactional
public class BillcheckDAOImpl extends SqlMapClientDaoSupport implements BillcheckDAO {

	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	public Billcheck getBillcheckByOrderId(String oid)
			throws DataAccessException {
		Billcheck billcheck = (Billcheck)getSqlMapClientTemplate().queryForObject("getBillcheckByOrderId", oid);
		return billcheck;
	}

	public Billcheck getBillcheckByTid(Integer tid) throws DataAccessException {
		Billcheck billcheck = (Billcheck)getSqlMapClientTemplate().queryForObject("getBillcheckByTid", tid);
		return billcheck;
	}

	@SuppressWarnings("unchecked")
	public List<Billcheck> getBillcheckList() throws DataAccessException {
		List<Billcheck> billcheck = getSqlMapClientTemplate().queryForList("getBillcheckList");
        return billcheck;
	}

	public void saveBillcheck(Billcheck billcheck) {
		getSqlMapClientTemplate().insert("saveBillcheck", billcheck);
	}

	public void updateBillcheck(Billcheck billcheck) {
		getSqlMapClientTemplate().update("updateBillcheck", billcheck);
	}

	public void deleteBillcheck(int tid) {
		getSqlMapClientTemplate().delete("deleteBillcheck", tid);
	}

}
