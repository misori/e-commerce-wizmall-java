package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.VisitMain;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("VisitMainDAO")
@Transactional
public class VisitMainDAOImpl extends SqlMapClientDaoSupport implements VisitMainDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }



	@SuppressWarnings("unchecked")
	public List<VisitMain> getVisitMainList() throws DataAccessException {
		List<VisitMain> visitMain = getSqlMapClientTemplate().queryForList("getVisitMainList");
        return visitMain;
	}

	public VisitMain getVisitMainByTid(Integer tid) throws DataAccessException {
		VisitMain visitMain = (VisitMain)getSqlMapClientTemplate().queryForObject("getVisitMainByTid", tid);
		return visitMain;
	}

	public void saveVisitMain(VisitMain visitMain) {
		getSqlMapClientTemplate().insert("saveVisitMain", visitMain);

	}

	public void updateVisitMain(VisitMain visitMain) {
		getSqlMapClientTemplate().update("updateVisitMain", visitMain);

	}

	public void deleteVisitMain(int tid) {
		getSqlMapClientTemplate().delete("deleteVisitMain", tid);

	}
}
