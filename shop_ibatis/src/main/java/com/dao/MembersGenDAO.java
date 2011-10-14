package com.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.MembersGen;


public interface MembersGenDAO  {
	public List<MembersGen> getAllMembersGen(HashMap<String, String> params) throws DataAccessException;

	public MembersGen getMembersGenByUserid(String user_id) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveMembers(MembersGen memberGen);

	/**
	 * 데이타를 업데이트
	 */
	public void updateMembers(MembersGen memberGen);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteMembers(MembersGen memberGen);
	public void deleteMembers(int tid);
	public void deleteMembers(String user_id);
}
