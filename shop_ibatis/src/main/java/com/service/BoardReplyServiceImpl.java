package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BoardReplyDAO;
import com.domain.BoardReply;

/**
 *
 * @author Pondol
 *
 */
@Service("BoardReplyService")
@Transactional
public class BoardReplyServiceImpl implements BoardReplyService {

	@Autowired
	private BoardReplyDAO boardReplyDAO;


	public BoardReplyServiceImpl() {
	}

	public BoardReply getBoardReplyByPrimaryKey(HashMap<String, String> params)
		throws DataAccessException {
		// TODO Auto-generated method stub
		return boardReplyDAO.getBoardReplyByPrimaryKey(params);
	}

	public Integer getBoardReplyCount(HashMap<String, String> params)
		throws DataAccessException {
		// TODO Auto-generated method stub
		return boardReplyDAO.getBoardReplyCount(params);
	}

	public List<BoardReply> getBoardReplyList(HashMap<String, String> params)
		throws DataAccessException {
		// TODO Auto-generated method stub
		return boardReplyDAO.getBoardReplyList(params);
	}

	public void deleteBoardReply(HashMap<String, String> params) {
		boardReplyDAO.deleteBoardReply(params);

	}

	public void saveBoardReply(BoardReply boardReply) {
		//BoardReply is_boardReply	= boardReplyDAO.getBoardReplyByPrimaryKey(params).getBannerByTid(boardReply.getTid());

		//if (is_banner != null) {
			//boardReplyDAO.updateBoardReply(boardReply);
		//} else {
			//boardReplyDAO.saveBoardReply(boardReply);
		//}

		boardReplyDAO.saveBoardReply(boardReply);

	}

	public void updateBoardReply(BoardReply boardReply) {
		boardReplyDAO.updateBoardReply(boardReply);

	}


}
