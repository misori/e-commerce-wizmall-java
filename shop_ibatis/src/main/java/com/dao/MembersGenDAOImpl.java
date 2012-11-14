package com.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.MembersGen;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
*
* @author Pondol
*
*/
@Repository("MembersGenDAO")
@Transactional
public class MembersGenDAOImpl extends SqlMapClientDaoSupport implements MembersGenDAO {

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
	public List<MembersGen> getAllMembersGen(HashMap<String, String> params) throws DataAccessException {
		List<MembersGen> membersGen = getSqlMapClientTemplate().queryForList("getMembersGenList", params);
        return membersGen;
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

	public void saveMembers(MembersGen memberGen) {
		getSqlMapClientTemplate().insert("saveMemberGen", memberGen);
	}

	public void updateMembers(MembersGen memberGen) {
		getSqlMapClientTemplate().update("updateMemberGen", memberGen);
	}

	/**
	 * 아래 3개 방식종 user_id를 이용하여 삭제하는 방법을 주로 이용
	 */
	public void deleteMembers(int tid) {
		getSqlMapClientTemplate().delete("deleteMemberGenByTid", tid);
	}

	public void deleteMembers(MembersGen memberGen) {
		getSqlMapClientTemplate().delete("deleteMemberGen", memberGen);
	}

	public void deleteMembers(String user_id) {
		System.out.println("MembersGenDAOImpl: deleteMembers:"+user_id);
		getSqlMapClientTemplate().delete("deleteMemberGenByUserId", user_id);

	}





}
