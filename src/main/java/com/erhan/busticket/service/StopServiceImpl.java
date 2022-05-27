package com.erhan.busticket.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.dao.StopDAO;
import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Stop;
import com.erhan.busticket.model.Voyage;

@Service("stopService")
public class StopServiceImpl implements StopService {

	@Autowired
	StopDAO stopDAO;
	
	@Override
	@Transactional
	public Long create(Stop stop) {
		Long id = stopDAO.create(stop);
		return id;
	}

	@Override
	@Transactional
	public Map<String, Date> getStopMapByVoyage(Voyage voyage) {
		Map<String, Date> stopMap = new LinkedHashMap<String, Date>(0);
		GregorianCalendar stopTime = new GregorianCalendar();
		stopTime.setTime(voyage.getDepartureTime());
		Integer lastStopDuration = 0;
		for(Stop stop : voyage.getRoute().getStops()) {
			if(stop.getDuration() != 0) {				
				stopTime.add(Calendar.MINUTE, stop.getDuration()-lastStopDuration);
			}
			stopMap.put(stop.getCity().getCityName(), stopTime.getTime());
			lastStopDuration = stop.getDuration();
		}
		return stopMap;
	}

	@Override
	@Transactional
	public Date getStopTimeByVoyageAndStopcity(Voyage voyage, City stopCity) {
		Date stopTime = null;
		Map<String, Date> stopMap = getStopMapByVoyage(voyage);
		for(String stopCityName : stopMap.keySet()) {
			if(stopCityName.equals(stopCity.getCityName())) {
				stopTime = stopMap.get(stopCityName);
			}
		}
		return stopTime;
	}
}
