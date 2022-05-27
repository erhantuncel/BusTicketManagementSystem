package com.erhan.busticket.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.model.VehicleBrand;
import com.erhan.busticket.model.VehicleModel;
import com.erhan.busticket.service.VehicleBrandService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
								 "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class VehicleBrandTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	VehicleBrandService vehicleBrandService;
	
	@Before
	@Transactional
	public void populateVehicleBrand() {
		VehicleBrand brand = new VehicleBrand("Mercede-Benz");
		
		VehicleModel vehicleModel1 = new VehicleModel("Tourismo 15 RHD", brand);
		VehicleModel vehicleModel2 = new VehicleModel("O403", brand);
		VehicleModel vehicleModel3 = new VehicleModel("Travego 17 SHD", brand);
		
		brand.getVehicleModelList().add(vehicleModel1);
		brand.getVehicleModelList().add(vehicleModel2);
		brand.getVehicleModelList().add(vehicleModel3);
		
		Long id = vehicleBrandService.create(brand);
		assertNotNull(id);
		System.out.println("Id = " + id);
	}
	
	@Test
	public void testFindById() {
		VehicleBrand brand = vehicleBrandService.findById(1L);
		assertNotNull(brand);
		System.out.println(brand);
	}
	
	@Test
	public void testFindAll() {
		List<VehicleBrand> brandList = vehicleBrandService.findAll();
		assertEquals(brandList.size(), 1);
		for(VehicleBrand brand : brandList) {
			System.out.println(brand);
			if(brand.getVehicleModelList().size() > 0) {
				for(VehicleModel model : brand.getVehicleModelList()) {
					System.out.println("\t" + model);
				}
			}
		}
	}
}
