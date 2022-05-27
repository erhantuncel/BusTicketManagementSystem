package com.erhan.busticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.dao.VehicleModelDAO;
import com.erhan.busticket.model.VehicleModel;

@Service("vehicleModelService")
public class VehicleModelServiceImpl implements VehicleModelService {

	@Autowired
	VehicleModelDAO vehicleModelDAO;

	@Override
	@Transactional
	public List<VehicleModel> findAll() {
		List<VehicleModel> models = vehicleModelDAO.findAll();
		return models;
	}

	@Override
	@Transactional
	public VehicleModel findById(Long id) {
		VehicleModel model = vehicleModelDAO.findById(id);
		return model;
	}
}
