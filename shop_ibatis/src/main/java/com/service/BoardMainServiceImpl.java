package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BoardMainDAO;
import com.domain.BoardMain;

/**
 *
 * @author Pondol
 *
 */
@Service("BoardMainService")
@Transactional
public class BoardMainServiceImpl implements BoardMainService{

	@Autowired
	private BoardMainDAO boardmainDAO;

	public BoardMain getBoardMainByTid(Integer tid) throws DataAccessException {
		return boardmainDAO.getBoardMainByTid(tid);
	}

	public List<BoardMain> getBoardMainList(HashMap<String, String> params) throws DataAccessException {
		return boardmainDAO.getBoardMainList(params);
	}

	public List<BoardMain> getBoardMainJoinBoardGroupList()
	throws DataAccessException {
		// TODO Auto-generated method stub
		return boardmainDAO.getBoardMainJoinBoardGroupList();
	}

	public void saveBoardMain(BoardMain boardMain) {

		BoardMain is_boardMain	= boardmainDAO.getBoardMainByTid(boardMain.getTid());

		if (is_boardMain != null) {
			boardmainDAO.updateBoardMain(boardMain);
		} else {
			boardmainDAO.saveBoardMain(boardMain);
		}

	}

	public Integer getBoardMainCount(HashMap<String, String> params) {
		return boardmainDAO.getBoardMainCount(params);
	}

	public void deleteBoardMain(int tid) {
		boardmainDAO.deleteBoardMain(tid);

	}



	public void updateBoardMain(BoardMain boardMain) {
		boardmainDAO.updateBoardMain(boardMain);

	}





}
