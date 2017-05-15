package com.erhan.onlinebilet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erhan.onlinebilet.service.SampleDataService;

@Controller
public class SampleDataController {
	
	@Autowired
	SampleDataService sampleDataService;
	
	@RequestMapping(value = "/loadSampleData", method = RequestMethod.GET )
	public String loadSampleData() {
		
		sampleDataService.populateData();
		
		return "public/index";
	}
}
