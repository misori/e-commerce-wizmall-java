package com.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.BoardFile;

public interface BoardFileDAO {
	/**
	 *
	 * @param params : pid, bid, gid 가 포함되어야 한다.
	 * @return
	 * @throws DataAccessException
	 */
	public List<BoardFile> getBoardFileByPid(HashMap<String, String> params) throws DataAccessException;
	public BoardFile getBoardFileByTid(Integer tid) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveBoardFile(BoardFile boardFile);

	/**
	 * 데이타를 업데이트
	 */
	public void updateBoardFile(BoardFile boardFile);

	/**
	 * 데이타를 삭제한다.
	 */
	public void deleteBoardFile(int tid);
	public void deleteBoardFileByPid(HashMap<String, String> params);
}
