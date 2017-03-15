package com.erhan.onlinebilet.service;

import java.util.List;

import com.erhan.onlinebilet.model.VehicleBrand;

public interface VehicleBrandService {
	public Long create(VehicleBrand brand);
	public VehicleBrand findById(Long id);
	public List<VehicleBrand> findAll();
	public VehicleBrand findByName(String name);
}
