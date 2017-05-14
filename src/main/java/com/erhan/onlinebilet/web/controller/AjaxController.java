package com.erhan.onlinebilet.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.CityDistance;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.service.CityDistanceService;
import com.erhan.onlinebilet.service.CityService;
import com.erhan.onlinebilet.service.TicketService;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForRouteDistance;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForTicket;


@RestController
public class AjaxController {

	@Autowired
	TicketService ticketService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CityDistanceService cityDistanceService;
	
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
		String[] distanceAndDuration = calculateDistanceAndDurationByStopArray(stopArray);
		result.setCode("200");
		result.setMessage("");
		result.setDistance(new Integer(distanceAndDuration[0]));
		result.setDuration(distanceAndDuration[1]);
		return result;
	}
	
	private String[] calculateDistanceAndDurationByStopArray(Integer[] stopArray) {
		String[] distanceAndDurationArray = new String[2];
		
		Integer averageSpeed = 90;
		Integer timePeriodInMinutes = 15;
		Integer extraTime = 15;
		Integer totalDistance = 0;
		Integer totalDurationMin = 0;
		City departure = null;
		for(int i=0; i<stopArray.length; i++) {
			if(i==0) {
				departure = cityService.findById(new Long(stopArray[i]));
				continue;
			}
			City arrival = cityService.findById(new Long(stopArray[i]));
			CityDistance distance = cityDistanceService.findByDepartureAndArrival(departure, arrival);
			Integer calculatedDurationMin = (int) Math.ceil((distance.getDistance().doubleValue() / averageSpeed.doubleValue())*60);
			Integer modDuration15 = calculatedDurationMin % timePeriodInMinutes;
			Integer duration = calculatedDurationMin + (timePeriodInMinutes - modDuration15) + extraTime;
			totalDurationMin = totalDurationMin + duration;
			totalDistance = totalDistance + distance.getDistance(); 
			departure = arrival;
		}
		Integer durationHour = totalDurationMin / 60;
		Integer durationMin = totalDurationMin % 60;
		String durationStr = durationHour + " sa. " + durationMin + " dk.";
		distanceAndDurationArray[0] = totalDistance.toString();
		distanceAndDurationArray[1] = durationStr;
		return distanceAndDurationArray;
	}
}
