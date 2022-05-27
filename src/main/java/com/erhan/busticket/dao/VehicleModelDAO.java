package com.erhan.busticket.dao;

import java.util.List;

import com.erhan.busticket.model.VehicleModel;

public interface VehicleModelDAO {
	public List<VehicleModel> findAll();
	public VehicleModel findById(Long id);
}
