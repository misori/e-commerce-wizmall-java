package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Zipcode;

public interface ZipcodeDAO {
	public List<Zipcode> getAddressByKeyword(String keyword) throws DataAccessException;
	public List<Zipcode> getAllZipAddress() throws DataAccessException;
}
