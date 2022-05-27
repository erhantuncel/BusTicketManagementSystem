package com.erhan.busticket.service;

import com.erhan.busticket.model.Admin;

public interface AdminService {
	public Long create(Admin admin);
	public Admin findByEmail(String email);
}
