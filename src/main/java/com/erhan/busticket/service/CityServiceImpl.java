package com.erhan.busticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.dao.CityDAO;
import com.erhan.busticket.model.City;

@Service("cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	CityDAO cityDAO;
	
	@Override
	@Transactional
	public City findById(Long id) {
		City city = cityDAO.findById(id);
		return city;
	}

	@Override
	@Transactional
	public Long create(City city) {
		Long id = cityDAO.create(city);
		return id;
	}

	@Override
	@Transactional
	public List<City> findAll() {
		List<City> cityList = cityDAO.findAll();
		return cityList;
	}

	@Override
	public void update(City city) {
		cityDAO.update(city);
	}
}
