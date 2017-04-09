package com.erhan.onlinebilet.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.TicketDAO;
import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.Voyage;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDAO ticketDAO;
	
	@Override
	@Transactional
	public Long create(Ticket ticket) {
		if(ticket.getIsReservation() == true) {
			Calendar c = Calendar.getInstance();
			c.setTime(ticket.getRegisterTime());
			c.add(Calendar.DATE, 3);
			ticket.setReservExpirationDate(c.getTime()); 
		}
		Long id = ticketDAO.create(ticket);
		return id;
	}

	@Override
	@Transactional
	public Ticket findById(Long id) {
		Ticket ticket = ticketDAO.findById(id);
		return ticket;
	}

	@Override
	@Transactional
	public List<Ticket> findAll() {
		List<Ticket> ticketList = ticketDAO.findAll();
		return ticketList;
	}

	@Override
	@Transactional
	public List<Ticket> findByCustomer(Customer customer) {
		List<Ticket> ticketList = ticketDAO.findByCustomer(customer);
		return ticketList;
	}

	@Override
	@Transactional
	public int delete(Long id) {
		int result = ticketDAO.delete(id);
		return result;
	}

	@Override
	@Transactional
	public Integer countAll() {
		Integer count = ticketDAO.countAll();
		return count;
	}

	@Override
	@Transactional
	public List<String[]> countMonthly(Integer year) {
		List<Object[]> ticketCountMonthly = ticketDAO.countMonthly(year);
		List<String[]> monthlyCountList = new ArrayList<String[]>();
		for(int i=1; i<=12; i++) {
			String countForMonth[] = new String[2];
			countForMonth[0] = String.valueOf(i);
			for(Object[] countOfTicket : ticketCountMonthly) {
				if(i == Integer.parseInt(countOfTicket[0].toString())) {
					countForMonth[1] = countOfTicket[1].toString();
					break;
				}
			}
			if(countForMonth[1] == null) {
				countForMonth[1] = "0";
			}
			monthlyCountList.add(countForMonth);
		}
		return monthlyCountList;
	}

	@Override
	@Transactional
	public List<Ticket> findByVoyage(Voyage voyage) {
		List<Ticket> tickets = ticketDAO.findByVoyage(voyage);
		return tickets;
	}

	@Override
	@Transactional
	public Integer countReservationByVoyage(Voyage voyage) {
		Integer count = ticketDAO.countReservationByVoyage(voyage);
		return count;
	}

	@Override
	@Transactional
	public Integer countTicketByVoyage(Voyage voyage) {
		Integer count = ticketDAO.countTicketByVoyage(voyage);
		return count;
	}

	@Override
	@Transactional
	public Ticket findByVoyageAndSeatNumber(Voyage voyage, Byte seatNumber) {
		Ticket ticket = ticketDAO.findByVoyageAndSeatNumber(voyage, seatNumber);
		return ticket;
	}

	@Override
	@Transactional
	public List<Byte> findSeatNumbersByVoyage(Voyage voyage) {
		List<Byte> seatNumbers = ticketDAO.findSeatNumbersByVoyage(voyage);
		return seatNumbers;
	}

	@Override
	@Transactional
	public Ticket[][] getTicketArray(Set<Ticket> ticketSet) {	
		Ticket[][] ticketArray = new Ticket[13][3];
		Byte seatNumber = 1;
		for(int i=0; i<ticketArray.length; i++) {
			for(int j=0; j<ticketArray[i].length; j++) {
				if(i==7 && j>0) {
					ticketArray[i][j] = null;
					continue;
				}
		        for(Ticket ticket : ticketSet) {
		        	Byte ticketSeatNumber = ticket.getSeatNumber();
//		        	System.out.println("Ticket Seat Number = " + ticketSeatNumber); 
					if(ticketSeatNumber == seatNumber) {
						ticketArray[i][j] = ticket;
						break;
					}
				}
				seatNumber++;
			}
		}
		return ticketArray;
	}
}
