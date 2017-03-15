package com.erhan.onlinebilet.service;

import java.util.List;

import com.erhan.onlinebilet.model.City;

public interface CityService {
	public City findById(Long id);
	public Long create(City city);
	public List<City> findAll();
	public void update(City city);
	
	
}
