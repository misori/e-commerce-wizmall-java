package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.domain.Point;

public interface PointDAO {
	public List<Point> getPointListByUserId(String user_id) throws DataAccessException;
	public Point getPointByTid(Integer tid) throws DataAccessException;
}