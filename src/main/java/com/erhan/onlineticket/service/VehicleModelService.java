package com.erhan.onlineticket.service;

import java.util.List;

import com.erhan.onlineticket.model.VehicleModel;

public interface VehicleModelService {
	public List<VehicleModel> findAll();
	public VehicleModel findById(Long id);
}
