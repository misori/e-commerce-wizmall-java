package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.BoardReply;

/**
 *
 * @author Pondol
 *
 */
public interface BoardReplyService {
	public List<BoardReply> getBoardReplyList(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 상세보기 가져오기
	 *
	 */
	public BoardReply getBoardReplyByPrimaryKey(HashMap<String, String> params) throws DataAccessException;

	/**
	 * 보드 총게시물 수 구하기
	 */
	public Integer getBoardReplyCount(HashMap<String, String> params) throws DataAccessException;


	/**
	 * 데이타를 저장
	 */
	public void saveBoardReply(BoardReply boardReply);

	/**
	 * 데이타를 업데이트
	 */
	public void updateBoardReply(BoardReply boardReply);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteBoardReply(HashMap<String, String> params);
}
