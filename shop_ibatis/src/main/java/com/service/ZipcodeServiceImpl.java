package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ZipcodeDAO;
import com.domain.Zipcode;

/**
 *
 * @author Pondol
 *
 */
@Service("ZipcodeService")
@Transactional
public class ZipcodeServiceImpl implements ZipcodeService {

	@Autowired
	private ZipcodeDAO zipcodeDAO;

	public ZipcodeServiceImpl(){

	}

	@Transactional
	public List<Zipcode> getAddressByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return zipcodeDAO.getAddressByKeyword(keyword);
	}
	@Transactional
	public List<Zipcode> getAllZipAddress() {
		// TODO Auto-generated method stub
		return zipcodeDAO.getAllZipAddress();
	}
}
