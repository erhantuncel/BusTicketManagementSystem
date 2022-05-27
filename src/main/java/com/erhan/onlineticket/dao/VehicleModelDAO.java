package com.erhan.onlineticket.dao;

import java.util.List;

import com.erhan.onlineticket.model.VehicleModel;

public interface VehicleModelDAO {
	public List<VehicleModel> findAll();
	public VehicleModel findById(Long id);
}
