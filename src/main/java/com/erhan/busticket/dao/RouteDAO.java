package com.erhan.busticket.dao;

import java.util.List;

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Route;

public interface RouteDAO {
	public Long create(Route route);
	public Route findById(Long id);
	public List<Route> findAll();
	public Route findByRouteName(String routeName);
	public List<Route> findAllByDepartureAndArrival(City departure, City arrival);
}
