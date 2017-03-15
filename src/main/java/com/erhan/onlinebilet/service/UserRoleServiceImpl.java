package com.erhan.onlinebilet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.UserRoleDAO;
import com.erhan.onlinebilet.model.UserRole;

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
