package com.erhan.busticket.service;

import java.util.Date;
import java.util.Map;

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Stop;
import com.erhan.busticket.model.Voyage;

public interface StopService {
	public Long create(Stop stop);
	public Map<String, Date> getStopMapByVoyage(Voyage voyage);
	public Date getStopTimeByVoyageAndStopcity(Voyage voyage, City stopCity);
}
