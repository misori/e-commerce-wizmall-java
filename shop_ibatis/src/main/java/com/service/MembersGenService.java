package com.service;

import java.util.HashMap;
import java.util.List;

import com.domain.MembersGen;

/**
 *
 * @author Pondol
 *
 */
public interface MembersGenService {

	/**
	 * Load an existing Member entity
	 *
	 */
	public List<MembersGen> getMembersGen(HashMap<String, String> params);


	/**
	 * Save an existing Member entity
	 *
	 */
	public void saveMemberGen(MembersGen membergen);

	/**
	 * Delete an existing Member entity
	 *
	 */
	public void deleteMembers(MembersGen membergen);
	public void deleteMembers(int tid);
	public void deleteMembers(String user_id);
}
