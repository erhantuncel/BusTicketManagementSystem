package com.erhan.onlinebilet.web.model;

import java.util.Map;

import com.erhan.onlinebilet.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBodyForVehicleModelMap {

	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private String code;
	
	@JsonView(Views.Public.class)
	private Map<String, String> modelMap;
	
	public AjaxResponseBodyForVehicleModelMap() {
		// TODO Auto-generated constructor stub
	}

	public AjaxResponseBodyForVehicleModelMap(String message, String code, Map<String, String> modelMap) {
		this.message = message;
		this.code = code;
		this.modelMap = modelMap;
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

	public Map<String, String> getModelMap() {
		return modelMap;
	}

	public void setModelMap(Map<String, String> modelMap) {
		this.modelMap = modelMap;
	}

	@Override
	public String toString() {
		return "AjaxResponseBodyForVehicleModelMap [message=" + message + ", code=" + code + ", modelMap=" + modelMap + "]";
	}
}
