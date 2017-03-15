package com.erhan.onlinebilet.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.model.Admin;

@Repository("adminDAO")
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Long create(Admin admin) {
		sessionFactory.getCurrentSession().saveOrUpdate(admin);
		Long id = admin.getId();
		return id;
	}

	@Override
	@Transactional
	public Admin findByEmail(String email) {
		 Criteria crit = sessionFactory.getCurrentSession().createCriteria(Admin.class);
		 crit.add(Restrictions.eq("eMail", email));
		 Admin admin = (Admin)crit.uniqueResult();
		 return admin;
	}
}
