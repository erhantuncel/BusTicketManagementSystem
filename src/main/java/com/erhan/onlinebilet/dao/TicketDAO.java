package com.erhan.onlinebilet.dao;

import java.util.List;

import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.Voyage;

public interface TicketDAO {
	public Long create(Ticket ticket);
	public Ticket findById(Long id);
	public List<Ticket> findAll();
	public List<Ticket> findByCustomer(Customer customer);
	public int delete(Long id);
	public Integer countAll();
	public List<Object[]> countMonthly(Integer year);
	public List<Ticket> findByVoyage(Voyage voyage);
	public Integer countReservationByVoyage(Voyage voyage);
	public Integer countTicketByVoyage(Voyage voyage);
	public Ticket findByVoyageAndSeatNumber(Voyage voyage, Byte seatNumber);
	public List<Byte> findSeatNumbersByVoyage(Voyage voyage); 
	public void update(Ticket ticket);
}
