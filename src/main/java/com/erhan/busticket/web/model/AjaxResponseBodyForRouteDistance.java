package com.erhan.busticket.web.model;

import com.erhan.busticket.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBodyForRouteDistance {
	
	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private String code;
	
	@JsonView(Views.Public.class)
	private Integer distance;
	
	@JsonView(Views.Public.class)
	private String duration;

	public AjaxResponseBodyForRouteDistance() {
		// TODO Auto-generated constructor stub
	}

	public AjaxResponseBodyForRouteDistance(String message, String code, Integer distance, String duration) {
		super();
		this.message = message;
		this.code = code;
		this.distance = distance;
		this.duration = duration;
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

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "AjaxResponseBodyForRouteDistance [message=" + message + ", code=" + code + ", distance=" + distance + ", duration=" + duration + "]";
	}
}
