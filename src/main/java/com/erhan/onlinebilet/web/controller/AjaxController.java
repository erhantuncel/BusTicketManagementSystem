package com.erhan.onlinebilet.web.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

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
import com.erhan.onlinebilet.model.VehicleBrand;
import com.erhan.onlinebilet.model.VehicleModel;
import com.erhan.onlinebilet.model.Voyage;
import com.erhan.onlinebilet.service.CityDistanceService;
import com.erhan.onlinebilet.service.CityService;
import com.erhan.onlinebilet.service.CustomerService;
import com.erhan.onlinebilet.service.RouteService;
import com.erhan.onlinebilet.service.StopService;
import com.erhan.onlinebilet.service.TicketService;
import com.erhan.onlinebilet.service.VehicleBrandService;
import com.erhan.onlinebilet.service.VehicleService;
import com.erhan.onlinebilet.service.VoyageService;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForMonthlyDataCount;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForRouteDistance;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForStopTimes;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForTicket;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForVehicle;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForVehicleModelMap;
import com.erhan.onlinebilet.web.model.AjaxResposeBodyForSeatNumbers;


@RestController
public class AjaxController {

	@Autowired
	TicketService ticketService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	VoyageService voyageService;
	
	@Autowired
	CityDistanceService cityDistanceService;
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	VehicleBrandService vehicleBrandService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	StopService stopService;
	
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
	
	@RequestMapping(value = "/admin/vehicleModel/brand/{brandId}")
	public AjaxResponseBodyForVehicleModelMap getVehicleModelListByBrand(@PathVariable(value="brandId") String id) {
		
		AjaxResponseBodyForVehicleModelMap result = new AjaxResponseBodyForVehicleModelMap();
		
		VehicleBrand vehicleBrand = vehicleBrandService.findById(new Long(id));
		Map<String, String> modelMap = new LinkedHashMap<String, String>();
		for(VehicleModel model : vehicleBrand.getVehicleModelList()) {
			modelMap.put(model.getId().toString(), model.getModelName());
		}
		result.setMessage("");
		result.setCode("200");
		result.setModelMap(modelMap);
		return result;
	}
	
	@RequestMapping(value="/admin/biletSayisi/yil/{year}", method=RequestMethod.GET)
	public AjaxResponseBodyForMonthlyDataCount getMontlyTicketCount(@PathVariable(value="year") String year) {
		
		AjaxResponseBodyForMonthlyDataCount result = new AjaxResponseBodyForMonthlyDataCount();
		List<String[]> monthlyTicketCountList = ticketService.countMonthly(new Integer(year));
		String[] data = new String[12];
		for(int i=0; i<monthlyTicketCountList.size(); i++) {
			data[i] = monthlyTicketCountList.get(i)[1];
		}
		result.setMessage("");
		result.setCode("200");
		result.setData(data);
		
		return result;
	}
	
	@RequestMapping(value="/admin/musteriSayisi/yil/{year}", method=RequestMethod.GET)
	public AjaxResponseBodyForMonthlyDataCount getMonthlyCustomerCount(@PathVariable(value="year") String year) {
		AjaxResponseBodyForMonthlyDataCount result = new AjaxResponseBodyForMonthlyDataCount();
		List<String[]> mothlyCustomerCountList = customerService.countMonthly(new Integer(year));
		String[] data = new String[12];
		for(int i=0; i<mothlyCustomerCountList.size(); i++) {
			data[i] = mothlyCustomerCountList.get(i)[1];
		}
		result.setMessage("");
		result.setCode("200");
		result.setData(data);
		return result;
	}
	
	@RequestMapping(value="/musteri/koltukNumaralari/sefer/{voyageId}/kalkis/{departureId}/varis/{arrivalId}", method=RequestMethod.GET)
	public AjaxResposeBodyForSeatNumbers getSeatNumbersForBuyTicketByStop(@PathVariable(value="voyageId") String voyageId, 
								@PathVariable(value="departureId") String departureId,
								@PathVariable(value="arrivalId") String arrivalId, HttpSession session) {
		Voyage voyage = voyageService.findById(new Long(voyageId));
		City departureCity = cityService.findById(new Long(departureId));
		City arrivalCity = cityService.findById(new Long(arrivalId));
		Map<Byte, String> seatNumberAndGenderMap = ticketService.findSeatNumbersAndGenderByVoyageAndStop(voyage, departureCity, arrivalCity);
		
		Ticket ticketForSave = (Ticket) session.getAttribute("ticketForSave");
		if(ticketForSave != null) {
			ticketForSave.setVoyage(voyage);
			ticketForSave.setDepartureTime(stopService.getStopTimeByVoyageAndStopcity(voyage, ticketForSave.getDeparture()));
		}
		session.setAttribute("ticketForSave", ticketForSave);
		
		AjaxResposeBodyForSeatNumbers result = new AjaxResposeBodyForSeatNumbers();
		result.setMessage("");
		result.setCode("200");
		result.setSeatNumbersAndGender(seatNumberAndGenderMap);
		return result;
	}
	
	@RequestMapping(value="/musteri/duraklar/sefer/{seferId}", method=RequestMethod.GET)
	public AjaxResponseBodyForStopTimes getStopTimesForSearchVoyage(@PathVariable(value="seferId") String voyageId, HttpSession session) {
		
		Map<String, Date> stopMapForResult = new LinkedHashMap<String, Date>(0);
		Ticket ticketForSave = (Ticket) session.getAttribute("ticketForSave");		
		
		if(ticketForSave != null) {			
			Voyage voyage = voyageService.findById(new Long(voyageId));
			Map<String, Date> stopMap = stopService.getStopMapByVoyage(voyage);
			Set<Stop> stopSet = voyage.getRoute().getStops();
			Stop[] stopArray = (Stop[]) stopSet.toArray(new Stop[stopSet.size()]);
			Stop[] stopArrayForTicket = null;
			int departureIndex = 0;
			int arrivalIndex = 0;
			for(int i=0; i<stopArray.length; i++) {
				if(stopArray[i].getCity().equals(ticketForSave.getDeparture())) {
//					stopArrayForTicket = Arrays.copyOfRange(stopArray, i, stopArray.length);
					departureIndex = i;
				} else if(stopArray[i].getCity().equals(ticketForSave.getArrival())) {
					arrivalIndex = i;
				}
			}
			stopArrayForTicket = Arrays.copyOfRange(stopArray, departureIndex, arrivalIndex+1);
			
			for(Stop stop : stopArrayForTicket) {
				stopMapForResult.put(stop.getCity().getCityName(), stopMap.get(stop.getCity().getCityName()));
			}
		}
		
		AjaxResponseBodyForStopTimes result = new AjaxResponseBodyForStopTimes();
		result.setMessage("");
		result.setCode("200");
		result.setStopMap(stopMapForResult);
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
