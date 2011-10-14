package com.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Members;

/**
 *
 * @author Pondol
 *
 */
public interface MembersDAO {
	/**
	 * JPQL Query - getAllMembers
	 *
	 */
	public List<Members> getMembersList() throws DataAccessException;
	public List<Members> getMembersList(HashMap<String, String> params) throws DataAccessException;

	/**
	 * JPQL Query - getMemberByPrimaryKey
	 *
	 */
	public Members getMemberByPrimaryKey(Integer tid) throws DataAccessException;

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
	 * 현재 회원의 포인트 구하기
	 * @return
	 * @throws DataAccessException
	 */
	public Integer getMemberPointByUserid(String user_id) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveMembers(Members member);

	/**
	 * 데이타를 업데이트
	 */
	public void updateMembers(Members member);


	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteMembers(Members member);
	public void deleteMembers(int tid);
	public void deleteMembers(String user_id);

}
