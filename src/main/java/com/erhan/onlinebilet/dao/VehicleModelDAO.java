package com.erhan.onlinebilet.dao;

import java.util.List;

import com.erhan.onlinebilet.model.VehicleModel;

public interface VehicleModelDAO {
	public List<VehicleModel> findAll();
	public VehicleModel findById(Long id);
}
