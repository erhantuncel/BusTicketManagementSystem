package com.erhan.onlineticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlineticket.dao.RouteDAO;
import com.erhan.onlineticket.model.City;
import com.erhan.onlineticket.model.CityDistance;
import com.erhan.onlineticket.model.Route;
import com.erhan.onlineticket.model.Stop;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

	@Autowired
	RouteDAO routeDAO;
	
	@Autowired
	CityDistanceService cityDistanceService;
	
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

	@Override
	public String[] getTotalDistanceAndDurationForRoute(Route route, Integer averageSpeed, Integer timePeriod, Integer extraTime) {
		Integer totalDistance = 0;
		Integer totalDurationMin = 0;
		City departure = null;
		int index = 0;
		for(Stop stop : route.getStops()) {
			if(index == 0) {
				departure = stop.getCity();
				index++;
				continue;
			}
			City arrival = stop.getCity();
			CityDistance distance = cityDistanceService.findByDepartureAndArrival(departure, arrival);
			Integer calculatedDurationMin = (int) Math.ceil((distance.getDistance().doubleValue() / averageSpeed.doubleValue())*60);
			Integer modDuration15 = calculatedDurationMin % timePeriod;
			Integer duration = calculatedDurationMin + (timePeriod - modDuration15) + extraTime;
			totalDurationMin = totalDurationMin + duration;
			totalDistance = totalDistance + distance.getDistance();
			departure = arrival;
		}
		Integer durationHour = totalDurationMin / 60;
		Integer durationMin = totalDurationMin % 60;
		String durationStr = durationHour + " sa. " + durationMin + " dk.";
		String[] distanceAndDurationArray = new String[2];
		distanceAndDurationArray[0] = totalDistance.toString();
		distanceAndDurationArray[1] = durationStr;		
		
		return distanceAndDurationArray;
	}
}
