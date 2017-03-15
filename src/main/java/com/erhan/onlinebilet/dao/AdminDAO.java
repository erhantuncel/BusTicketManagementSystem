package com.erhan.onlinebilet.dao;

import com.erhan.onlinebilet.model.Admin;

public interface AdminDAO {
	public Long create(Admin admin);
	public Admin findByEmail(String email);
}
