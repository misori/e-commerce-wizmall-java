package com.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.BoardCategory;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("BoardCategoryDAO")
@Transactional
public class BoardCategoryDAOImpl extends SqlMapClientDaoSupport implements BoardCategoryDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	@SuppressWarnings("unchecked")
	public List<BoardCategory> getAllBoardCategoryList()
			throws DataAccessException {
		List<BoardCategory> boardCategory = getSqlMapClientTemplate().queryForList("getAllBoardCategoryList");
        return boardCategory;
	}

	@SuppressWarnings("unchecked")
	public List<BoardCategory> getBoardCategoryList(
			HashMap<String, String> params) throws DataAccessException {
		List<BoardCategory> boardCategory = getSqlMapClientTemplate().queryForList("getBoardCategoryList", params);
        return boardCategory;
	}

	public BoardCategory getBoardCategoryByTid(Integer tid)
			throws DataAccessException {
		BoardCategory boardCategory = (BoardCategory)getSqlMapClientTemplate().queryForObject("getBoardCategoryByTid", tid);
		return boardCategory;
	}

	public Integer getBoardCategoryMaxOrderNum(HashMap<String, String> params)
			throws DataAccessException {
		// TODO Auto-generated method stub
		//System.out.println(params);
		//return 0;
		Object result = super.getSqlMapClientTemplate().queryForObject("getBoardCategoryMaxOrderNum", params);
		//Integer max_ordernum	= ((Integer)super.getSqlMapClientTemplate().queryForObject("getBoardCategoryMaxOrderNum", params)).intValue();
		Integer max_ordernum = result == null ? 0 : (Integer)result;
		return max_ordernum;
	}


	public void saveBoardCategory(BoardCategory boardCategory) {
		getSqlMapClientTemplate().insert("saveBoardCategory", boardCategory);
	}

	public void updateBoardCategory(BoardCategory boardCategory) {
		getSqlMapClientTemplate().update("updateBoardCategory", boardCategory);
	}

	public void deleteBoardCategory(int tid) {
		getSqlMapClientTemplate().delete("deleteBoardCategory", tid);
	}



}
