package com.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.BoardReply;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("BoardReplyDAO")
@Transactional
public class BoardReplyDAOImpl extends SqlMapClientDaoSupport implements BoardReplyDAO {

	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	public BoardReply getBoardReplyByPrimaryKey(HashMap<String, String> params)
			throws DataAccessException {
		BoardReply boardReply = (BoardReply)getSqlMapClientTemplate().queryForObject("getBoardReplyByPrimaryKey", params);
		//System.out.println(params);
		return boardReply;
	}

	public Integer getBoardReplyCount(HashMap<String, String> params)
			throws DataAccessException {
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("getBoardReplyCount", params)).intValue();
        return cnt;
	}

	@SuppressWarnings("unchecked")
	public List<BoardReply> getBoardReplyList(HashMap<String, String> params)
			throws DataAccessException {
		List<BoardReply> boardReply = getSqlMapClientTemplate().queryForList("getBoardReplyList", params);
        return boardReply;
	}

	public void saveBoardReply(BoardReply boardReply) {
		System.out.println("DAOImpl saveBoardReply");
		getSqlMapClientTemplate().insert("saveBoardReply", boardReply);

	}

	public void updateBoardReply(BoardReply boardReply) {
		getSqlMapClientTemplate().update("updateBoardReply", boardReply);

	}

	public void deleteBoardReply(HashMap<String, String> params) {
		getSqlMapClientTemplate().delete("deleteBoardReply", params);

	}
}
