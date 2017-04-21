package com.erhan.onlinebilet.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Voyage;

public interface VoyageService {

	public Long create(Voyage voyage);
	public Voyage findById(Long id);
	public List<Voyage> findAll();
	public void update(Voyage voyage);
	public List<Voyage> findAllByDate(Date date);
	public List<Voyage> findAllByRouteAndDate(List<Route> routeList, Date date);
	public Map<Voyage, String> findAllStartedInHourWithCompletionPercentage(Integer hour);
	public List<Voyage> findAllSortedByDepartureTimeForRecords(Integer count);
	public List<Voyage> findAllBetweenDates(Date start, Date end);
	public void delete(Voyage voyage);
}
