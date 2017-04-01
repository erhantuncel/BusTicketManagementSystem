package com.erhan.onlinebilet.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.service.TicketService;
import com.erhan.onlinebilet.web.model.AjaxResponseBodyForTicket;


@RestController
public class AjaxController {

	@Autowired
	TicketService ticketService;
	
	@RequestMapping(value = "/admin/biletDetay/{id}")
	public AjaxResponseBodyForTicket getTicketSearchResultById2(@PathVariable(value="id") String id) {
		
		AjaxResponseBodyForTicket result = new AjaxResponseBodyForTicket();
		
		Ticket ticket = ticketService.findById(Long.valueOf(id));
		
		result.setCode("200");
		result.setMessage("");
		result.setResponse(ticket);
		
		return result;
	}
}
