package com.erhan.busticket.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.dao.VehicleDAO;
import com.erhan.busticket.model.Vehicle;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	VehicleDAO vehicleDAO;

	@Override
	@Transactional
	public Long create(Vehicle vehicle) {
		Long id = vehicleDAO.create(vehicle);
		return id;
	}

	@Override
	@Transactional
	public List<Vehicle> findAll() {
		return vehicleDAO.findAll();
	}

	@Override
	@Transactional
	public LinkedList<Long> findAllIds() {
		LinkedList<Long> ids = vehicleDAO.findAllIds();
		return ids;
	}

	@Override
	@Transactional
	public List<Vehicle> findAllSortedByYear() {
		List<Vehicle> vehicleList = vehicleDAO.findAllSortedByYear();
		return vehicleList;
	}

	@Override
	@Transactional
	public Vehicle findByPlateCode(String plateCode) {
		Vehicle vehicle = vehicleDAO.findByPlateCode(plateCode);
		return vehicle;
	}

	@Override
	@Transactional
	public Vehicle findById(Long id) {
		Vehicle vehicle = vehicleDAO.findById(id);
		return vehicle;
	}

	@Override
	@Transactional
	public void update(Vehicle vehicle) {
		vehicleDAO.update(vehicle);
	}

	@Override
	@Transactional
	public void delete(Vehicle vehicle) {
		vehicleDAO.delete(vehicle);
	}
}
