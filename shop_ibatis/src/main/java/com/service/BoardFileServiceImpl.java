package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BoardFileDAO;
import com.domain.BoardFile;

/**
 *
 * @author Pondol
 *
 */
@Service("BoardFileService")
@Transactional
public class BoardFileServiceImpl implements BoardFileService{

	@Autowired
	private BoardFileDAO boardfileDAO;



	public List<BoardFile> getBoardFileByPid(HashMap<String, String> params)
			throws DataAccessException {
		return boardfileDAO.getBoardFileByPid(params);
	}

	public BoardFile getBoardFileByTid(Integer tid) throws DataAccessException {
		return boardfileDAO.getBoardFileByTid(tid);
	}

	public void saveBoardFile(BoardFile boardFile) {

		BoardFile is_boardFile	= boardfileDAO.getBoardFileByTid(boardFile.getTid());

		if (is_boardFile != null) {
			boardfileDAO.updateBoardFile(boardFile);
		} else {
			boardfileDAO.saveBoardFile(boardFile);
		}


	}

	public void updateBoardFile(BoardFile boardFile) {
		boardfileDAO.updateBoardFile(boardFile);

	}

	public void deleteBoardFile(int tid) {
		boardfileDAO.deleteBoardFile(tid);
	}

	public void deleteBoardFileByPid(HashMap<String, String> params) {
		boardfileDAO.deleteBoardFileByPid(params);
	}


}
