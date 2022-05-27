package com.erhan.onlineticket.service;

import com.erhan.onlineticket.model.Admin;

public interface AdminService {
	public Long create(Admin admin);
	public Admin findByEmail(String email);
}
