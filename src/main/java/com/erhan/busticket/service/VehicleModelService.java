package com.erhan.busticket.service;

import java.util.List;

import com.erhan.busticket.model.VehicleModel;

public interface VehicleModelService {
	public List<VehicleModel> findAll();
	public VehicleModel findById(Long id);
}
