package com.erhan.busticket.dao;

import java.util.List;

import com.erhan.busticket.model.City;

public interface CityDAO {
	public City findById(Long id);
	public Long create(City city);
	public List<City> findAll();
	public void update(City city);
}
