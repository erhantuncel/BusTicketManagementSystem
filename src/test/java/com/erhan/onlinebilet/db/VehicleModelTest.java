package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.erhan.onlinebilet.model.VehicleModel;
import com.erhan.onlinebilet.service.VehicleBrandService;
import com.erhan.onlinebilet.service.VehicleModelService;

public class VehicleModelTest extends BaseTest {

	@Autowired
	VehicleModelService vehicleModelService;
	
	@Autowired
	VehicleBrandService vehicleBrandService;
	
	@Test
	public void testFindAllVehicleModel() {
		List<VehicleModel> modelList = vehicleModelService.findAll();
		assertEquals(modelList.size(), 7);
		for(VehicleModel model : modelList) {
			System.out.println(model.getBrand().getName() + " - " + model.getModelName());
		}
	}
	
	@Test
	public void testFindById() {
		VehicleModel model = vehicleModelService.findById(7L);
		assertEquals(model.getBrand().getName(), "Neoplan");
		assertEquals(model.getModelName(), "Cityliner");
	}
}
