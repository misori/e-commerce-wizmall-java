package com.service;

import com.domain.Point;

/**
 *
 * @author Pondol
 *
 */
public interface PointService {

	/**
	 * Save an existing Account entity
	 *
	 */
	public void savePoint(Point point);

	/**
	 * Delete an existing Account entity
	 *
	 */
	public void deletePoint(Point point);

	public void updatePointTable();//포인트 테이블만 업데이트 한다.

	public void updatePoint();//포인트 관련 테이블(회원의 point 테이블) 도 동시에 update 한다.

	public void insertPoint(Point point);//포인트 테이블에는 insert 회원의 point 필드는  update 한다.
}
