package com.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.BoardFile;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("BoardFileDAO")
@Transactional
public class BoardFileDAOImpl extends SqlMapClientDaoSupport implements BoardFileDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	@SuppressWarnings("unchecked")
	public List<BoardFile> getBoardFileByPid(HashMap<String, String> params)
			throws DataAccessException {
		List<BoardFile> boardFile = getSqlMapClientTemplate().queryForList("getBoardFileByPid", params);
        return boardFile;
	}


	public BoardFile getBoardFileByTid(Integer tid) throws DataAccessException {
		BoardFile boardMain = (BoardFile)getSqlMapClientTemplate().queryForObject("getBoardFileByTid", tid);
		return boardMain;
	}

	public void deleteBoardFile(int tid) {
		getSqlMapClientTemplate().delete("deleteBoardFile", tid);

	}
	public void deleteBoardFileByPid(HashMap<String, String> params) {
		getSqlMapClientTemplate().delete("deleteBoardFileByPid", params);
	}

	public void saveBoardFile(BoardFile boardFile) {
		getSqlMapClientTemplate().insert("saveBoardFile", boardFile);

	}

	public void updateBoardFile(BoardFile boardFile) {
		getSqlMapClientTemplate().update("updateBoardFile", boardFile);

	}



}
