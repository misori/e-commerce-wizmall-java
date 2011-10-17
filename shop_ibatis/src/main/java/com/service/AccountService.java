package com.service;

import java.util.List;

import com.domain.Account;

/**
 *
 * @author Pondol
 *
 */
public interface AccountService {


	public List<Account> getAllAccountList();

	/**
	 * Save an existing Account entity
	 *
	 */
	public void saveAccount(Account account);

	/**
	 * Delete an existing Account entity
	 *@deprecated
	 */
	public void deleteAccount(Account account);

	public void deleteAccount(Integer tid);

}
