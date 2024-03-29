package com.service;

import java.util.Date;
import java.util.List;

import com.dao.VisitRefererDAO;
import com.domain.VisitReferer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("VisitRefererService")
@Transactional
public class VisitRefererServiceImpl implements VisitRefererService{

	@Autowired
	private VisitRefererDAO visitRefererDAO;



	public VisitReferer getVisitRefererByTid(Integer tid)
			throws DataAccessException {
		return visitRefererDAO.getVisitRefererByTid(tid);
	}

	public List<VisitReferer> getVisitRefererList() throws DataAccessException {
		// TODO Auto-generated method stub
		return visitRefererDAO.getVisitRefererList();
	}

	public VisitReferer getVisitReferer(VisitReferer visitReferer)
			throws DataAccessException {
		return visitRefererDAO.getVisitReferer(visitReferer);
	}


	public VisitReferer countVisitRefererByHour(String hour)
			throws DataAccessException {
		return visitRefererDAO.countVisitRefererByHour(hour);
	}

	public VisitReferer countVisitRefererByDay(String day)
			throws DataAccessException {
		return visitRefererDAO.countVisitRefererByDay(day);
	}

	public VisitReferer countVisitRefererByMonth(String month)
			throws DataAccessException {
		return visitRefererDAO.countVisitRefererByMonth(month);
	}

	public void saveVisitReferer(VisitReferer visitReferer) {
		VisitReferer is_visitReferer	= visitRefererDAO.getVisitRefererByTid(visitReferer.getTid());

		if (is_visitReferer != null) {
			visitRefererDAO.updateVisitReferer(visitReferer);
		} else {
			visitRefererDAO.saveVisitReferer(visitReferer);
		}

	}

	public void deleteVisitReferer(int tid) {
		visitRefererDAO.deleteVisitReferer(tid);

	}

	public void updateVisitReferer(VisitReferer visitReferer) {
		// TODO Auto-generated method stub

	}





}
