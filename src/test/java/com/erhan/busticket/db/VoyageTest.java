package com.erhan.busticket.db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.busticket.dao.VoyageDAO;
import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Expense;
import com.erhan.busticket.model.Route;
import com.erhan.busticket.model.Stop;
import com.erhan.busticket.model.Voyage;
import com.erhan.busticket.service.CityService;
import com.erhan.busticket.service.ExpenseService;
import com.erhan.busticket.service.ExpenseTypeService;
import com.erhan.busticket.service.StopService;
import com.erhan.busticket.service.TicketService;
import com.erhan.busticket.service.VehicleBrandService;
import com.erhan.busticket.service.VoyageService;


public class VoyageTest extends BaseTest {

	@Autowired
	VehicleBrandService vehicleBrandService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	StopService stopService;
	
	@Autowired
	VoyageService voyageService;
	
	@Autowired
	ExpenseTypeService expenseTypeService;
	
	@Autowired
	ExpenseService expenseService;
	
//	@Autowired
//	TicketService ticketService;
	
	
//	
//	private Long[] ids;
//	
//	@BeforeTransaction
//	public void populateData() {
//		
//		// Vehicles
//		VehicleBrand mercedes = new VehicleBrand("Mercedes");
//		VehicleModel travego17SHD = new VehicleModel("Travego 17 SHD", mercedes);
//		mercedes.getVehicleModelList().add(travego17SHD);
//		vehicleBrandService.create(mercedes);
//		
//		VehicleBrand neoplan = new VehicleBrand("Neoplan");
//		VehicleModel cityLiner = new VehicleModel("Cityliner", neoplan);
//		neoplan.getVehicleModelList().add(cityLiner);
//		vehicleBrandService.create(neoplan); 
//		
//		VehicleBrand setra = new VehicleBrand("Setra");
//		VehicleModel s516HD = new VehicleModel("S 516 HD", setra);
//		setra.getVehicleModelList().add(s516HD);
//		vehicleBrandService.create(setra);
//		
//		
//		Vehicle mercedesTravego = new Vehicle("14AJ779", 37, travego17SHD, "2014", 84526);
//		Vehicle setraS516HD = new Vehicle("14DS943", 37, s516HD, "2012", 105478);
//		Vehicle neoplanCityLiner = new Vehicle("14AS875", 37, cityLiner, "2009", 165720);
//		
//		// Cities
//		City ankara = cityService.findById(06L);
//		City balikesir = cityService.findById(10L);
//		City bursa = cityService.findById(16L);
//		City bolu = cityService.findById(14L);
//		City istanbul = cityService.findById(34L);
//		City izmir = cityService.findById(35L);
//		City kocaeli = cityService.findById(41L);
//		City manisa = cityService.findById(45L);
//		City sakarya = cityService.findById(54L);
//		City duzce = cityService.findById(81L);
//		
//		// Routes
//		Route route1 = new Route("Ankara-Kocaeli");
//		Route route2 = new Route("Ankara-Düzce");
//		Route route3 = new Route("İstanbul-İzmir");
//		
//		// Stops
//		
//		Stop stop = new Stop(route1, ankara, 0);
//		stopService.create(stop);
//		stop = new Stop(route1, bolu, 135);
//		stopService.create(stop);
//		stop = new Stop(route1, sakarya, 225);
//		stopService.create(stop);
//		stop = new Stop(route1, kocaeli, 270);
//		stopService.create(stop);
//		stop = new Stop(route2, ankara, 0);
//		stopService.create(stop);
//		stop = new Stop(route2, bolu, 135);
//		stopService.create(stop);
//		stop = new Stop(route2, duzce, 180);
//		stopService.create(stop);
//		stop = new Stop(route3, istanbul, 0);
//		stopService.create(stop);
//		stop = new Stop(route3, bursa, 270);
//		stopService.create(stop);
//		stop = new Stop(route3, balikesir, 435);
//		stopService.create(stop);
//		stop = new Stop(route3, manisa, 510);
//		stopService.create(stop);
//		stop = new Stop(route3, izmir, 600);
//		stopService.create(stop);
//		
//		ExpenseType fuelExpense = expenseTypeService.findById(1L);
//		ExpenseType snackExpense = expenseTypeService.findById(3L);
//		ExpenseType terminalExpense = expenseTypeService.findById(4L);
//		
//		Calendar c = Calendar.getInstance();
//		
//		c.set(2016, 4, 15, 11, 00, 00);
//		Date departureTime = c.getTime();
//		c.set(2016, 4, 8, 9, 15, 00);
//		Date registerTime = c.getTime();
//		Voyage voyage1 = new Voyage(setraS516HD, route1, departureTime, new BigDecimal("38.00"), registerTime);
//		Long id1 = voyageService.create(voyage1);
//		
//		c.set(2016, 4, 17, 20, 00, 00);
//		departureTime = c.getTime();
//		c.set(2016, 4, 11, 21, 4, 00);
//		registerTime = c.getTime();
//		Voyage voyage2 = new Voyage(neoplanCityLiner, route2, departureTime, new BigDecimal("33.00"), registerTime);
//		Expense fuelExp2 = new Expense(new BigDecimal("500.00"), fuelExpense);
//		Expense snackExp2 = new Expense(new BigDecimal("150.00"), snackExpense);
//		Expense terminalExp2 = new Expense(new BigDecimal("50.00"), terminalExpense);
//		voyage2.getExpenseList().add(fuelExp2);
//		voyage2.getExpenseList().add(snackExp2);
//		voyage2.getExpenseList().add(terminalExp2);
//		Long id2 = voyageService.create(voyage2);
//		
//		c.set(2016, 4, 17, 22, 00, 00);
//		departureTime = c.getTime();
//		c.set(2016, 4, 11, 22, 00, 00);
//		registerTime = c.getTime();
//		Voyage voyage3 = new Voyage(mercedesTravego, route3, departureTime, new BigDecimal("70.00"), registerTime);
//		Expense terminalExp3 = new Expense(new BigDecimal("65.00"), terminalExpense);
//		voyage3.getExpenseList().add(terminalExp3);
//		Long id3 = voyageService.create(voyage3);
//		
//		c.set(2016, 4, 18, 9, 00, 00);
//		departureTime = c.getTime();
//		c.set(2016, 4, 11, 22, 25, 00);
//		registerTime = c.getTime();
//		Voyage voyage4 = new Voyage(neoplanCityLiner, route3, departureTime, new BigDecimal("70.00"), registerTime);
//		Long id4 = voyageService.create(voyage4);
//		
//		
//			
//		ids = new Long[]{id1, id2, id3, id4};
//	}
	
