package com.erhan.onlineticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlineticket.dao.UserRoleDAO;
import com.erhan.onlineticket.model.UserRole;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDAO userDAO;
	
	@Override
	@Transactional
	public Long create(UserRole userRole) {
		return userDAO.create(userRole);
	}

}
