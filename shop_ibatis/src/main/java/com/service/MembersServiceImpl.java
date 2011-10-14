package com.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MembersDAO;
import com.domain.Members;
import com.util.TimeUtil;

/**
 * Spring service that handles CRUD requests for Member entities
 *
 */

@Service("MembersService")
@Transactional
public class MembersServiceImpl implements MembersService {
	//private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	/**
	 * DAO injected by Spring that manages Member entities
	 *
	 */
	@Autowired
	private MembersDAO membersDAO;

	/**
	 * Instantiates a new MemberServiceImpl.
	 *
	 */
	public MembersServiceImpl() {
	}

	/**
	 * Load an existing Member entity
	 * @deprecated
	 */
	@Transactional
	public List<Members> getMembers() {
		return null;
	}

	@Transactional
	public void saveMember(Members member) {
		// TODO Auto-generated method stub
		//Members is_member = membersDAO.getMemberByPrimaryKey(member.getTid());
		Members is_member = membersDAO.getMemberByUserid(member.getUser_id());
		//logger.info("Welcome /saveMember");
		//System.out.println("getMemberByUserid:"+member.getUser_id());
		if (is_member != null) {//수정
			is_member.setTid(is_member.getTid());//어떤식으로던 고유값을 할당해야 한다.
			is_member.setUser_id(member.getUser_id());
			is_member.setUser_passwd(member.getUser_passwd());
			is_member.setUser_name(member.getUser_name());
			is_member.setUser_email(member.getUser_email());
			membersDAO.updateMembers(is_member);
			//membersDAO.
		} else {//입력
			membersDAO.saveMembers(member);
		}
	}
	public void updateLogin(Members member){

		 try {

			TimeUtil tutil	= new TimeUtil();
			InetAddress ip = InetAddress.getLocalHost();
			String user_ip	= ip.getHostAddress();

			member.setUser_login_ip(user_ip);//아이피
			member.setUser_login_date(tutil.mkCurrentData());//로그인시간
			member.setUser_login_num(member.getUser_login_num()+1);//로그인 횟수
			membersDAO.updateMembers(member);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public Members getMemberByUserid(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		//System.out.println("getMemberByUserid:userId:"+userId);
		Members members = membersDAO.getMemberByUserid(userId);
		//System.out.println("members:"+members);
		return members;
	}

	public Members getMemberByUsername(String userName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getMembersCount(HashMap<String, String> params) throws DataAccessException {
		// TODO Auto-generated method stub
		return membersDAO.getMembersCount(params);
	}

	public Integer getMembersCount()
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated
	 */
	public List<Members> getMembersList() throws DataAccessException {
		// TODO Auto-generated method stub
		return membersDAO.getMembersList();
	}

	public List<Members> getMembersList(HashMap<String, String> params)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return membersDAO.getMembersList(params);
	}

	public Integer getMemberPointByUserid(String userId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return membersDAO.getMemberPointByUserid(userId);
	}
	/**
	 * Delete an existing Member entity
	 *
	 */
	@Transactional
	public void deleteMembers(Members member) {
		membersDAO.deleteMembers(member);
	}
	public void deleteMembers(int tid) {
		membersDAO.deleteMembers(tid);
	}
	public void deleteMembers(String userId) {
		membersDAO.deleteMembers(userId);

	}

}
