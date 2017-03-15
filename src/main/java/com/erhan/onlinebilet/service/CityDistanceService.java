package com.erhan.onlinebilet.service;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.CityDistance;

public interface CityDistanceService {
	public Long create(CityDistance cityDistance);
	public CityDistance findById(Long id);
	public CityDistance findByDepartureAndArrival(City departure, City arrival);
}
