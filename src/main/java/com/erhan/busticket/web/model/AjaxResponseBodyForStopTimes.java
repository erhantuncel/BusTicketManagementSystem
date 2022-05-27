package com.erhan.busticket.web.model;

import java.util.Date;
import java.util.Map;

import com.erhan.busticket.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class AjaxResponseBodyForStopTimes {
	
	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private String code;
	
	@JsonView(Views.Public.class)
	@JsonFormat(shape=Shape.STRING, pattern="HH:mm", timezone="Europe/Istanbul")
	private Map<String, Date> stopMap;

	public String getMessage() {
		return message;
	}
	
	public AjaxResponseBodyForStopTimes() {
		// TODO Auto-generated constructor stub
	}

	public AjaxResponseBodyForStopTimes(String message, String code, Map<String, Date> stopMap) {
		this.message = message;
		this.code = code;
		this.stopMap = stopMap;
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

	public Map<String, Date> getStopMap() {
		return stopMap;
	}

	public void setStopMap(Map<String, Date> stopMap) {
		this.stopMap = stopMap;
	}
	
	

}
