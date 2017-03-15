package com.erhan.onlinebilet.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlinebilet.model.UserRole;

@Repository("userRoleDAO")
public class UserRoleDAOImpl implements UserRoleDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Long create(UserRole userRole) {
		sessionFactory.getCurrentSession().saveOrUpdate(userRole);
		return userRole.getId();
	}

}
