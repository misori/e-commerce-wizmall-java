package com.service;

import java.util.List;

import com.domain.Deliverer;

public interface DelivererService {
	public List<Deliverer> getAllDelivererList();

	public Deliverer getDelivererByTid(Integer tid);
	/**
	 * Save an existing Deliverer entity
	 *
	 */
	public void saveDeliverer(Deliverer deliverer);

	/**
	 * Delete an existing Deliverer entity
	 *@deprecated
	 */
	public void deleteDeliverer(Deliverer deliverer);
	public void deleteDeliverer(Integer tid);
}