package com.erhan.onlineticket.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.erhan.onlineticket.model.VehicleModel;
import com.erhan.onlineticket.service.VehicleBrandService;
import com.erhan.onlineticket.service.VehicleModelService;

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
