package com.erhan.busticket.web.model;

import com.erhan.busticket.model.Vehicle;
import com.erhan.busticket.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBodyForVehicle {

	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private String code;
	
	@JsonView(Views.Public.class)
	private Vehicle vehicle;
	
	public AjaxResponseBodyForVehicle() {
		// TODO Auto-generated constructor stub
	}

	public AjaxResponseBodyForVehicle(String message, String code, Vehicle vehicle) {
		this.message = message;
		this.code = code;
		this.vehicle = vehicle;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "AjaxResponseBodyForVehicle [message=" + message + ", code=" + code + ", vehicle=" + vehicle + "]";
	}
}
