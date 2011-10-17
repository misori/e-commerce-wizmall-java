package com.service;

import java.util.List;

import com.domain.Zipcode;

/**
 *
 * @author Pondol
 *
 */
public interface ZipcodeService {
	public List<Zipcode> getAddressByKeyword(String keyword);

	public List<Zipcode> getAllZipAddress();
}
