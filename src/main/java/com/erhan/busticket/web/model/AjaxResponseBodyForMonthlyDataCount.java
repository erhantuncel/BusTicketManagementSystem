package com.erhan.busticket.web.model;

import java.util.Arrays;

import com.erhan.busticket.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBodyForMonthlyDataCount {
	
	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private String code;
	
	@JsonView(Views.Public.class)
	private String[] data;
	
	public AjaxResponseBodyForMonthlyDataCount() {
		// TODO Auto-generated constructor stub
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

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AjaxResponseBodyForMonthlyDataCount [message=" + message + ", code=" + code + ", data=" + Arrays.toString(data) + "]";
	}
}
