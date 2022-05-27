package com.erhan.onlineticket.dao;

import com.erhan.onlineticket.model.City;
import com.erhan.onlineticket.model.CityDistance;

public interface CityDistanceDAO {
	public Long create(CityDistance cityDistance);
	public CityDistance findById(Long id);
	public CityDistance findByDepartureAndArrival(City departure, City arrival);
}
