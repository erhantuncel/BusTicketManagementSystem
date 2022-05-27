package com.erhan.onlineticket.web.model;

import com.erhan.onlineticket.model.Ticket;
import com.erhan.onlineticket.web.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBodyForTicket {
	@JsonView(Views.Public.class)
	private String message;
	
	@JsonView(Views.Public.class)
	private String code;
	
	private Ticket ticket;

	public AjaxResponseBodyForTicket() {
		// TODO Auto-generated constructor stub
	}
	
	public AjaxResponseBodyForTicket(String message, String code, Ticket ticket) {
		super();
		this.message = message;
		this.code = code;
		this.ticket = ticket;
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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "AjaxResponseBodyForTicket [message=" + message + ", code=" + code + ", ticket=" + ticket + "]";
	}
}
