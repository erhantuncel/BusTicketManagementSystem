package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.Voyage;

public class TicketTest extends BaseTest {

	Calendar c = Calendar.getInstance();
	SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm");
	
	@Test
	public void testFindById() {
		
		renewTransaction();
		
		Ticket ticket = ticketService.findById(324L);
		assertNotNull(ticket);
		if(ticket.getIsReservation()) {
			System.out.println("Reservation Expire Date : " + df.format(ticket.getReservExpirationDate()));
		}
		System.out.println("Voyage Id: " + ticket.getVoyage().getId());
		System.out.println("Departure Time : " + df.format(ticket.getVoyage().getDepartureTime()));
		System.out.println("Route : " + ticket.getVoyage().getRoute().getRouteName());
		Customer customer = ticket.getCustomer();
		if(customer != null) {
			System.out.println("Customer : " + customer.getName() + " " + customer.getSurname());
			System.out.println("Customer email : " + customer.geteMail());
		}		
		System.out.println("Passanger Tc Number : " + ticket.getPassangerTcNumber());
		System.out.println("Passanger Name Surname : " + ticket.getPassangerName() + " " + ticket.getPassangerSurname());
		System.out.println("Passanger Gender : " + ticket.getPassangerGender().toString());
		System.out.println("Registered Time : " + df.format(ticket.getRegisterTime()));
	}
	
	@Test
	public void testfindAll() {
		renewTransaction();
		
		List<Ticket> ticketList = ticketService.findAll();
		System.out.println("Number of Tickets = " + ticketList.size());
//		assertEquals(ticketList.size(), 3);
//		for(Ticket t : ticketList) {
//			System.out.print("ID = " + t.getId());
//			if(t.getCustomer() != null) {
//				System.out.print("\tCustomer Name = " + t.getCustomer().getName());
//			} else {
//				System.out.print("\tCustomer Name = -");
//			}
//			System.out.println();
//		}
	}
	
	@Test
	public void testFindByCustomer() {
		renewTransaction();
		
		Customer customer30 = customerService.findById(30L);
		
		List<Ticket> ticketList = ticketService.findByCustomer(customer30);
		assertNotNull(ticketList);
		System.out.println("Ticket counts : " + ticketList.size());
	}
	
	@Test
	public void testCountAll() {
		renewTransaction();
		
		Integer count = ticketService.countAll();
		assertNotEquals(count, new Integer("0"));
		System.out.println("Ticket count : " + count);
	}

	@Test
	public void testCountMonthly() {
		renewTransaction();
		
		List<String[]> countList = ticketService.countMonthly(2016);
		assertEquals(countList.size(), 12);
		for(String[] o : countList) {
			System.out.println(o[0] + "\t" + o[1]);
		}
	}
	
	@Test
	public void testFindByVoyage() {
		renewTransaction();
		
		Voyage voyage10 = voyageService.findById(10L);
		List<Ticket> tickets = ticketService.findByVoyage(voyage10);
		assertNotNull(tickets);
		Set<Ticket> ticketSet = new LinkedHashSet<Ticket>(tickets);
		System.out.println("Voyage ID : " + voyage10.getId().toString());
		System.out.println("Departure Time : " + df.format(voyage10.getDepartureTime()));
		System.out.println("Ticket count for " + voyage10.getRoute().getRouteName() + " :" + ticketSet.size());
		for(Ticket t : ticketSet ) {
			System.out.println("\t" + t.getId() + 
								"\t" + t.getSeatNumber() + 
								"\t" + t.getPassangerTcNumber() + 
								"\t" + t.getPassangerName() + 
								"\t" + t.getPassangerGender());
		}
	}
	
	@Test
	public void testCountReservationByVoyage() {
		renewTransaction();
		
		Voyage voyage30 = voyageService.findById(30L);
		List<Ticket> tickets = ticketService.findByVoyage(voyage30);
		assertNotNull(tickets);
		Set<Ticket> ticketSet = new LinkedHashSet<Ticket>(tickets);
		Integer countOfReservation = 0;
		for(Ticket t : ticketSet) {
			if(t.getIsReservation()) {
				countOfReservation = countOfReservation + 1;
			}
		}

		Integer count = ticketService.countReservationByVoyage(voyage30);
		assertNotNull(count);
		assertEquals(count, countOfReservation);
		System.out.println("Count of reservation for " + voyage30.getRoute().getRouteName() + " : " + count);
		
	}
	
	@Test
	public void testCountTicketByVoyage() {
		renewTransaction();
		
		Voyage voyage30 = voyageService.findById(30L);
		List<Ticket> tickets = ticketService.findByVoyage(voyage30);
		assertNotNull(tickets);
		Set<Ticket> ticketSet = new LinkedHashSet<Ticket>(tickets);
		Integer countOfTicket = 0;
		for(Ticket t : ticketSet) {
			if(!t.getIsReservation()) {
				countOfTicket = countOfTicket + 1;
			}
		}

		Integer count = ticketService.countTicketByVoyage(voyage30);
		assertNotNull(count);
		assertEquals(count, countOfTicket);
		System.out.println("Count of total Ticket/Reservation : " + ticketSet.size());
		System.out.println("Count of ticket for " + voyage30.getRoute().getRouteName() + " : " + count);
		
	}
	
	@Test
	public void testFindByVoyageAndSeatNumber() {
		renewTransaction();
		
		Ticket ticket144 = ticketService.findById(144L);
		assertNotNull(ticket144);
		
		renewTransaction();
		
		Ticket ticketForTest = ticketService.findByVoyageAndSeatNumber(ticket144.getVoyage(), ticket144.getSeatNumber());
		assertNotNull(ticketForTest);
		assertEquals(ticket144.getPassangerTcNumber(), ticketForTest.getPassangerTcNumber());
	}
	
	@Test
	public void testFindSeatNumbersByVoyage() {
		renewTransaction();
		
		Voyage voyage18 = voyageService.findById(18L);
		
		List<Byte> seatNumbers = ticketService.findSeatNumbersByVoyage(voyage18);
		assertNotNull(seatNumbers);
		System.out.println("Seat Numbers Count : " + seatNumbers.size());
		for(Byte b : seatNumbers) {
			System.out.print("Seat Number : " + b + "\n");
		}
	}
	
	@Test
	public void testGetTicketArray() {
		renewTransaction();
		
		Voyage voyage10 = voyageService.findById(10L);
		Set<Ticket> ticketSet = new LinkedHashSet<Ticket>(ticketService.findByVoyage(voyage10));
//		for(Ticket ticket : ticketSet) {
//			System.out.println(ticket.getSeatNumber() + " - " + ticket.getPassangerGender().toString());
//		}
		System.out.println("ticketSet size = " + ticketSet.size());
		Ticket[][] ticketArray = ticketService.getTicketArray(ticketSet);
		assertNotNull(ticketArray);
		for(int i=0; i<ticketArray.length; i++) {
			for(int j=0; j<ticketArray[i].length; j++) {
				if(ticketArray[i][j] != null) {					
					System.out.print(ticketArray[i][j].getPassangerGender().toString() + "\t");
				} else {
					System.out.print("null\t");
				}
			}
			System.out.print("\n");
		}
		
		
		
	}
	
	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
