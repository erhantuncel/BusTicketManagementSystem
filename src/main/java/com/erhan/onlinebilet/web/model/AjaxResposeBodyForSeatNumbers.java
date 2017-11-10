package com.erhan.onlinebilet.web.model;

import java.util.Map;

import com.erhan.onlinebilet.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResposeBodyForSeatNumbers {
	
	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private String code;
	
	@JsonView(Views.Public.class)
	private Map<Byte, String> seatNumbersAndGender;

	public AjaxResposeBodyForSeatNumbers() {
		// TODO Auto-generated constructor stub
	}
	
	public AjaxResposeBodyForSeatNumbers(String message, String code, Map<Byte, String> seatNumbersAndGender) {
		super();
		this.message = message;
		this.code = code;
		this.seatNumbersAndGender = seatNumbersAndGender;
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

	public Map<Byte, String> getSeatNumbersAndGender() {
		return seatNumbersAndGender;
	}

	public void setSeatNumbersAndGender(Map<Byte, String> seatNumbersAndGender) {
		this.seatNumbersAndGender = seatNumbersAndGender;
	}

	@Override
	public String toString() {
		return "AjaxResposeBodyForSeatNumbers [message=" + message + ", code=" + code + ", seatNumbersAndGender=" + seatNumbersAndGender + "]";
	}
}
