package com.erhan.onlinebilet.utilities;

import java.util.Date;

public class ObjForConvertToJson {
	
	private int id;
	
	private String name;
	
	private Date registerDate;
	
	public ObjForConvertToJson() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "ObjForConvertToJson [id=" + id + ", name=" + name + ", registerDate=" + registerDate + "]";
	}
}
