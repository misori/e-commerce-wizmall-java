package com.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Members;

/**
 * Spring service that handles CRUD requests for Member entities
 *	@author Pondol
 */
public interface MembersService {
	/**
	 * Load an existing Member entity
	 *
	 */
	public List<Members> getMembers();

	/**
	 * JPQL Query - getMemberByUserid
	 *
	 */
	public Members getMemberByUserid(String user_id) throws DataAccessException;
	//public Members getMemberByUserid(String user_id, int startResult, int maxRows) throws DataAccessException;

	/**
	 *JPQL Query - getMemberByUsername
	 */
	public Members getMemberByUsername(String user_name) throws DataAccessException;

	/**
	 * 총 회원 수 구하기
	 */
	public Integer getMembersCount() throws DataAccessException;
	public Integer getMembersCount(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 전체 회원 리스트를 가져온다.
	 * @return
	 * @throws DataAccessException
	 *
	 */
	public List<Members> getMembersList() throws DataAccessException;
	public List<Members> getMembersList(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 현재 회원의 포인트 구하기
	 * @return
	 * @throws DataAccessException
	 */
	public Integer getMemberPointByUserid(String user_id) throws DataAccessException;

	/**
	 * Save an existing Member entity
	 *
	 */
	public void saveMember(Members member);

	public void updateMember(Members member);

	/**
	 * Delete an existing Member entity
	 *
	 */
	public void deleteMembers(Members member);
	public void deleteMembers(int tid);
	public void deleteMembers(String user_id);

	/**
	 * 로그인 정보 업데이트
	 */
	public void updateLogin(Members member);
}
