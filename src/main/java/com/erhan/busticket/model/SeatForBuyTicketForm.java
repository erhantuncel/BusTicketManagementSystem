package com.erhan.busticket.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.erhan.busticket.validator.TcNumber;

public class SeatForBuyTicketForm {
	
	@NotNull
	private Byte seatNumber;
	
	@NotNull
	private Gender passangerGender;
	
	@NotEmpty
	@TcNumber(message = "Geçerli bir T.C. Kimlik numarası giriniz.")
	private String passangerTcNumber;
	
	@NotEmpty
	private String passangerName;
	
	@NotEmpty
	private String passangerSurname;

	
	public SeatForBuyTicketForm() {
		
	}

	public Byte getSeatNumber() {
		return seatNumber;
	}


	public void setSeatNumber(Byte seatNumber) {
		this.seatNumber = seatNumber;
	}


	public Gender getPassangerGender() {
		return passangerGender;
	}


	public void setPassangerGender(Gender passangerGender) {
		this.passangerGender = passangerGender;
	}


	public String getPassangerTcNumber() {
		return passangerTcNumber;
	}


	public void setPassangerTcNumber(String passangerTcNumber) {
		this.passangerTcNumber = passangerTcNumber;
	}


	public String getPassangerName() {
		return passangerName;
	}


	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}


	public String getPassangerSurname() {
		return passangerSurname;
	}


	public void setPassangerSurname(String passangerSurname) {
		this.passangerSurname = passangerSurname;
	}


	@Override
	public String toString() {
		return "SeatForBuyTicketForm [seatNumber=" + seatNumber + ", passangerGender=" + passangerGender
				+ ", passangerTcNumber=" + passangerTcNumber + ", passangerName=" + passangerName
				+ ", passangerSurname=" + passangerSurname + "]";
	}
}
