package com.erhan.onlineticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlineticket.dao.VehicleBrandDAO;
import com.erhan.onlineticket.model.VehicleBrand;

@Service("vehicleBrandService")
public class VehicleBrandServiceImpl implements VehicleBrandService {
	
	@Autowired
	VehicleBrandDAO vehicleBrandDAO;

	@Override
	@Transactional
	public Long create(VehicleBrand brand) {
		Long id = vehicleBrandDAO.create(brand);
		return id;
	}

	@Override
	@Transactional
	public VehicleBrand findById(Long id) {
		VehicleBrand brand = vehicleBrandDAO.findById(id);
		return brand;
	}

	@Override
	@Transactional
	public List<VehicleBrand> findAll() {
		return vehicleBrandDAO.findAll();
	}

	@Override
	@Transactional
	public VehicleBrand findByName(String name) {
		VehicleBrand brand = vehicleBrandDAO.findByName(name);
		return brand;
	}
}
