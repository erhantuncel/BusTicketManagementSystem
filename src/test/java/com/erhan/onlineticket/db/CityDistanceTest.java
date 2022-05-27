package com.erhan.onlineticket.db;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.onlineticket.model.City;
import com.erhan.onlineticket.model.CityDistance;
import com.erhan.onlineticket.service.CityDistanceService;

public class CityDistanceTest extends BaseTest {

	@Autowired
	CityDistanceService cityDistanceService;
	
	@Test
	public void testCreateCityDistance() {
		renewTransaction();
		
		City adana = cityService.findById(1L);
		City adiyaman = cityService.findById(2L);
		
		CityDistance distance1to2 = new CityDistance();
		distance1to2.setDeparture(adana);
		distance1to2.setArrival(adiyaman);
		distance1to2.setDistance(329);
		cityDistanceService.create(distance1to2);
		
		renewTransaction();
		
		CityDistance distance1to2FromDB = cityDistanceService.findById(1L);
		assertNotNull(distance1to2FromDB);
		assertEquals(distance1to2FromDB.getDistance(), new Integer(329));
		System.out.println("Distance from Adana to Adıyaman : " + distance1to2FromDB.getDistance());
		
	}
	
	@Test
	public void testFindByDepartureAndArrival() {
		renewTransaction();
		
		City adana = cityService.findById(1L);
		City adiyaman = cityService.findById(2L);
		City afyon = cityService.findById(3L);
		
		CityDistance distance1to2 = new CityDistance();
		distance1to2.setDeparture(adana);
		distance1to2.setArrival(adiyaman);
		distance1to2.setDistance(329);
		cityDistanceService.create(distance1to2);
		
		CityDistance distance1to3 = new CityDistance();
		distance1to3.setDeparture(adana);
		distance1to3.setArrival(afyon);
		distance1to3.setDistance(573);
		cityDistanceService.create(distance1to3);
		
		renewTransaction();
		
		CityDistance distance1to3FromDB = cityDistanceService.findByDepartureAndArrival(adana, afyon);
		assertNotNull(distance1to3FromDB);
		assertEquals(distance1to3FromDB.getDistance(), new Integer(573));
		System.out.println("Distance from Adana to Afyon : " + distance1to3FromDB.getDistance());
		
		City amasya = cityService.findById(5L);
		
		CityDistance distance1to5FromDB = cityDistanceService.findByDepartureAndArrival(adana, amasya);
		assertNotNull(distance1to5FromDB);
		assertEquals(distance1to5FromDB.getDistance(), new Integer(613));
		System.out.println("Distance from Adana to Amasya : " + distance1to3FromDB.getDistance());
		
		
	}
	
	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
