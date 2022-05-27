package com.erhan.onlineticket.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlineticket.model.Vehicle;

@Repository("vehicleDAO")
public class VehicleDAOImpl implements VehicleDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Long create(Vehicle vehicle) {
//		Long id = (Long) sessionFactory.getCurrentSession().save(vehicle);
//		return id;
		
		sessionFactory.getCurrentSession().saveOrUpdate(vehicle);
		return vehicle.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Vehicle");
		List<Vehicle> vehicleList = query.list();
		return vehicleList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LinkedList<Long> findAllIds() {
		String hql = "select v.id from Vehicle v";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		LinkedList<Long> ids = (LinkedList<Long>)query.list();
		return ids;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicle> findAllSortedByYear() {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Vehicle.class);
		crt.addOrder(Order.desc("year"));
		List<Vehicle> vehicleList = crt.list();
		return vehicleList;
	}

	@Override
	public Vehicle findByPlateCode(String plateCode) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Vehicle.class);
		crt.add(Restrictions.eq("plateCode", plateCode));
		Vehicle vehicle = (Vehicle) crt.uniqueResult();
		return vehicle;
	}

	@Override
	public Vehicle findById(Long id) {
		Vehicle vehicle = (Vehicle) sessionFactory.getCurrentSession().get(Vehicle.class, id);
		return vehicle;
	}

	@Override
	public void update(Vehicle vehicle) {
		sessionFactory.getCurrentSession().update(vehicle);
	}

	@Override
	public void delete(Vehicle vehicle) {
		sessionFactory.getCurrentSession().delete(vehicle);
	}
}
