package com.erhan.onlineticket.service;

import java.util.List;

import com.erhan.onlineticket.model.City;

public interface CityService {
	public City findById(Long id);
	public Long create(City city);
	public List<City> findAll();
	public void update(City city);
	
	
}
