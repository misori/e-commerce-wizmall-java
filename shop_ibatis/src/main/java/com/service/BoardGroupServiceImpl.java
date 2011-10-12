package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.dao.BoardGroupDAO;
import com.domain.BoardGroup;

public class BoardGroupServiceImpl implements BoardGroupService{

	@Autowired
	private BoardGroupDAO boardGroupDAO;

	public BoardGroup getBoardGroupByTid(Integer tid)
			throws DataAccessException {
		return boardGroupDAO.getBoardGroupByTid(tid);
	}

	public List<BoardGroup> getBoardGroupList() throws DataAccessException {
		return boardGroupDAO.getBoardGroupList();
	}

	public void deleteBoardGroup(int tid) {
		boardGroupDAO.deleteBoardGroup(tid);

	}

	public void saveBoardGroup(BoardGroup boardGroup) {

		BoardGroup is_boardMain	= boardGroupDAO.getBoardGroupByTid(boardGroup.getTid());

		if (is_boardMain != null) {
			boardGroupDAO.updateBoardGroup(boardGroup);
		} else {
			boardGroupDAO.saveBoardGroup(boardGroup);
		}

	}

	public void updateBoardGroup(BoardGroup boardGroup) {
		boardGroupDAO.updateBoardGroup(boardGroup);

	}

}
