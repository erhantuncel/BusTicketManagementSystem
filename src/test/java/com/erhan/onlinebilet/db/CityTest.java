package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.service.CityService;

public class CityTest extends BaseTest {
	
	@Autowired
	CityService cityService;
	
	@Test
	public void testCityName() {
		City city = cityService.findById(10L);
		assertNotNull(city);
		assertEquals(city.getCityName(), "BALIKESİR");
		System.out.println(city);
	}
	
	@Test
	public void testFindAllCity() {
		List<City> cityList = cityService.findAll();
		assertNotNull(cityList);
		assertEquals(cityList.size(), 81);
		assertEquals(cityList.get(13).getCityName(), "BOLU");
		for(City c : cityList) {
			System.out.println(c);
		}
	}
}
