package com.service;

import java.util.List;

import com.domain.MembersGen;

public interface MembersGenService {

	/**
	 * Load an existing Member entity
	 *
	 */
	public List<MembersGen> getMembersGen();


	/**
	 * Save an existing Member entity
	 *
	 */
	public void saveMemberGen(MembersGen membergen);

	/**
	 * Delete an existing Member entity
	 *
	 */
	public void deleteMember(MembersGen member_1);
}
