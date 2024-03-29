package com.dao;

import java.util.Date;
import java.util.HashMap;
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


	public VisitMain getVistMainByDate(Date date) throws DataAccessException {
		//System.out.println(date);
		VisitMain visitMain = (VisitMain)getSqlMapClientTemplate().queryForObject("getVistMainByDate", date);
		return visitMain;
		//return null;
	}

	public VisitMain getVistMainByTerm(HashMap<String, String> day_term) throws DataAccessException{
		//System.out.println(day_term);
		VisitMain visitMain = (VisitMain)getSqlMapClientTemplate().queryForObject("getVistMainByTerm", day_term);
		return visitMain;
	}

	public VisitMain getVistMainByMonth(String date) throws DataAccessException{
		//System.out.println(day_term);
		VisitMain visitMain = (VisitMain)getSqlMapClientTemplate().queryForObject("getVistMainByMonth", date);
		return visitMain;
	}

	public VisitMain getVistMainByYear(String date) throws DataAccessException{
		//System.out.println(day_term);
		VisitMain visitMain = (VisitMain)getSqlMapClientTemplate().queryForObject("getVistMainByYear", date);
		return visitMain;
	}


	public VisitMain getVisitMainTotal() throws DataAccessException {
		VisitMain visitMain = (VisitMain)getSqlMapClientTemplate().queryForObject("getVisitMainTotal");
		return visitMain;
	}



	public VisitMain getVistMainMax() throws DataAccessException {
		VisitMain visitMain = (VisitMain)getSqlMapClientTemplate().queryForObject("getVistMainMax");
		return visitMain;
	}



	public VisitMain getVistMainMin() throws DataAccessException {
		VisitMain visitMain = (VisitMain)getSqlMapClientTemplate().queryForObject("getVistMainMin");
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
