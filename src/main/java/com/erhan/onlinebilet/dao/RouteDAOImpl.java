package com.erhan.onlinebilet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Stop;

@Repository("routeDAO")
public class RouteDAOImpl implements RouteDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Long create(Route route) {
//		Long id = (Long) sessionFactory.getCurrentSession().save(route);
//		return id;
		
		sessionFactory.getCurrentSession().saveOrUpdate(route);
		return route.getId();
	}

	@Override
	public Route findById(Long id) {
		Route route = sessionFactory.getCurrentSession().get(Route.class, id);
		return route;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Route");
		List<Route> routeList = query.list();
		return routeList;
	}

	@Override
	public Route findByRouteName(String routeName) {
		String hql = "from Route where routeName=:routeName";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("routeName", routeName);
		Route route = (Route) query.uniqueResult();
		return route;
	}

	@Override
	public List<Route> findAllByDepartureAndArrival(City departure, City arrival) {
		List<Route> allRoutes = this.findAll();
		List<Route> foundRoutes = new ArrayList<Route>();
		for(Route route : allRoutes) {
			Set<Stop> stopSet = route.getStops();
			boolean departureFind = false;
			boolean arrivalFind = false;
			for(Stop stop : stopSet) {
				if(!departureFind) {
					if(stop.getCity() != departure) {
						continue;
					} else {
						departureFind = true;
						continue;
					}
				} else {
					if(stop.getCity() != arrival) {
						continue;
					} else {
						arrivalFind = true;
						break;
					}
				}
			}
			if(departureFind & arrivalFind) {
				foundRoutes.add(route);
			}
		}
		return foundRoutes;
	}
}