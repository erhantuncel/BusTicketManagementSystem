package com.erhan.onlineticket.dao;

import java.util.Date;
import java.util.List;

import com.erhan.onlineticket.model.Route;
import com.erhan.onlineticket.model.Voyage;

public interface VoyageDAO {
	public Long create(Voyage voyage);
	public Voyage findById(Long id);
	public List<Voyage> findAll();
	public void update(Voyage voyage);
	public List<Voyage> findAllByDate(Date date);
	public List<Voyage> findAllByRouteAndDate(Route route, Date date);
	public List<Voyage> findAllStartedInHour(Integer hour);
	public List<Voyage> findAllSortedByDepartureTimeForRecords(Integer count);
	public List<Voyage> findAllBetweenDates(Date start, Date end);
	public void delete(Voyage voyage);
}
