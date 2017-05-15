package com.erhan.onlinebilet.service;

import java.util.List;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Route;

public interface RouteService {
	public Long create(Route route);
	public Route findById(Long id);
	public List<Route> findAll();
	public Route findByRouteName(String routeName);
	public List<Route> findAllByDepartureAndArrival(City departure, City arrival);
	public String[] getTotalDistanceAndDurationForRoute(Route route, Integer averageSpeed, Integer timePeriod, Integer extraTime);
}
