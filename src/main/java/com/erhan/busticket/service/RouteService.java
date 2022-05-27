package com.erhan.busticket.service;

import java.util.List;

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Route;

public interface RouteService {
	public Long create(Route route);
	public Route findById(Long id);
	public List<Route> findAll();
	public Route findByRouteName(String routeName);
	public List<Route> findAllByDepartureAndArrival(City departure, City arrival);
	public String[] getTotalDistanceAndDurationForRoute(Route route, Integer averageSpeed, Integer timePeriod, Integer extraTime);
}
