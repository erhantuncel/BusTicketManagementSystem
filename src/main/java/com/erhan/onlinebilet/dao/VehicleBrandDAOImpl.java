package com.erhan.onlinebilet.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlinebilet.model.VehicleBrand;

@Repository("vehicleBrandDAO")
public class VehicleBrandDAOImpl implements VehicleBrandDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Long create(VehicleBrand vehicleBrand) {
//		Long id = (Long) sessionFactory.getCurrentSession().save(vehicleBrand);
//		return id;
		
		sessionFactory.getCurrentSession().saveOrUpdate(vehicleBrand);
		return vehicleBrand.getId();
	}

	@Override
	public VehicleBrand findById(Long id) {
		VehicleBrand brand = sessionFactory.getCurrentSession().get(VehicleBrand.class, id);
		return brand;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleBrand> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from VehicleBrand");
		List<VehicleBrand> brandList = query.list();
		return brandList;
	}

	@Override
	public VehicleBrand findByName(String name) {
		String hql = "from VehicleBrand where name=:name";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("name", name);
		VehicleBrand brand = (VehicleBrand) query.uniqueResult();
		return brand;
	}
}
