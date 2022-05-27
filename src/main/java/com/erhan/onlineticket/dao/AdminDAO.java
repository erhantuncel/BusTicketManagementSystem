package com.erhan.onlineticket.dao;

import com.erhan.onlineticket.model.Admin;

public interface AdminDAO {
	public Long create(Admin admin);
	public Admin findByEmail(String email);
}
