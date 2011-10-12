package com.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Point;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("PointDAO")
@Transactional
public class PointDAOImpl  extends SqlMapClientDaoSupport implements PointDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	public Point getPointByTid(Integer tid) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Point> getPointListByUserId(String userId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}