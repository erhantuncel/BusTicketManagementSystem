package com.erhan.busticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.dao.AdminDAO;
import com.erhan.busticket.model.Admin;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;
	
	@Override
	@Transactional
	public Long create(Admin admin) {
		Long id = adminDAO.create(admin);
		return id;
	}

	@Override
	@Transactional
	public Admin findByEmail(String email) {
		Admin admin = adminDAO.findByEmail(email);
		return admin;
	}

}
