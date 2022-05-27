package com.erhan.busticket.service;

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.CityDistance;

public interface CityDistanceService {
	public Long create(CityDistance cityDistance);
	public CityDistance findById(Long id);
	public CityDistance findByDepartureAndArrival(City departure, City arrival);
}
