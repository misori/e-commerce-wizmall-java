package com.dao;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Board;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 *
 * @author Pondol
 *
 */
@Repository("BoardDAO")
@Transactional
public class BoardDAOImpl extends SqlMapClientDaoSupport implements BoardDAO {//BoardDAO(interface) 이므로 이 값들을 상속 받아야 한다.

	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Board> getBoardList(HashMap<String, String> params) throws DataAccessException {
		List<Board> board = getSqlMapClientTemplate().queryForList("getBoardList", params);
        return board;
	}



	/**
	 * (non-Javadoc)
	 * 현재 보드의 총 카운트 구해오기
	 */

	public Integer getBoardCount(HashMap<String, String> params)  throws DataAccessException {
		//Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("getBoardCount", params)).intValue();
		Object result	= (Integer)super.getSqlMapClientTemplate().queryForObject("getBoardCount", params);
		Integer cnt = result == null ? 0 : (Integer)result;
        return cnt;
	}





	/**
	 * (non-Javadoc)
	 * @see com.dao.BoardDAO#getBoardByPrimaryKey(java.lang.Integer)
	 */
	public Board getBoardByPrimaryKey(HashMap<String, String> params) throws DataAccessException {

		Board board = (Board)getSqlMapClientTemplate().queryForObject("getBoardByPrimaryKey", params);
		//System.out.println(params);
		return board;

	}


	/**
	 * get Max Fid
	 */
	public Integer getMaxFid(HashMap<String, String> params) throws DataAccessException {
		//return ((Integer)super.getSqlMapClientTemplate().queryForObject("getBoardMaxFid", params)).intValue();
		Object result	= (Integer)super.getSqlMapClientTemplate().queryForObject("getBoardMaxFid", params);
		Integer cnt = result == null ? 0 : (Integer)result;
        return cnt;
	}

	/**
	 * get Max TID
	 */
	public Integer getMaxTid(HashMap<String, String> params)
		throws DataAccessException {
		//return ((Integer)super.getSqlMapClientTemplate().queryForObject("getBoardMaxTid", params)).intValue();
		Object result	= (Integer)super.getSqlMapClientTemplate().queryForObject("getBoardMaxTid", params);
		Integer cnt = result == null ? 0 : (Integer)result;
        return cnt;
	}


	/**
	 * get Thread
	 */
	public String getBoardThread(HashMap<String, String> params) throws DataAccessException {
		String fid	= (String)super.getSqlMapClientTemplate().queryForObject("getBoardThread", params);
		return fid;
	}


	public void updateBoardViewCount(HashMap<String, String> params) throws DataAccessException {
		//System.out.println("updateBoardViewCount:"+params);
		getSqlMapClientTemplate().update("updateBoardViewCount", params);
	}

	public void updateCategoryInBoard(HashMap<String, String> params)
			throws DataAccessException {
		getSqlMapClientTemplate().update("updateCategoryInBoard", params);

	}

	public void saveBoard(Board board) {
		getSqlMapClientTemplate().insert("saveBoard", board);

	}


	public void updateBoard(Board board) {
		getSqlMapClientTemplate().update("updateBoard", board);
	}


	public void deleteBoard(HashMap<String, String> params) {
		getSqlMapClientTemplate().delete("deleteContents", params);

	}

	public void createBoardTable(HashMap<String, String> params) {
		super.getSqlMapClientTemplate().queryForObject("createBoardTable", params);
		super.getSqlMapClientTemplate().queryForObject("createBoardReplyTable", params);
	}

}
