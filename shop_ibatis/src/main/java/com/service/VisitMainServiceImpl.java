package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.VisitMainDAO;
import com.domain.VisitMain;

@Service("VisitMainService")
@Transactional
public class VisitMainServiceImpl implements VisitMainService{

	@Autowired
	private VisitMainDAO visitMainDAO ;



	public VisitMain getVisitMainByTid(Integer tid) throws DataAccessException {
		return visitMainDAO.getVisitMainByTid(tid);
	}

	public List<VisitMain> getVisitMainList() throws DataAccessException {
		// TODO Auto-generated method stub
		return visitMainDAO.getVisitMainList();
	}

	public VisitMain getVistMainByDate(Date date) throws DataAccessException{
		// TODO Auto-generated method stub
		return visitMainDAO.getVistMainByDate(date);
	}

	public void saveVisitMain(VisitMain visitMain) {
		VisitMain is_visitMain	= visitMainDAO.getVisitMainByTid(visitMain.getTid());

		if (is_visitMain != null) {
			visitMainDAO.updateVisitMain(visitMain);
		} else {
			visitMainDAO.saveVisitMain(visitMain);
		}

	}

	public void updateVisitMain(VisitMain visitMain) {
		visitMainDAO.updateVisitMain(visitMain);

	}

	public void deleteVisitMain(int tid) {
		visitMainDAO.deleteVisitMain(tid);

	}
}
