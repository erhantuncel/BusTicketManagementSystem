package com.erhan.onlineticket.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlineticket.model.City;
import com.erhan.onlineticket.model.CityDistance;

@Repository("cityDistanceDAO")
public class CityDistanceDAOImpl implements CityDistanceDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Long create(CityDistance cityDistance) {
		sessionFactory.getCurrentSession().saveOrUpdate(cityDistance);
		return cityDistance.getId();
	}

	@Override
	public CityDistance findById(Long id) {
		CityDistance cityDistance = sessionFactory.getCurrentSession().get(CityDistance.class, id);
		return cityDistance;
	}

	@Override
	public CityDistance findByDepartureAndArrival(City departure, City arrival) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(CityDistance.class);
		if(departure.getId() < arrival.getId()) {
			crt.add(Restrictions.eq("departure", departure));
			crt.add(Restrictions.eq("arrival", arrival));			
		} else {
			crt.add(Restrictions.eq("departure", arrival));
			crt.add(Restrictions.eq("arrival", departure));
		}
		return (CityDistance) crt.uniqueResult();
	}
}
