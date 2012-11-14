package com.dao;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Members;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * DAO to manage Members entities.
 * @author Pondol
 */
@Repository("MembersDAO")
@Transactional
public class MembersDAOImpl extends SqlMapClientDaoSupport implements MembersDAO {

	@Resource(name = "sqlMapClient")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

	/**
	 * Instantiates a new MembersDAOImpl
	 *
	 */
	public MembersDAOImpl() {
		super();
	}





	/**
	 * JPQL Query - findAllMembers
	 *@deprecated
	 */
	@Transactional
	public List<Members> getMembersList() throws DataAccessException {
		return null;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Members> getMembersList(HashMap<String, String> params) throws DataAccessException {
		//System.out.println("getMembersList:"+params);
		List<Members> members = getSqlMapClientTemplate().queryForList("getMembersList", params);

        return members;
	}

	/**
	 * JPQL Query - getMemberByPrimaryKey
	 *
	 */
	@Transactional
	public Members getMemberByPrimaryKey(Integer tid) throws DataAccessException {
		Members member = (Members)getSqlMapClientTemplate().queryForObject("getMemberByPrimaryKey", tid);
		return member;
	}

	/**
	 * JPQL Query - getMemberByPrimaryKey
	 *
	 */
	@Transactional
	public Members getMemberByUserid(String user_id) throws DataAccessException {
		Members member = (Members)getSqlMapClientTemplate().queryForObject("getMemberByUserid", user_id);
		return member;
	}

	/**
	 * JPQL Query - getMemberByUsername
	 */
	@Transactional
	public Members getMemberByUsername(String user_name) throws DataAccessException {
		return null;
	}


	/**
	 * 회원멤버수 구하기
	 */
	public Integer getMembersCount() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getMembersCount(HashMap<String, String> params)
			throws DataAccessException {
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("getMembersCount", params));//.intValue()
        return cnt;
	}

	/**
	 * 회원 포인트 구하기
	 */
	public Integer getMemberPointByUserid(String user_id) throws DataAccessException {
		Integer cnt = ((Integer)super.getSqlMapClientTemplate().queryForObject("getMemberPointByUserid", user_id)).intValue();
        return cnt;
	}

	public void deleteMembers(int tid) {
		getSqlMapClientTemplate().delete("deleteMembersByUserTid", tid);

	}
	public void deleteMembers(String user_id) {
		getSqlMapClientTemplate().delete("deleteMembersByUserId", user_id);
	}
	public void deleteMembers(Members member) {
		getSqlMapClientTemplate().delete("deleteMembers", member);

	}


	public void saveMembers(Members member) {
		getSqlMapClientTemplate().insert("saveMember", member);

	}

	public void updateMembers(Members member) {
		System.out.println("updateMembers:"+member.toString());
		getSqlMapClientTemplate().update("updateMember", member);

	}


}
