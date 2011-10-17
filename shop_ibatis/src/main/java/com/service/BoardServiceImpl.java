package com.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BoardDAO;
import com.domain.Board;
import com.util.Constants;
import com.util.file.FileManage;

/**
 *
 * @author Pondol
 *
 */
@Service("BoardService")
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;


	public BoardServiceImpl() {
	}


	@Transactional
	public List<Board> getBoardList(HashMap<String, String> params) {
		return boardDAO.getBoardList(params);
	}

	@Transactional
	public Integer getAllBoardCount(HashMap<String, String> params){
		return boardDAO.getBoardCount(params);
	}
	@Transactional
	public Integer getBoardCount(HashMap<String, String> params){
		return boardDAO.getBoardCount(params);
	}
	@Transactional
	public void saveBoard(Board board) {

/*
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		date = cal.getTime();
		board.setW_date(cal.getTime());
*/

		//System.currentTimeMillis();


		if (board.getTid() != null) {//수정
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("bid",board.getBid());
			params.put("gid",board.getGid());
			params.put("tid",Integer.toString(board.getTid()));
			Board is_board = boardDAO.getBoardByPrimaryKey(params);
			is_board.setTid(board.getTid());
			is_board.setUser_id(board.getUser_id());
			is_board.setUser_name(board.getUser_name());
			is_board.setUser_passwd(board.getUser_passwd());
			is_board.setSubject(board.getSubject());
			is_board.setContents(board.getContents());

			is_board.setBid(board.getBid());
			is_board.setGid(board.getGid());

			boardDAO.updateBoard(is_board);
		} else {//입력
			Date date = new Date();

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			date = cal.getTime();
			//System.out.println("cal.getTime():"+cal.getTime());
			board.setW_date(cal.getTime());
			board.setCount(0);
			boardDAO.saveBoard(board);
		}



	}

	/*
	 * auto_increment 속성이 있을 경우는 위의 방식대로 처리해도 문제가 없어 보임
	 *
	@Transactional
	public void saveBoard(Board board) {
		Board is_board = boardDAO.getBoardByPrimaryKey(board.getTid());

		if (is_board != null) {
			is_board.setTid(board.getTid());
			is_board.setUser_id(board.getUser_id());
			is_board.setUser_name(board.getUser_name());
			is_board.setUser_passwd(board.getUser_passwd());
			is_board.setSubject(board.getSubject());
			is_board.setContents(board.getContents());
			board = boardDAO.store(is_board);
		} else {
			board = boardDAO.store(board);
		}
		boardDAO.flush();
	}
	*/

	/**
	 * Delete an existing Board entity
	 *
	 */
	public void deleteBoard(HashMap<String, String> params) {
		// 1. 테이블로 부터 자료 삭제
		boardDAO.deleteBoard(params);
		// 2. 첨부된 파일 지우기
		System.out.println("deleteBoard Start");
		FileManage filemanage	= new FileManage();//파일매니저 set
		String updirpath	= "board/table/"+params.get("gid")+"/"+params.get("bid")+"/updir/"+params.get("tid")+"/";
		String AbsolutePath	= Constants.AbsolutePath+updirpath;
		System.out.println(AbsolutePath);
		filemanage.DeleteDir(AbsolutePath);
	}
	/**
	 * Add View Count
	 *
	 */
	public void updateBoardViewCount(HashMap<String, String> params){
		boardDAO.updateBoardViewCount(params);
		//board.setCount(board.getCount()+1);
	}


	public void updateCategoryInBoard(HashMap<String, String> params)
			throws DataAccessException {
		boardDAO.updateCategoryInBoard(params);

	}


	public Integer getMaxFid(HashMap<String, String> params){
		Integer max = boardDAO.getMaxFid(params);
		return max;
	}

	public Integer getMaxTid(HashMap<String, String> params)
	throws DataAccessException {
		Integer max = boardDAO.getMaxTid(params);
		return max;
	}


	public String getBoardThread(HashMap<String, String> params){
		String thread = boardDAO.getBoardThread(params);
		return thread;
	}

	public Board getBoardByPrimaryKey(HashMap<String, String> params) throws DataAccessException {
		// TODO Auto-generated method stub
		Board board = boardDAO.getBoardByPrimaryKey(params);
		//System.out.println(params);
		return board;
	}


	public void createBoard(HashMap<String, String> params) {
		boardDAO.createBoardTable(params);

	}






}
