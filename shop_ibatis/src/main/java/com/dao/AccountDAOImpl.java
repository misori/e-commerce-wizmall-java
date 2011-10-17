package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Account;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("AccountDAO")
@Transactional
public class AccountDAOImpl extends SqlMapClientDaoSupport implements AccountDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	@SuppressWarnings("unchecked")
	public List<Account> getAllAccountList() throws DataAccessException {
		List<Account> account = getSqlMapClientTemplate().queryForList("getAllAccountList");
        return account;
	}

	public Account getAccountByTid(Integer tid) throws DataAccessException {
		Account account = (Account)getSqlMapClientTemplate().queryForObject("getAccountByTid", tid);
		return account;
	}

	public void deleteAccount(int tid) {
		getSqlMapClientTemplate().delete("deleteAccount", tid);

	}

	public void saveAccount(Account account) {
		getSqlMapClientTemplate().insert("saveAccount", account);

	}

	public void updateAccount(Account account) {
		getSqlMapClientTemplate().update("updateAccount", account);

	}

}
