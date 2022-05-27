package com.erhan.busticket.dao;

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.CityDistance;

public interface CityDistanceDAO {
	public Long create(CityDistance cityDistance);
	public CityDistance findById(Long id);
	public CityDistance findByDepartureAndArrival(City departure, City arrival);
}
