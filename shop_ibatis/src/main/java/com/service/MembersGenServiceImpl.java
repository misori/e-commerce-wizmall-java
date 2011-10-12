package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MembersGenDAO;
import com.domain.MembersGen;

@Service("MembersGenService")
@Transactional
public class MembersGenServiceImpl implements MembersGenService {

	/**
	 * Load an existing Member entity
	 *
	 */

	@Autowired
	private MembersGenDAO membersgenDAO;


	/**
	 * Instantiates a new MemberServiceImpl.
	 *
	 */
	public MembersGenServiceImpl() {
	}


	@Transactional
	public List<MembersGen> getMembersGen() {
		return membersgenDAO.getAllMembersGen();
	}


	public void deleteMember(MembersGen member) {
		// TODO Auto-generated method stub

	}

	public void saveMemberGen(MembersGen membergen) {
		// TODO Auto-generated method stub
		MembersGen is_member = membersgenDAO.getMembersGenByUserid(membergen.getUser_id());
		System.out.println("getMembersGenByUserid:"+membergen.getUser_id());
		if (is_member != null) {
			membersgenDAO.updateMembers(membergen);
		} else {
			membersgenDAO.saveMembers(membergen);

		}

	}

}