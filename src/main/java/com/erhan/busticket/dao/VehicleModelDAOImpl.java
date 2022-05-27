package com.erhan.busticket.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.busticket.model.VehicleModel;

@Repository("vehicleModelDAO")
public class VehicleModelDAOImpl implements VehicleModelDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleModel> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from VehicleModel");
		List<VehicleModel> modelList = query.list();
		return modelList;
	}

	@Override
	public VehicleModel findById(Long id) {
		VehicleModel model = sessionFactory.getCurrentSession().get(VehicleModel.class, id);
		return model;
	}
}
