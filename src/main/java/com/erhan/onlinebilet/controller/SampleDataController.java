package com.erhan.onlinebilet.controller;


import java.util.concurrent.TimeUnit;

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
		long startTime = System.currentTimeMillis();
		sampleDataService.populateData();
		long endTime = System.currentTimeMillis();
		Long duration = (endTime - startTime);
		System.out.println("SAMPLE DATA LOADING DURATION = " + millisToShortDHMS(duration));
		return "public/index";
	}
	
	private String millisToShortDHMS(long duration) {
	    String res = "";    // java.util.concurrent.TimeUnit;
	    long days       = TimeUnit.MILLISECONDS.toDays(duration);
	    long hours      = TimeUnit.MILLISECONDS.toHours(duration) -
	                      TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
	    long minutes    = TimeUnit.MILLISECONDS.toMinutes(duration) -
	                      TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
	    long seconds    = TimeUnit.MILLISECONDS.toSeconds(duration) -
	                      TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
	    long millis     = TimeUnit.MILLISECONDS.toMillis(duration) - 
	                      TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration));

	    if (days == 0)      res = String.format("%02d:%02d:%02d.%04d", hours, minutes, seconds, millis);
	    else                res = String.format("%dd %02d:%02d:%02d.%04d", days, hours, minutes, seconds, millis);
	    return res;
	}
}
