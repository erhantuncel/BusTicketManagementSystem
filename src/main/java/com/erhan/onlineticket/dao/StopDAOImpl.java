package com.erhan.onlineticket.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlineticket.model.Stop;

@Repository("stopDAO")
public class StopDAOImpl implements StopDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Long create(Stop stop) {
//		Long id = (Long) sessionFactory.getCurrentSession().save(stop);
//		return id;
		sessionFactory.getCurrentSession().saveOrUpdate(stop);
		return stop.getId();
	}
}
