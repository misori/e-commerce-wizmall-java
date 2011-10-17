package com.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.BoardMain;

/**
 *
 * @author Pondol
 *
 */
public interface BoardMainDAO {
	public List<BoardMain> getBoardMainList(HashMap<String, String> params) throws DataAccessException;
	public List<BoardMain> getBoardMainJoinBoardGroupList() throws DataAccessException;
	public BoardMain getBoardMainByTid(Integer tid) throws DataAccessException;
	public Integer getBoardMainCount(HashMap<String, String> params);

	/**
	 * 데이타를 저장
	 */
	public void saveBoardMain(BoardMain boardMain);

	/**
	 * 데이타를 업데이트
	 */
	public void updateBoardMain(BoardMain boardMain);

	/**
	 * 데이타를 삭제한다.
	 */
	public void deleteBoardMain(int tid);
}
