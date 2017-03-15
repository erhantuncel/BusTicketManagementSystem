package com.erhan.onlinebilet.dao;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.CityDistance;

public interface CityDistanceDAO {
	public Long create(CityDistance cityDistance);
	public CityDistance findById(Long id);
	public CityDistance findByDepartureAndArrival(City departure, City arrival);
}
