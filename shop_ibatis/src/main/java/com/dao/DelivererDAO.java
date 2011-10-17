package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Deliverer;

/**
 *
 * @author Pondol
 *
 */
public interface DelivererDAO{
	public List<Deliverer> getAllDelivererList() throws DataAccessException;
	public Deliverer getDelivererByTid(Integer tid) throws DataAccessException;

	/**
	 * 데이타를 저장
	 */
	public void saveDeliverer(Deliverer deliverer);

	/**
	 * 데이타를 업데이트
	 */
	public void updateDeliverer(Deliverer deliverer);

	/**
	 * 게시물을 삭제한다.
	 */
	public void deleteDeliverer(int tid);
}