package com.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.BoardGroup;

public interface BoardGroupService {
	public List<BoardGroup> getBoardGroupList() throws DataAccessException;
	public BoardGroup getBoardGroupByTid(Integer tid) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveBoardGroup(BoardGroup boardGroup);

	/**
	 * 데이타를 업데이트
	 */
	public void updateBoardGroup(BoardGroup boardGroup);

	/**
	 * 데이타를 삭제한다.
	 */
	public void deleteBoardGroup(int tid);
}
