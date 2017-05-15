package com.erhan.onlinebilet.controller.admin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.CityDistance;
import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Stop;
import com.erhan.onlinebilet.service.CityDistanceService;
import com.erhan.onlinebilet.service.CityService;
import com.erhan.onlinebilet.service.RouteService;
import com.erhan.onlinebilet.service.StopService;
import com.erhan.onlinebilet.web.model.AddRouteForm;

@Controller
public class AdminRouteController {
	
	@Autowired
	StopService stopService;
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CityDistanceService cityDistanceService;
	
	@RequestMapping(value = "/admin/guzergahlar", method=RequestMethod.GET)
	public ModelAndView routes() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Güzergâhlar");

		List<Route> routeList = routeService.findAll();
		Map<Route, String[]> routeAndDistanceDurationMap = new LinkedHashMap<Route, String[]>();
		for(Route route : routeList) {
			String[] distanceAndDurationForRoute = routeService.getTotalDistanceAndDurationForRoute(route, 90, 15, 15);
			routeAndDistanceDurationMap.put(route, distanceAndDurationForRoute);
		}		
		
		model.addObject("routeAndDistanceDurationMap", routeAndDistanceDurationMap);
		model.setViewName("admin/guzergahlar");
		return model;
	}
	
	@RequestMapping(value = "/admin/guzergahEkle", method=RequestMethod.GET)
	public ModelAndView showAddRouteForm() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Güzergah Ekleme");
		populateModelWithCity(cityService.findAll(), model);
		
		model.addObject("addRouteForm", new AddRouteForm());
		model.setViewName("admin/guzergahEkle");
		return model;
	}
	
	@RequestMapping(value = "/admin/guzergahEkle", method=RequestMethod.POST)
	public ModelAndView saveRouteAndStops(@ModelAttribute("addRouteForm") @Valid AddRouteForm addRouteForm, BindingResult result,
											ModelAndView model, RedirectAttributes redir) {
		List<String> stopPlateCodes = addRouteForm.getStopList();
		if(result.hasErrors()) {
			populateModelWithCity(cityService.findAll(), model);
			model.setViewName("admin/guzergahEkle");
		} else {
			Route route = new Route();
			route.setRouteName(addRouteForm.getRouteName());
			City departure = null;
			Stop stop = null;
			Integer lastDuration = 0;
			for (int i=0; i<stopPlateCodes.size(); i++) {
				if(i==0) {
					departure = cityService.findById(new Long(stopPlateCodes.get(i)));
					stop = new Stop(route, departure, 0, 0);
				} else {
					City arrival = cityService.findById(new Long(stopPlateCodes.get(i)));
					
					Integer averageSpeed = 90;
					Integer timePeriodInMinutes = 15;
					Integer extraTime = 15;
					CityDistance distance = cityDistanceService.findByDepartureAndArrival(departure, arrival);
					Integer calculatedDurationMin = (int) Math.ceil((distance.getDistance().doubleValue() / averageSpeed.doubleValue())*60);
					Integer modDuration15 = calculatedDurationMin % timePeriodInMinutes;
					Integer duration = calculatedDurationMin + (timePeriodInMinutes - modDuration15) + extraTime;
					lastDuration = lastDuration + duration;
					stop = new Stop(route, arrival, lastDuration, 0);
					departure = arrival;
				}
				stopService.create(stop);
				route.getStops().add(stop);
				routeService.create(route);
			}
			
			String resultMessage = route.getRouteName() + " güzergâhı oluşturuldu.";
			
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", resultMessage);
		}
		
		model.setViewName("redirect:" + "/admin/guzergahlar");
		return model;
	}
	
	private void populateModelWithCity(List<City> cityList, ModelAndView model) {
		Map<String, String> cityMap = new LinkedHashMap<String, String>();
		for(City city : cityList) {
			cityMap.put(city.getId().toString(), city.getCityName());
		}
		model.addObject("cityMap", cityMap);
	}
}
