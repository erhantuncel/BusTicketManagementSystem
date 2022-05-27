package com.erhan.onlineticket.dao;

import java.util.List;

import com.erhan.onlineticket.model.VehicleBrand;

public interface VehicleBrandDAO {
	public Long create(VehicleBrand vehicleBrand);
	public VehicleBrand findById(Long id);
	public List<VehicleBrand> findAll();
	public VehicleBrand findByName(String name);
}