package com.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.VisitReferer;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("VisitRefererDAO")
@Transactional
public class VisitRefererDAOImpl extends SqlMapClientDaoSupport implements VisitRefererDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }



	@SuppressWarnings("unchecked")
	public List<VisitReferer> getVisitRefererList() throws DataAccessException {
		List<VisitReferer> visitReferer = getSqlMapClientTemplate().queryForList("getVisitRefererList");
        return visitReferer;
	}

	public VisitReferer getVisitRefererByTid(Integer tid)
		throws DataAccessException {
		VisitReferer visitReferer = (VisitReferer)getSqlMapClientTemplate().queryForObject("getVisitRefererByTid", tid);
		return visitReferer;
	}

	public VisitReferer getVisitReferer(VisitReferer visitReferer)
		throws DataAccessException {
		System.out.println(visitReferer);
		VisitReferer new_visitReferer = (VisitReferer)getSqlMapClientTemplate().queryForObject("getVisitRefererByDateIp", visitReferer);
		return new_visitReferer;
	}

	public void saveVisitReferer(VisitReferer visitReferer) {
		getSqlMapClientTemplate().insert("saveVisitReferer", visitReferer);

	}

	public void updateVisitReferer(VisitReferer visitReferer) {
		getSqlMapClientTemplate().update("updateVisitReferer", visitReferer);

	}

	public void deleteVisitReferer(int tid) {
		getSqlMapClientTemplate().delete("deleteVisitReferer", tid);

	}




}
