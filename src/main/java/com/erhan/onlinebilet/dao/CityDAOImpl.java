package com.erhan.onlinebilet.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlinebilet.model.City;

@Repository("cityDAO")
public class CityDAOImpl implements CityDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public City findById(Long id) {
		City city = sessionFactory.getCurrentSession().get(City.class, id);
		return city;
	}

	@Override
	public Long create(City city) {
//		Long id = (Long) sessionFactory.getCurrentSession().save(city);
//		return id;
		sessionFactory.getCurrentSession().saveOrUpdate(city);
		return city.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from City");
		List<City> cityList = query.list();
		return cityList;
	}

	@Override
	public void update(City city) {
		sessionFactory.getCurrentSession().update(city);
	}
	
	
}
