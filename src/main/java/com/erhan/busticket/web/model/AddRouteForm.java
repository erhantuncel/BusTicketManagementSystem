package com.erhan.busticket.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AddRouteForm {
	
	@NotEmpty
	private String routeName;
	
	@NotNull
	private List<String> stopList;
	
	public AddRouteForm() {
		// TODO Auto-generated constructor stub
	}

	public AddRouteForm(String routeName, List<String> stopList) {
		super();
		this.routeName = routeName;
		this.stopList = stopList;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public List<String> getStopList() {
		return stopList;
	}

	public void setStopList(List<String> stopList) {
		this.stopList = stopList;
	}

	@Override
	public String toString() {
		return "AddRouteForm [routeName=" + routeName + ", stopList=" + stopList + "]";
	}
}
