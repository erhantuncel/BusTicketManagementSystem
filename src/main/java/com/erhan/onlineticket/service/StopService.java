package com.erhan.onlineticket.service;

import java.util.Date;
import java.util.Map;

import com.erhan.onlineticket.model.City;
import com.erhan.onlineticket.model.Stop;
import com.erhan.onlineticket.model.Voyage;

public interface StopService {
	public Long create(Stop stop);
	public Map<String, Date> getStopMapByVoyage(Voyage voyage);
	public Date getStopTimeByVoyageAndStopcity(Voyage voyage, City stopCity);
}
