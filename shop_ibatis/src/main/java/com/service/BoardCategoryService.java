package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.BoardCategory;

public interface BoardCategoryService {
	public List<BoardCategory> getAllBoardCategoryList() throws DataAccessException;

	public List<BoardCategory> getBoardCategoryList(HashMap<String, String> params) throws DataAccessException;
	public BoardCategory getBoardCategoryByTid(Integer tid) throws DataAccessException;

	/**
	 * Max OrderNum 구하기
	 */
	public Integer getBoardCategoryMaxOrderNum(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveBoardCategory(BoardCategory boardCategory);

	/**
	 * 데이타를 업데이트
	 */
	public void updateBoardCategory(BoardCategory boardCategory);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteBoardCategory(int tid);
}
