package com.erhan.onlinebilet.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.erhan.onlinebilet.service.SampleDataService;

@Controller
public class SampleDataController implements ServletContextAware {
	
	@Autowired
	SampleDataService sampleDataService;
	
	
	private ServletContext servletContext; 
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/loadSampleData", method = RequestMethod.GET )
	public String loadSampleData() {
		
		sampleDataService.populateData(servletContext);
		
		return "public/index";
	}
}
