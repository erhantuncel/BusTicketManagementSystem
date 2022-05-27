package com.erhan.onlineticket.service;

import com.erhan.onlineticket.model.City;
import com.erhan.onlineticket.model.CityDistance;

public interface CityDistanceService {
	public Long create(CityDistance cityDistance);
	public CityDistance findById(Long id);
	public CityDistance findByDepartureAndArrival(City departure, City arrival);
}
