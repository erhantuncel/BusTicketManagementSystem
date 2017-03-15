package com.erhan.onlinebilet.service;

import java.util.Date;
import java.util.Map;

import com.erhan.onlinebilet.model.Stop;
import com.erhan.onlinebilet.model.Voyage;

public interface StopService {
	public Long create(Stop stop);
	public Map<String, Date> getStopMapByVoyage(Voyage voyage);
}
