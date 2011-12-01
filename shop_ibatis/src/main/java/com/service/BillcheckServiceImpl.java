package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BillcheckDAO;
import com.domain.Billcheck;


@Service("BillcheckService")
@Transactional
public class BillcheckServiceImpl  implements BillcheckService{
	@Autowired
	private BillcheckDAO billcheckDAO;

	public Billcheck getBillcheckByOrderId(String oid)
		throws DataAccessException {
		return billcheckDAO.getBillcheckByOrderId(oid);
	}

	public Billcheck getBillcheckByTid(Integer tid) throws DataAccessException {
		return billcheckDAO.getBillcheckByTid(tid);
	}

	public List<Billcheck> getBillcheckList() throws DataAccessException {
		return billcheckDAO.getBillcheckList();
	}

	public void saveBillcheck(Billcheck billcheck) {
		Billcheck is_billcheck	= billcheckDAO.getBillcheckByTid(billcheck.getTid());

		if (is_billcheck != null) {
			//is_billcheck.setAccount_no(billcheck.getAccount_no());
			//is_billcheck.setAccount_owner(billcheck.getAccount_owner());
			//is_billcheck.setBankname(billcheck.getBankname());
			billcheckDAO.updateBillcheck(billcheck);
		} else {
			billcheckDAO.saveBillcheck(billcheck);
		}
	}

	public void updateBillcheck(Billcheck billcheck) {
		billcheckDAO.updateBillcheck(billcheck);
	}

	public void deleteBillcheck(int tid) {
		billcheckDAO.deleteBillcheck(tid);
	}
}
