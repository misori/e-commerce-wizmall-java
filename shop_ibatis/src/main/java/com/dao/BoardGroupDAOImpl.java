package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.BoardGroup;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("BoardGroupDAO")
@Transactional
public class BoardGroupDAOImpl extends SqlMapClientDaoSupport implements BoardGroupDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }



	public BoardGroup getBoardGroupByTid(Integer tid)
			throws DataAccessException {
		BoardGroup boardGroup = (BoardGroup)getSqlMapClientTemplate().queryForObject("getBoardGroupByTid", tid);
		return boardGroup;
	}

	@SuppressWarnings("unchecked")
	public List<BoardGroup> getBoardGroupList() throws DataAccessException {
		List<BoardGroup> boardGroup = getSqlMapClientTemplate().queryForList("getBoardGroupList");
        return boardGroup;
	}

	public void deleteBoardGroup(int tid) {
		getSqlMapClientTemplate().delete("deleteBoardGroup", tid);

	}

	public void saveBoardGroup(BoardGroup boardGroup) {
		getSqlMapClientTemplate().insert("saveBoardGroup", boardGroup);

	}

	public void updateBoardGroup(BoardGroup boardGroup) {
		getSqlMapClientTemplate().update("updateBoardGroup", boardGroup);

	}

}
