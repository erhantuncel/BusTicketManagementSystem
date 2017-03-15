package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.onlinebilet.model.Vehicle;
import com.erhan.onlinebilet.model.VehicleModel;
import com.erhan.onlinebilet.service.VehicleBrandService;
import com.erhan.onlinebilet.service.VehicleService;

public class VehicleTest extends BaseTest {

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	VehicleBrandService vehicleBrandService;
	
	
	@Test
	public void testFindAll() {
		renewTransaction();
		
		List<Vehicle> vehicleList = vehicleService.findAll();
		assertEquals(vehicleList.size(), 12);
		Collections.shuffle(vehicleList);
		for(Vehicle vehicle : vehicleList) {
			System.out.print("Id :" + vehicle.getId());
			System.out.print("\t Plate Code : " + vehicle.getPlateCode());
			System.out.print("\t Brand : " + vehicle.getModel().getBrand().getName());
			System.out.print(" - " + vehicle.getModel().getModelName());
			System.out.print("\t Year : " + vehicle.getYear());
			System.out.println("\t Milage : " + vehicle.getMilage().toString());
		}
	}
	
	@Test
	public void testFindAllSortedByYear() {
		renewTransaction();
		
		List<Vehicle> vehicleList = vehicleService.findAllSortedByYear();
		for(Vehicle vehicle : vehicleList) {
			System.out.print("Id : " + vehicle.getId());
			System.out.print("\t Plate Code : " + vehicle.getPlateCode());
			System.out.println("\t Year : " + vehicle.getYear());
		}
	}
	
	@Test
	public void testFindByPlateCode() {
		renewTransaction();
		
		Vehicle vehicle6 = vehicleService.findById(6L);
		String plateCode6 = vehicle6.getPlateCode();
		
		renewTransaction();
		
		Vehicle vehicleTest = vehicleService.findByPlateCode(plateCode6);
		assertTrue(vehicleTest.getId() == 6L);
	}
	
	@Test
	public void testUpdate() {
		renewTransaction();
		
		Vehicle vehicle3 = vehicleService.findById(3L);
		vehicle3.setMilage(152222);
		vehicleService.update(vehicle3);
		
		renewTransaction();
		
		Vehicle vehicleTest = vehicleService.findById(3L);
		assertEquals(vehicleTest.getMilage(), new Integer(152222));
	}
	
	@Test
	public void testDelete() {
		renewTransaction();
		
		VehicleModel model = vehicleModelService.findById(1L);
		
		Vehicle vehicle = new Vehicle("14AS957", 37, model, "2014", 85366);
		vehicleService.create(vehicle);

		renewTransaction();
				
		Vehicle vehicleForDelete = vehicleService.findByPlateCode("14AS957");
		vehicleForDelete.setModel(null);
		vehicleService.delete(vehicleForDelete);
		
		renewTransaction();
		Vehicle vehicleForTest = vehicleService.findByPlateCode("14AS957");
		assertNull(vehicleForTest);
		
	}
	
	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
