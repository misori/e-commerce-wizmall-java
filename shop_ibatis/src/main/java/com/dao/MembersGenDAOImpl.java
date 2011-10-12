package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Members;
import com.domain.MembersGen;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("MembersGenDAO")
@Transactional
public class MembersGenDAOImpl extends SqlMapClientDaoSupport implements MembersGenDAO {

	@SuppressWarnings("restriction")
	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }



	/**
	 * JPQL Query - getAllMembersGen
	 *
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MembersGen> getAllMembersGen() throws DataAccessException {

		return null;
	}


	/**
	 * JPQL Query - getMembersGenByUserid
	 *
	 */
	public MembersGen getMembersGenByUserid(String userId)
			throws DataAccessException {
		MembersGen membergen = (MembersGen)getSqlMapClientTemplate().queryForObject("getMembersGenByUserid", userId);
		return membergen;
	}



	public void deleteMembers(int tid) {
		getSqlMapClientTemplate().delete("deleteMemberGen", tid);

	}



	public void saveMembers(MembersGen memberGen) {
		getSqlMapClientTemplate().insert("saveMemberGen", memberGen);

	}



	public void updateMembers(MembersGen memberGen) {
		getSqlMapClientTemplate().update("updateMemberGen", memberGen);

	}





}
