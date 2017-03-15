package com.erhan.onlinebilet.service;

import com.erhan.onlinebilet.model.Admin;

public interface AdminService {
	public Long create(Admin admin);
	public Admin findByEmail(String email);
}
