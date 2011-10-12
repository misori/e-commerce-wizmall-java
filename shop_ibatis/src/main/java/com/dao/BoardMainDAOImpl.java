package com.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.BoardMain;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("BoardMainDAO")
@Transactional
public class BoardMainDAOImpl extends SqlMapClientDaoSupport implements BoardMainDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	@SuppressWarnings("unchecked")
	public List<BoardMain> getBoardMainList(HashMap<String, String> params) throws DataAccessException {
		List<BoardMain> boardMain = getSqlMapClientTemplate().queryForList("getBoardMainList", params);
        return boardMain;
	}

	@SuppressWarnings("unchecked")
	public List<BoardMain> getBoardMainJoinBoardGroupList()
		throws DataAccessException {
		List<BoardMain> boardMain = getSqlMapClientTemplate().queryForList("getBoardMainJoinBoardGroupList");
        return boardMain;
	}

	public BoardMain getBoardMainByTid(Integer tid) throws DataAccessException {
		BoardMain boardMain = (BoardMain)getSqlMapClientTemplate().queryForObject("getBoardMainByTid", tid);
		return boardMain;
	}

	public Integer getBoardMainCount(HashMap<String, String> params) {
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("getBoardMainCount", params)).intValue();
        return cnt;
	}

	public void deleteBoardMain(int tid) {
		getSqlMapClientTemplate().delete("deleteBoardMain", tid);

	}

	public void saveBoardMain(BoardMain boardMain) {
		getSqlMapClientTemplate().insert("saveBoardMain", boardMain);

	}

	public void updateBoardMain(BoardMain boardMain) {
		getSqlMapClientTemplate().update("updateBoardMain", boardMain);

	}







}
