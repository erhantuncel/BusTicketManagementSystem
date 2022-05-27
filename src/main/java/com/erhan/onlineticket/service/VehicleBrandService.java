package com.erhan.onlineticket.service;

import java.util.List;

import com.erhan.onlineticket.model.VehicleBrand;

public interface VehicleBrandService {
	public Long create(VehicleBrand brand);
	public VehicleBrand findById(Long id);
	public List<VehicleBrand> findAll();
	public VehicleBrand findByName(String name);
}
