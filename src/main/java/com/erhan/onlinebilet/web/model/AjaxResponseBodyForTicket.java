package com.erhan.onlinebilet.web.model;

import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBodyForTicket {
	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private String code;
	
	private Ticket response;

	public AjaxResponseBodyForTicket() {
		// TODO Auto-generated constructor stub
	}
	
	public AjaxResponseBodyForTicket(String message, String code, Ticket response) {
		super();
		this.message = message;
		this.code = code;
		this.response = response;
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

	public Ticket getResponse() {
		return response;
	}

	public void setResponse(Ticket response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "AjaxResponseBodyForTicket [message=" + message + ", code=" + code + ", response=" + response + "]";
	}
	
	
}
