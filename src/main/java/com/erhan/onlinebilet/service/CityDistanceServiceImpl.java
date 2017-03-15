package com.erhan.onlinebilet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.CityDistanceDAO;
import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.CityDistance;

@Service("cityDistanceService")
public class CityDistanceServiceImpl implements CityDistanceService {

	@Autowired
	CityDistanceDAO cityDistanceDAO;
	
	@Override
	@Transactional
	public Long create(CityDistance cityDistance) {
		return cityDistanceDAO.create(cityDistance);
	}

	@Override
	@Transactional
	public CityDistance findById(Long id) {
		return cityDistanceDAO.findById(id);
	}

	@Override
	@Transactional
	public CityDistance findByDepartureAndArrival(City departure, City arrival) {
		return cityDistanceDAO.findByDepartureAndArrival(departure, arrival);
	}
}
