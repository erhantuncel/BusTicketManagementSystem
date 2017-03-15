package com.erhan.onlinebilet.dao;

import java.util.Date;
import java.util.List;

import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Voyage;

public interface VoyageDAO {
	public Long create(Voyage voyage);
	public Voyage findById(Long id);
	public List<Voyage> findAll();
	public void update(Voyage voyage);
	public List<Voyage> findAllByDate(Date date);
	public List<Voyage> findAllByRouteAndDate(Route route, Date date);
	public List<Voyage> findAllStartedInHour(Integer hour);
	public List<Voyage> findAllSortedByDepartureTimeForRecords(Integer count);
	public void delete(Voyage voyage);
}
