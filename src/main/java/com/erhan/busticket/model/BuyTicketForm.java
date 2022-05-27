package com.erhan.busticket.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class BuyTicketForm {
	
	@Valid
	private List<SeatForBuyTicketForm> seatList = new ArrayList<SeatForBuyTicketForm>(0);
	
	public BuyTicketForm() {
		
	}

	public List<SeatForBuyTicketForm> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<SeatForBuyTicketForm> seatList) {
		this.seatList = seatList;
	}
}
