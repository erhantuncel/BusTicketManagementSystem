package com.erhan.busticket.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Customer;
import com.erhan.busticket.service.CityService;
import com.erhan.busticket.service.CustomerService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CityService cityService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		populateModelWithCity(cityService.findAll(), model);
		model.addObject("title", "Online Bilet Sistemi");
		model.setViewName("public/index");
		return model;
	}
	
//	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
//	public ModelAndView notCustomer(@PathVariable(value="page") String page) {
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Online Bilet Sistemi");
//		model.setViewName("public/" + page);
//		return model;
//	}
	
//	@RequestMapping(value = "/musteri/{page}", method = RequestMethod.GET)
//	public ModelAndView customer(@PathVariable(value="page") String page) {
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Online Bilet Sistemi");
//		model.addObject("customer", getCustomer());
//		model.setViewName("public/" + page);
//		return model;
//	}
	
	private void populateModelWithCity(List<City> cityList, ModelAndView model) {
		Map<String, String> cityMap = new LinkedHashMap<String, String>();
		for(City city : cityList) {
			cityMap.put(city.getId().toString(), city.getCityName());
		}
		model.addObject("cityMap", cityMap);
	}
	
	private Customer getCustomer() {
		String tcNumber = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			tcNumber = ((UserDetails)principal).getUsername();
		} else {
			tcNumber = principal.toString();
		}
		
		Customer customer = customerService.findByTcNumber(tcNumber);
		return customer;
	}
}
