package com.service;

import java.util.HashMap;
import java.util.List;

import com.dao.BoardCategoryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.BoardCategory;

@Service("BoardCategoryService")
@Transactional
public class BoardCategoryServiceImpl implements BoardCategoryService{

	@Autowired
	private BoardCategoryDAO boardcategoryDAO;


	public List<BoardCategory> getAllBoardCategoryList()
			throws DataAccessException {
		// TODO Auto-generated method stub
		return boardcategoryDAO.getAllBoardCategoryList();
	}

	public List<BoardCategory> getBoardCategoryList(
			HashMap<String, String> params) throws DataAccessException {
		// TODO Auto-generated method stub
		return boardcategoryDAO.getBoardCategoryList(params);
	}

	public BoardCategory getBoardCategoryByTid(Integer tid)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return boardcategoryDAO.getBoardCategoryByTid(tid);
	}
	public Integer getBoardCategoryMaxOrderNum(HashMap<String, String> params)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return boardcategoryDAO.getBoardCategoryMaxOrderNum(params);
	}

	public void saveBoardCategory(BoardCategory boardCategory) {
		boardcategoryDAO.saveBoardCategory(boardCategory);

	}

	public void updateBoardCategory(BoardCategory boardCategory) {
		boardcategoryDAO.updateBoardCategory(boardCategory);

	}

	public void deleteBoardCategory(int tid) {
		boardcategoryDAO.deleteBoardCategory(tid);

	}




}
