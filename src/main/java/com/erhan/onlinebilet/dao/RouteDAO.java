package com.erhan.onlinebilet.dao;

import java.util.List;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Route;

public interface RouteDAO {
	public Long create(Route route);
	public Route findById(Long id);
	public List<Route> findAll();
	public Route findByRouteName(String routeName);
	public List<Route> findAllByDepartureAndArrival(City departure, City arrival);
}
