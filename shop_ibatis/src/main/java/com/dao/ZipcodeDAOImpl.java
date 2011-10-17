package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Zipcode;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("ZipcodeDAO")
@Transactional
public class ZipcodeDAOImpl extends SqlMapClientDaoSupport implements ZipcodeDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }



	/*
	 * (non-Javadoc)
	 * @see com.dao.ZipcodeDAO#getAddressByKeyword(java.lang.String)
	 * 우편번호 검색
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Zipcode> getAddressByKeyword(String keyword) throws DataAccessException {
		keyword	= "%"+keyword+"%";
		List<Zipcode> zipcode = getSqlMapClientTemplate().queryForList("getAddressByKeyword", keyword);
        return zipcode;
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Zipcode> getAllZipAddress() throws DataAccessException {
		List<Zipcode> zipcode = getSqlMapClientTemplate().queryForList("getAllZipAddress");
        return zipcode;
	}

}
