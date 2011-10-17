package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Account;

/**
 *
 * @author Pondol
 *
 */
public interface AccountDAO {
	public List<Account> getAllAccountList() throws DataAccessException;
	public Account getAccountByTid(Integer tid) throws DataAccessException;
	/**
	 * 데이타를 저장
	 */
	public void saveAccount(Account account);

	/**
	 * 데이타를 업데이트
	 */
	public void updateAccount(Account account);

	/**
	 * 데이타를 삭제한다.
	 */
	public void deleteAccount(int tid);
}
