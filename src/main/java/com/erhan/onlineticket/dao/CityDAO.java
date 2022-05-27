package com.erhan.onlineticket.dao;

import java.util.List;

import com.erhan.onlineticket.model.City;

public interface CityDAO {
	public City findById(Long id);
	public Long create(City city);
	public List<City> findAll();
	public void update(City city);
}
