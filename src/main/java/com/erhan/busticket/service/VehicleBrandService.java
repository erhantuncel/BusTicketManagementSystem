package com.erhan.busticket.service;

import java.util.List;

import com.erhan.busticket.model.VehicleBrand;

public interface VehicleBrandService {
	public Long create(VehicleBrand brand);
	public VehicleBrand findById(Long id);
	public List<VehicleBrand> findAll();
	public VehicleBrand findByName(String name);
}
