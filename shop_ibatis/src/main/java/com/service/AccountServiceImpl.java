package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AccountDAO;
import com.domain.Account;

/**
 *
 * @author Pondol
 *
 */
@Service("AccountService")
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDAO accountDAO;

	public List<Account> getAllAccountList() {
		// TODO Auto-generated method stub
		return accountDAO.getAllAccountList();
	}

	/**
	 * @deprecated
	 */
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
	}

	public void deleteAccount(Integer tid) {
		accountDAO.deleteAccount(tid);
	}

	public void saveAccount(Account account) {
		// TODO Auto-generated method stub
		Account is_account	= accountDAO.getAccountByTid(account.getTid());
		System.out.println("is_account:"+is_account);
		if (is_account != null) {
			is_account.setAccount_no(account.getAccount_no());
			is_account.setAccount_owner(account.getAccount_owner());
			is_account.setBankname(account.getBankname());
			accountDAO.updateAccount(is_account);
		} else {
			accountDAO.saveAccount(account);
		}



	}

}
