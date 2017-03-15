package com.erhan.onlinebilet.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.VoyageDAO;
import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Stop;
import com.erhan.onlinebilet.model.Voyage;

@Service("voyageService")
public class VoyageServiceImpl implements VoyageService {

	@Autowired
	VoyageDAO voyageDAO;
	
	@Override
	@Transactional
	public Long create(Voyage voyage) {
		Long id = voyageDAO.create(voyage);
		return id;
	}

	@Override
	@Transactional
	public Voyage findById(Long id) {
		Voyage voyage = voyageDAO.findById(id);
		return voyage;
	}

	@Override
	@Transactional
	public List<Voyage> findAll() {
		List<Voyage> voyageList = voyageDAO.findAll();
		return voyageList;
	}

	@Override
	@Transactional
	public void update(Voyage voyage) {
		voyageDAO.update(voyage);
	}

	@Override
	@Transactional
	public List<Voyage> findAllByDate(Date date) {
		List<Voyage> voyageList = voyageDAO.findAllByDate(date);
		return voyageList;
	}

	@Override
	@Transactional
	public List<Voyage> findAllByRouteAndDate(List<Route> routeList, Date date) {
		List<Voyage> voyageList = new ArrayList<Voyage>();
		for(Route route : routeList) {
			voyageList.addAll(voyageDAO.findAllByRouteAndDate(route, date));
		}
		Collections.sort(voyageList);
		return voyageList;
	}

	@Override
	@Transactional
	public Map<Voyage, String> findAllStartedInHourWithCompletionPercentage(Integer hour) {
		Date now = new Date();
		Map<Voyage, String> voyageMap = new LinkedHashMap<Voyage, String>(0);
		List<Voyage> voyageList = voyageDAO.findAllStartedInHour(hour);
		Set<Voyage> voyageSet = new LinkedHashSet<Voyage>(voyageList);
		for(Voyage voyage : voyageSet) {
			Set<Stop> stopSet = voyage.getRoute().getStops();
			Stop lastStop = (Stop) stopSet.toArray()[stopSet.size()-1];
			GregorianCalendar arrivalTime = new GregorianCalendar();
			arrivalTime.setTime(voyage.getDepartureTime());
			arrivalTime.add(Calendar.MINUTE, lastStop.getDuration());
			long remainMilliSecond = arrivalTime.getTimeInMillis() - now.getTime();
			if(remainMilliSecond > 0L) {
				long minutesInMilli = 60 * 1000;
				long hoursInMilli = minutesInMilli * 60;
				long remainHours = remainMilliSecond / hoursInMilli;
				remainMilliSecond = remainMilliSecond % hoursInMilli;
				long remainMinutes = remainMilliSecond / minutesInMilli;
				String remainTime = remainHours + " sa " + remainMinutes + " dk.";
				voyageMap.put(voyage, remainTime);
			}
		}
		return voyageMap;
	}

	@Override
	@Transactional
	public List<Voyage> findAllSortedByDepartureTimeForRecords(Integer count) {
		List<Voyage> voyageList = voyageDAO.findAllSortedByDepartureTimeForRecords(count);
		return voyageList;
	}

	@Override
	@Transactional
	public void delete(Voyage voyage) {
		voyageDAO.delete(voyage);
	}
}
