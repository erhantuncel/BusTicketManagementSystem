package com.erhan.onlinebilet.service;

import java.util.List;

import com.erhan.onlinebilet.model.VehicleModel;

public interface VehicleModelService {
	public List<VehicleModel> findAll();
	public VehicleModel findById(Long id);
}
