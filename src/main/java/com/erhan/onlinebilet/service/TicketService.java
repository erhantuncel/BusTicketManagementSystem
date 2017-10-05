package com.erhan.onlinebilet.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.Voyage;

public interface TicketService {

	public Long create(Ticket ticket);
	public Ticket findById(Long id);
	public List<Ticket> findAll();
	public List<Ticket> findByCustomer(Customer customer);
	public List<Ticket> findForFutureByCustomer(Customer customer);
	public int delete(Long id);
	public Integer countAll();
	public List<String[]> countMonthly(Integer year);
	public List<Ticket> findByVoyage(Voyage voyage);
	public Integer countReservationByVoyage(Voyage voyage);
	public Integer countTicketByVoyage(Voyage voyage);
	public Ticket findByVoyageAndSeatNumber(Voyage voyage, Byte seatNumber);
	public List<Byte> findSeatNumbersByVoyage(Voyage voyage);
	public List<Byte> findSeatNumbersByVoyageAndArrival(Voyage voyage, City arrival);
	public Map<Byte, String> findSeatNumbersAndGenderByVoyageAndStop(Voyage voyage, City departure, City arrival);
	public Ticket[][] getTicketArray(Set<Ticket> ticketList);
	public void update(Ticket ticket);
	public BigDecimal calculateTicketPriceByDepartureAndArrival(City departure, City arrival);
}
