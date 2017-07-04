package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Stop;
import com.erhan.onlinebilet.service.CityService;
import com.erhan.onlinebilet.service.RouteService;
import com.erhan.onlinebilet.service.StopService;

public class RouteTest extends BaseTest {

	@Autowired
	RouteService routeService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	StopService stopService;
	
	@Test
	public void testFindById() {
		Route route2 = routeService.findById(1L);
		assertNotNull(route2);
		System.out.println("Route : " + route2.getRouteName() + " Stops : " + route2.getStops().size());
		Set<Stop> stops = route2.getStops();
		for(Stop s : stops) {
			System.out.println("\t -" + s.getCity().getCityName().toString() + " [" + s.getDuration() + " m]");
		}
	}
	
	@Test
	public void testFindAll() {
		renewTransaction();
		
		List<Route> routeList = routeService.findAll();
		//assertEquals(routeList.size(), 4);
		System.out.println("===========================");
		for(Route route : routeList) {
			System.out.println("Route name : " + route.getRouteName());
			System.out.println("Stops");
			for(Stop s : route.getStops()) {
				System.out.println("\t- " + s.getCity().getCityName() + "[" + s.getDuration() + "]");
			}
			System.out.println("===========================");
		}
	}
	
	@Test
	public void testFindByRouteName() {
		renewTransaction();
		 
		Route ankaraKocaeli = routeService.findByRouteName("Ankara-Kocaeli");
		assertNotNull(ankaraKocaeli);
		assertEquals(ankaraKocaeli.getRouteName(), "Ankara-Kocaeli");
		assertEquals(ankaraKocaeli.getStops().size(), 4);
//		System.out.println("Route name : " + ankaraKocaeli.getRouteName());
//		System.out.println("Stops");
//		for(Stop s : ankaraKocaeli.getStops()) {
//			System.out.println("\t- " + s.getCity().getCityName() + "[" + s.getDuration() + " - " + s.getDistance() + "]");
//		}
	}
	
	@Test
	public void testFindByDepartureAndArrival() {
		renewTransaction();
		
		City ankara = cityService.findById(06L);
		City bolu = cityService.findById(14L);
		
		List<Route> routeList = routeService.findAllByDepartureAndArrival(ankara, bolu);
		assertEquals(routeList.size(), 2);
		for(Route route : routeList) {
			System.out.println("Route name : " + route.getRouteName());
			System.out.println("Stops");
			for(Stop s : route.getStops()) {
				System.out.println("\t- " + s.getCity().getCityName() + "[" + s.getDuration() + " - " + s.getDistance() + "]");
			}
			System.out.println("===========================");
		}
	}
	
	@Test
	public void testGetTotalDistanceAndDurationForRoute() {
		renewTransaction();
		
		Route route1 = routeService.findById(1L);
		System.out.println("Route Name = " + route1.getRouteName());
		
		String[] distanceAndDuration = routeService.getTotalDistanceAndDurationForRoute(route1, 90, 15, 15);
		System.out.println("Distance = " + distanceAndDuration[0]);
		System.out.println("Duration = " + distanceAndDuration[1]);
		
	}
	
	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
