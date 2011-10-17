package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MembersDAO;
import com.dao.PointDAO;
import com.domain.Members;
import com.domain.Point;

/**
 *
 * @author Pondol
 *
 */
@Service("PointService")
@Transactional
public class PointServiceImpl implements PointService{

	@Autowired
	private PointDAO pointDAO;

	@Autowired
	private MembersDAO membersDAO;

	public void deletePoint(Point point) {
		// TODO Auto-generated method stub

	}

	public void savePoint(Point point) {
		// TODO Auto-generated method stub

	}

	public void updatePoint() {
		// TODO Auto-generated method stub

	}

	public void updatePointTable() {
		// TODO Auto-generated method stub

	}

	public void insertPoint(Point point) {
		// TODO Auto-generated method stub
		//회원 필드도 동시에 업데이트 한다.
		if(point.getFlag() == 0){//즉시실행인경우
			Members m	= membersDAO.getMemberByUserid(point.getUser_id());
			m.setUser_point(m.getUser_point() + point.getPoint());
		}

	}

}
