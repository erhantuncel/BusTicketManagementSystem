package com.erhan.onlinebilet.web.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Stop;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.Vehicle;
import com.erhan.onlinebilet.service.CityDistanceService;
import com.erhan.onlinebilet.service.CityService;
import com.erhan.onlinebilet.service.RouteService;
import com.erhan.onlinebilet.service.TicketService;
import com.erhan.onlinebilet.service.VehicleService;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForRouteDistance;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForTicket;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForVehicle;


@RestController
public class AjaxController {

	@Autowired
	TicketService ticketService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CityDistanceService cityDistanceService;
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	VehicleService vehicleService;
	
	@RequestMapping(value = "/admin/biletDetay/{id}")
	public AjaxResponseBodyForTicket getTicketSearchResultById2(@PathVariable(value="id") String id) {
		
		AjaxResponseBodyForTicket result = new AjaxResponseBodyForTicket();
		
		Ticket ticket = ticketService.findById(Long.valueOf(id));
		
		result.setCode("200");
		result.setMessage("");
		result.setTicket(ticket);
		
		return result;
	}
	
	@RequestMapping(value="/admin/rotaMesafeHesapla", method=RequestMethod.POST)
	public AjaxResponseBodyForRouteDistance getTotalDistanceForRoute(@RequestBody Integer[] stopArray) {
		
		AjaxResponseBodyForRouteDistance result = new AjaxResponseBodyForRouteDistance();
		Route route = new Route();
		populateRouteWithStopArray(route, stopArray);
		String[] distanceAndDuration = routeService.getTotalDistanceAndDurationForRoute(route, 90, 15, 15);
		result.setCode("200");
		result.setMessage("");
		result.setDistance(new Integer(distanceAndDuration[0]));
		result.setDuration(distanceAndDuration[1]);
		return result;
	}
	
	@RequestMapping(value = "/admin/aracDetay/{id}")
	public AjaxResponseBodyForVehicle getVehicleSearchResultById(@PathVariable(value="id") String id) {
		
		AjaxResponseBodyForVehicle result = new AjaxResponseBodyForVehicle();
		
		Vehicle vehicle = vehicleService.findById(new Long(id));
		
		result.setCode("200");
		result.setMessage("");
		result.setVehicle(vehicle);
		
		return result;
	}
	
	private void populateRouteWithStopArray(Route route, Integer[] stopArray) {
		route.setRouteName("Deneme");
		Set<Stop> stopSet = new LinkedHashSet<Stop>();
		for(int i=0; i<stopArray.length; i++) {
			City city = cityService.findById(new Long(stopArray[i]));
			Stop stop = new Stop(route, city, 0, 0);
			stopSet.add(stop);
		}
		route.setStops(stopSet);
	}
}
