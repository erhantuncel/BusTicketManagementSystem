package com.erhan.onlineticket.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.erhan.onlineticket.model.City;
import com.erhan.onlineticket.service.CityService;


@Controller
public class LoginController {

	@Autowired
	CityService cityService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(ModelAndView model) {
		populateModelWithCity(cityService.findAll(), model);
		model.setViewName("public/index");
		return model;
	}
	
	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public View commonWelcomePage(HttpServletRequest request) {
		Set<String> roles = AuthorityUtils.authorityListToSet(
				SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		if(roles.contains("ROLE_ADMIN")) {
			return new RedirectView("admin");
		} else {
			return new RedirectView("musteri");
		}
	}
	
	private void populateModelWithCity(List<City> cityList, ModelAndView model) {
		Map<String, String> cityMap = new LinkedHashMap<String, String>();
		for(City city : cityList) {
			cityMap.put(city.getId().toString(), city.getCityName());
		}
		model.addObject("cityMap", cityMap);
	}
}