package com.erhan.onlinebilet.dao;

import java.util.List;

import com.erhan.onlinebilet.model.City;

public interface CityDAO {
	public City findById(Long id);
	public Long create(City city);
	public List<City> findAll();
	public void update(City city);
}
