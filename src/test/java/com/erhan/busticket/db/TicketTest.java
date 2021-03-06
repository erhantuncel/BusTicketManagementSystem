package com.erhan.busticket.db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Customer;
import com.erhan.busticket.model.Stop;
import com.erhan.busticket.model.Ticket;
import com.erhan.busticket.model.Voyage;

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
	public void testFindFutureByCustomer() {
		renewTransaction();
		
		Customer customer2 = customerService.findById(2L);
		List<Ticket> ticketList = ticketService.findForFutureByCustomer(customer2);
		assertNotNull(ticketList);
		System.out.println("Ticket counts : " + ticketList.size());
		for(Ticket ticket : ticketList) {
			System.out.println("Id = " + ticket.getId().toString() + " time = " + ticket.getVoyage().getDepartureTime());
		}
	}
	
	@Test
	public void testDelete() {
		renewTransaction();
		
		int result = ticketService.delete(324L);
		assertEquals(1, result);
		
		Ticket deletedTicket = ticketService.findById(324L);
		assertNull(deletedTicket);
//		System.out.println("Id = " + ticket324.getId().toString() + "\tYolcu Ad = " + ticket324.getPassangerName());
		
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
		
		List<String[]> countList = ticketService.countMonthly(2017);
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
	public void testFindSeatNumbersByVoyageAndDeparture() {
		renewTransaction();
		
		Voyage voyage14 = voyageService.findById(14L);
		City arrival = null;
		int stopCount = voyage14.getRoute().getStops().size();
		int i = 0;
		for(Stop stop : voyage14.getRoute().getStops()) {
			if(i == stopCount-1) {
				arrival = stop.getCity();
			}
			i++;
		}
		System.out.println("Arrival = " + arrival.getCityName());
		
		List<Byte> seatNumbersFromService = ticketService.findSeatNumbersByVoyageAndArrival(voyage14, arrival);
		
		List<Ticket> ticketListForVoyage14 = ticketService.findByVoyage(voyage14);
		System.out.println("Ticket Size = " + ticketListForVoyage14.size());
		List<Byte> seatNumbers = new ArrayList<Byte>();
		for(Ticket ticket : ticketListForVoyage14) {
//			System.out.println("Id = " + ticket.getId() + " SeatNumber = " + ticket.getSeatNumber() 
//						+ "Departure = " + ticket.getDeparture().getCityName()
//						+ " Arrival = " + ticket.getArrival().getCityName()); 
			if(ticket.getArrival().getCityName().equals(arrival.getCityName())) {
				seatNumbers.add(ticket.getSeatNumber());
			}
		}
		System.out.println("Seat Numbers Count - Service = " + seatNumbersFromService.size());
		System.out.println("Seat Numbers Count - Loop = " + seatNumbers.size()); 
		assertEquals(seatNumbersFromService.size(), seatNumbers.size());
		
	}
	
	@Test
	public void testFindSeatNumberAndGenderByVoyageAndStop() {
		renewTransaction();
		
		Voyage voyage15 = voyageService.findById(15L);
		Set<Stop> stopSet = voyage15.getRoute().getStops();
		Stop[] stopArray = (Stop[]) voyage15.getRoute().getStops().toArray(new Stop[stopSet.size()]);
		City departureCity = stopArray[1].getCity();
		City arrivalCity = stopArray[stopArray.length-2].getCity();
		System.out.println("Route Name = " + voyage15.getRoute().getRouteName());
		System.out.println("Departure = " + departureCity.getCityName() + "\t Arrival = " + arrivalCity.getCityName());
		Map<Byte, String> seatNumberAndGenderMap = ticketService.findSeatNumbersAndGenderByVoyageAndStop(voyage15, departureCity, arrivalCity);
		
		for(Map.Entry<Byte, String> entry : seatNumberAndGenderMap.entrySet()) {
			System.out.println("Seat Number : " + entry.getKey() + " Gender = " + entry.getValue());
		}
		System.out.println("Seat Count = " + seatNumberAndGenderMap.size());
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
	
	@Test
	public void testUpdate() {
		renewTransaction();
		
		Ticket ticket3 = ticketService.findById(3L);
		ticket3.setPrice(new BigDecimal("100.00"));
		ticketService.update(ticket3);
		
		renewTransaction();
		Ticket ticketTest = ticketService.findById(3L);
		assertEquals(ticketTest.getPrice(), new BigDecimal("100.00"));
	}
	
	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