	@Test
	public void testFindById() {
				
		TestTransaction.flagForCommit();
		TestTransaction.end();
		TestTransaction.start();
		
		Voyage voyage = voyageService.findById(10L);
		assertNotNull(voyage);
//		assertEquals(voyage1.getVehicle().getSeatCount(), new Integer("37"));
//		assertEquals(voyage1.getRoute().getRouteName(), "Ankara-Kocaeli");
//		assertEquals(voyage1.getRoute().getStops().size(), 4);
		System.out.println(voyage.getVehicle().getPlateCode() + " - " + voyage.getVehicle().getModel().getModelName());
		System.out.println("Departure Time = " + voyage.getDepartureTime().toString());
		System.out.println("Register Time = " + voyage.getRegisterTime().toString());
		
		Set<Stop> stopList = voyage.getRoute().getStops();
		for(Stop s : stopList) {
			System.out.println(s.getCity().getCityName() + " - " + s.getDuration());
		}
		
		Set<Expense> expenseSet = new LinkedHashSet<Expense>(voyage.getExpenseList());
//		List<Expense> expenseList = voyage.getExpenseList();
		assertNotNull(expenseSet);
		for(Expense e : expenseSet) {
			System.out.println(e.getType().getName() + " - " + e.getPrice().toString());
		}		
	}
	
	@Test
	public void testFindAll() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		TestTransaction.start();
		
		List<Voyage> voyageList = voyageService.findAll();
		assertEquals(voyageList.size(), 60);		
//		System.out.println("=================================================");
//		for(Voyage v : voyageList) {
//			Vehicle vehicle = v.getVehicle();
//			System.out.println("ID :" + v.getId());
//			System.out.println("Vehicle : " 
//					+ vehicle.getModel().getBrand().getName() 
//					+ " - " + vehicle.getModel().getModelName() 
//					+ " ["+ vehicle.getPlateCode() + "]");
//			System.out.println("Departure Time : " + v.getDepartureTime().toString());
//			System.out.println("Registered Time : " + v.getRegisterTime().toString());
//			System.out.println("Ticket Price : " + v.getTicketPrice());
//			System.out.println("Stops for " + v.getRoute().getRouteName());
//			Calendar c = Calendar.getInstance();
//			c.setTime(v.getDepartureTime());
//			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//			int previousDuration = 0;
//			for(Stop s : v.getRoute().getStops()) {
//				c.add(Calendar.MINUTE, s.getDuration()-previousDuration);
//				System.out.println("\t- " + s.getCity().getCityName() + "[" + df.format(c.getTime()) + "]");
//				previousDuration = s.getDuration();
//			}
//			System.out.println("Expenses for " + v.getRoute().getRouteName());
//			if(v.getExpenseList().size() != 0) {
//				for(Expense e : v.getExpenseList()) {
//					System.out.println("\t" + e.getType().getName() + " - " + e.getPrice());
//				}
//			}
//			System.out.println("=================================================");
//		}
	}
	
	@Test
	public void testUpdate() {
		renewTransaction();
		
		Voyage voyage10 = voyageService.findById(10L);
		
		voyageService.update(voyage10);
		
		renewTransaction();
		
		Voyage voyageTest = voyageService.findById(10L);
	}
	
	@Test
	public void testFindByDate() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		List<Voyage> voyageListForToday = voyageService.findAllByDate(gc.getTime());
		assertNotNull(voyageListForToday);
