package com.erhan.onlineticket.service;

import java.util.List;

import com.erhan.onlineticket.model.City;
import com.erhan.onlineticket.model.Route;

public interface RouteService {
	public Long create(Route route);
	public Route findById(Long id);
	public List<Route> findAll();
	public Route findByRouteName(String routeName);
	public List<Route> findAllByDepartureAndArrival(City departure, City arrival);
	public String[] getTotalDistanceAndDurationForRoute(Route route, Integer averageSpeed, Integer timePeriod, Integer extraTime);
}
