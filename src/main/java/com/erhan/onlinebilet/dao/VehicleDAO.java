package com.erhan.onlinebilet.dao;

import java.util.LinkedList;
import java.util.List;

import com.erhan.onlinebilet.model.Vehicle;

public interface VehicleDAO {
	public Long create(Vehicle vehicle);
	public List<Vehicle> findAll();
	public LinkedList<Long> findAllIds();
	public List<Vehicle> findAllSortedByYear();
	public Vehicle findByPlateCode(String plateCode);
	public Vehicle findById(Long id);
	public void update(Vehicle vehicle);
	public void delete(Vehicle vehicle);
}