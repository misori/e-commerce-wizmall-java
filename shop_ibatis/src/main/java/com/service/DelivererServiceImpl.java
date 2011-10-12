package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DelivererDAO;
import com.domain.Deliverer;


@Service("DelivererService")
@Transactional
public class DelivererServiceImpl implements DelivererService{

	@Autowired
	private DelivererDAO delivererDAO;

	/**
	 * @deprecated
	 */
	public void deleteDeliverer(Deliverer deliverer) {
		// TODO Auto-generated method stub

	}

	public List<Deliverer> getAllDelivererList() {
		return delivererDAO.getAllDelivererList();
	}

	public void saveDeliverer(Deliverer deliverer) {
		Deliverer is_deliverer	= delivererDAO.getDelivererByTid(deliverer.getTid());

		if (is_deliverer != null) {
			delivererDAO.updateDeliverer(deliverer);
		} else {
			delivererDAO.saveDeliverer(deliverer);
		}

	}

	public void deleteDeliverer(Integer tid) {
		delivererDAO.deleteDeliverer(tid);

	}

	public Deliverer getDelivererByTid(Integer tid) {
		// TODO Auto-generated method stub
		return delivererDAO.getDelivererByTid(tid);
	}

}