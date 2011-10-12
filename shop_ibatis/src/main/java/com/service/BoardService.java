package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Board;

public interface BoardService {

	public List<Board> getBoardList(HashMap<String, String> params);

	/**
	 * 전체 게시물 수를 가져온다.
	 */
	public Integer getAllBoardCount(HashMap<String, String> params);

	public Integer getBoardCount(HashMap<String, String> params);
	/**
	 * 상세보기 페이지를 가져온다.
	 * @param tid
	 * @return
	 * @throws DataAccessException
	 */
	public Board getBoardByPrimaryKey(HashMap<String, String> params) throws DataAccessException;

	/**
	 * max FID를 가져온다.
	 */
	public Integer getMaxFid(HashMap<String, String> params);

	/**
	 * get Max TID
	 */
	public Integer getMaxTid(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 현재 THREAD 값을 가져온다.
	 */
	public String getBoardThread(HashMap<String, String> params);

	/**
	 * view count를 올린다.
	 */
	public void updateBoardViewCount(HashMap<String, String> params);

	/**
	 * 카테고리를 변경한다.
	 */
	public void updateCategoryInBoard(HashMap<String, String> params) throws DataAccessException;

	/**
	 * form 을 통해 넘어온 데이타를 저장
	 */
	public void saveBoard(Board board);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteBoard(HashMap<String, String> params);

	/**
	 * 테이블을 생성한다.
	 * @param params
	 */
	public void createBoard(HashMap<String, String> params);

}
