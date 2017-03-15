package com.erhan.onlinebilet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.RouteDAO;
import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Route;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

	@Autowired
	RouteDAO routeDAO;
	
	@Override
	@Transactional
	public Long create(Route route) {
		Long id = routeDAO.create(route);
		return id;
	}

	@Override
	@Transactional
	public Route findById(Long id) {
		Route route = routeDAO.findById(id);
		return route;
	}

	@Override
	@Transactional
	public List<Route> findAll() {
		List<Route> routeList = routeDAO.findAll();
		return routeList;
	}

	@Override
	@Transactional
	public Route findByRouteName(String routeName) {
		Route route = routeDAO.findByRouteName(routeName);
		return route;
	}

	@Override
	@Transactional
	public List<Route> findAllByDepartureAndArrival(City departure, City arrival) {
		List<Route> routeList = routeDAO.findAllByDepartureAndArrival(departure, arrival);
		return routeList;
	}
}
