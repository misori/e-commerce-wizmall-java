package com.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Board;

/**
 *
 * @author Pondol
 *
 */
public interface BoardDAO {

	public List<Board> getBoardList(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 상세보기 가져오기
	 *
	 */
	public Board getBoardByPrimaryKey(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 보드 총게시물 수 구하기
	 */
	public Integer getBoardCount(HashMap<String, String> params) throws DataAccessException;

	/**
	 * get Max Fid
	 */
	public Integer getMaxFid(HashMap<String, String> params) throws DataAccessException;

	/**
	 * get Max TID
	 */
	public Integer getMaxTid(HashMap<String, String> params) throws DataAccessException;

	/**
	 * get Thread
	 */
	public String getBoardThread(HashMap<String, String> params) throws DataAccessException;

	/**
	 * view count를 올린다.
	 */
	public void updateBoardViewCount(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 카테고리를 변경한다.
	 */
	public void updateCategoryInBoard(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveBoard(Board board);

	/**
	 * 데이타를 업데이트
	 */
	public void updateBoard(Board board);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteBoard(HashMap<String, String> params);

	/**
	 * 테이블을 생성한다.
	 * @param params
	 */
	public void createBoardTable(HashMap<String, String> params);

}
