package com.erhan.busticket.dao;

import com.erhan.busticket.model.Admin;

public interface AdminDAO {
	public Long create(Admin admin);
	public Admin findByEmail(String email);
}