//		Set<Voyage> voyageSetForToday = new LinkedHashSet<Voyage>(voyageListForToday);		
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		for(Voyage v : voyageListForToday) {
			System.out.print("Id = " + v.getId() + "\t");
			System.out.print("Route = " + v.getRoute().getRouteName() + "\t");
			System.out.println("Time = " + df.format(v.getDepartureTime()) + "\t");
		}
	}
	
	@Test
	public void testFindByRouteAndDate() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		
		Route ankaraDuzce = routeService.findByRouteName("Ankara-Düzce");
		Route istanbulIzmir = routeService.findByRouteName("İstanbul-İzmir");
		List<Route> routeList = new ArrayList<Route>();
		routeList.add(ankaraDuzce);
		routeList.add(istanbulIzmir);
		List<Voyage> voyageList  = voyageService.findAllByRouteAndDate(routeList, gc.getTime());
		assertNotNull(voyageList);
		Set<Voyage> voyageSet = new LinkedHashSet<Voyage>(voyageList);
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		for(Voyage v : voyageSet) {
			System.out.print("Id = " + v.getId() + "\t");
			System.out.print("Route = " + v.getRoute().getRouteName() + "\t");
			System.out.println("Time = " + df.format(v.getDepartureTime()) + "\t");
		}
	}
	
	@Test
	public void testFindVoyageByDepartureArrivalAndDate() {
		renewTransaction();
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		
		City bolu = cityService.findById(14L);
		City duzce = cityService.findById(81L);
		
		List<Route> routeList = routeService.findAllByDepartureAndArrival(bolu, duzce);
		List<Voyage> voyageList = voyageService.findAllByRouteAndDate(routeList, gc.getTime());
		assertNotEquals(voyageList.size(), 0);
		Set<Voyage> voyageSet = new LinkedHashSet<Voyage>(voyageList);
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		for(Voyage voyage : voyageSet) {
			System.out.print("Id = " + voyage.getId() + "\t");
			System.out.print("Route = " + voyage.getRoute().getRouteName() + "\t");
			System.out.println("Time = " + df.format(voyage.getDepartureTime()) + "\t");
		}
	}

	@Test
	public void testFindAllStartedInFiveHour() {
		renewTransaction();
		Map<Voyage, String> voyageMap = voyageService.findAllStartedInHourWithCompletionPercentage(3);
		Set<Voyage> voyageSet = voyageMap.keySet();
		Voyage lastVoyage = (Voyage) voyageSet.toArray()[voyageSet.size()-1];
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.set(Calendar.HOUR_OF_DAY, gc.get(Calendar.HOUR_OF_DAY)-3);
		assertTrue(gc.getTime().before(lastVoyage.getDepartureTime()));
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		for(Voyage voyage : voyageSet) {
			System.out.print("Id = " + voyage.getId() + "\t");
			System.out.print("Route = " + voyage.getRoute().getRouteName() + "\t");
			System.out.print("Time = " + df.format(voyage.getDepartureTime()) + "\t");
			System.out.println("Remain Time = " + voyageMap.get(voyage));
		}
	}
	
	@Test
	public void testFindAllSortedByDepartureTimeForRecords() {
		renewTransaction();
		
		List<Voyage> voyageList = voyageService.findAllSortedByDepartureTimeForRecords(10);
		//Set<Voyage> voyageSet = new LinkedHashSet<Voyage>(voyageList);
		assertNotNull(voyageList);
		assertEquals(voyageList.size(), 10);
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		for(Voyage voyage : voyageList) {
			System.out.print("Id = " + voyage.getId() + "\t");
			System.out.print("Route = " + voyage.getRoute().getRouteName() + "\t");
			System.out.println("Time = " + df.format(voyage.getDepartureTime()) + "\t");
		}
	}
	
	@Test
	public void testFindAllBeetweenDate() {
		renewTransaction();
		
		GregorianCalendar start = new GregorianCalendar();
		start.setTime(new Date());
		
		GregorianCalendar end = new GregorianCalendar();
		end.set(Calendar.DAY_OF_MONTH, end.get(Calendar.DAY_OF_MONTH)+10);
		
		List<Voyage> voyageList = voyageService.findAllBetweenDates(start.getTime(), end.getTime());
		assertNotNull(voyageList);
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		for(Voyage voyage : voyageList) {
			System.out.print("Id = " + voyage.getId() + "\t");
			System.out.print("Route = " + voyage.getRoute().getRouteName() + "\t");
			System.out.println("Time = " + df.format(voyage.getDepartureTime()) + "\t");
		}
		System.out.println("Start = "+ df.format(start.getTime()) + "\t" + "End = " + df.format(end.getTime()));
		System.out.println("Sefer Count = " + voyageList.size());
		
	}
	
	@Test
	public void testDelete() {
		renewTransaction();
		
		Voyage voyage12 = voyageService.findById(12L);
//		voyage12.getExpenseList().clear();
		voyage12.getRoute().getVoyageList().clear();
		voyage12.getRoute().getStops().clear();
//		voyage12.getTicketList().clear();
		voyageService.delete(voyage12);
		renewTransaction();		
		Voyage voyageTest = voyageService.findById(12L);
		assertNull(voyageTest);
	}
	
	
	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
