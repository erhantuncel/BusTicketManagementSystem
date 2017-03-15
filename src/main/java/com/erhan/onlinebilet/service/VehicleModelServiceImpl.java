package com.erhan.onlinebilet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.VehicleModelDAO;
import com.erhan.onlinebilet.model.VehicleModel;

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
