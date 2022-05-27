package com.erhan.busticket.db;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Customer;
import com.erhan.busticket.model.Expense;
import com.erhan.busticket.model.ExpenseType;
import com.erhan.busticket.model.Gender;
import com.erhan.busticket.model.Income;
import com.erhan.busticket.model.Route;
import com.erhan.busticket.model.Stop;
import com.erhan.busticket.model.Ticket;
import com.erhan.busticket.model.UserRole;
import com.erhan.busticket.model.Vehicle;
import com.erhan.busticket.model.VehicleBrand;
import com.erhan.busticket.model.VehicleModel;
import com.erhan.busticket.model.Voyage;
import com.erhan.busticket.service.AdminService;
import com.erhan.busticket.service.CityDistanceService;
import com.erhan.busticket.service.CityService;
import com.erhan.busticket.service.CustomerService;
import com.erhan.busticket.service.ExpenseService;
import com.erhan.busticket.service.ExpenseTypeService;
import com.erhan.busticket.service.IncomeService;
import com.erhan.busticket.service.RouteService;
import com.erhan.busticket.service.StopService;
import com.erhan.busticket.service.TicketService;
import com.erhan.busticket.service.UserRoleService;
import com.erhan.busticket.service.VehicleBrandService;
import com.erhan.busticket.service.VehicleModelService;
import com.erhan.busticket.service.VehicleService;
import com.erhan.busticket.service.VoyageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/spring-security.xml" })
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests { 
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	CustomerService customerService;

	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	VehicleBrandService vehicleBrandService;
	
	@Autowired
	VehicleModelService vehicleModelService;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	CityService cityService;

	@Autowired
	CityDistanceService cityDistanceService;
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	StopService stopService;

	@Autowired
	VoyageService voyageService;

	@Autowired
	ExpenseTypeService expenseTypeService;

	@Autowired
	ExpenseService expenseService;

	@Autowired
	TicketService ticketService;
	
	@Autowired
	IncomeService incomeService;

	// protected LinkedList<String> names;
	protected List<String> maleNames;
	protected List<String> femaleNames;
	protected List<String> surnames;
	
	protected Customer[] customers;
	protected Long[] voyageIds;
	protected Long[] ticketIds;

	@BeforeTransaction
	public void populateData() {

		maleNames = populateNames("maleNames.txt");
		femaleNames = populateNames("femaleNames.txt");
		surnames = populateNames("surnames.txt");
		LinkedList<String> passwordList = getListOfDataFromTxtFile("encodedPasswords.txt");
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// Admin Erhan
		UserRole roleAdmin = new UserRole("ROLE_ADMIN");
		Customer adminErhan = new Customer();
		adminErhan.setTcNumber("10987654321");
		adminErhan.setName("Erhan");
		adminErhan.setSurname("TUNÇEL");
		adminErhan.setGender(Gender.ERKEK);
		Calendar c = Calendar.getInstance();
		c.set(1984, 11, 29);
		adminErhan.setDateOfBirth(c.getTime());
		adminErhan.setMobileNumber("5965534785");
		adminErhan.seteMail("erhan@abc.com");
		adminErhan.setPassword(passwordEncoder.encode("admiN123"));
		c.set(2016, 2, 21, 11, 45, 55);
		adminErhan.setDateOfRegister(c.getTime());
		c.setTime(new Date());
		adminErhan.setTimeOfLastOnline(c.getTime());
		adminErhan.setEnabled(true);
		roleAdmin.setUser(adminErhan);
		userRoleService.create(roleAdmin);

		// Creating User account (Serhan)
		UserRole userRoleSerhan = new UserRole("ROLE_USER");
		Customer userSerhan = new Customer();
		userSerhan.setTcNumber("12345678910");
		userSerhan.setName("Serhan");
		userSerhan.setSurname("TUNÇEL");
		userSerhan.setGender(Gender.ERKEK);
		userSerhan.setDateOfBirth(generateRandomDateMaximum18Year());
		userSerhan.setMobileNumber(generateRandomGsmNumber());
		userSerhan.seteMail(generateNameForEmail("serhan")+"@abc.com");
		userSerhan.setPassword(passwordEncoder.encode("useR1234"));
		userSerhan.setDateOfRegister(generateCustomerRegisteredTime(180, 150));
		userSerhan.setTimeOfLastOnline(generateCustomerLastOnlineTime(16, 0));
		userSerhan.setEnabled(true);
		userRoleSerhan.setUser(userSerhan);
		userRoleService.create(userRoleSerhan);
		
		// Customers 
		int customerCount = 0;
		while (customerCount < 24) {
			UserRole roleUser = new UserRole("ROLE_USER");
			Customer customer = new Customer();
			customer.setGender(Gender.getRandom());
			String[] name = generateName(customer.getGender());
			customer.setTcNumber(generateRandomTcNumber());
			customer.setName(name[0]);
			customer.setSurname(name[1]);
			customer.setDateOfBirth(generateRandomDateMaximum18Year());
			customer.setMobileNumber(generateRandomGsmNumber());
			customer.seteMail(generateNameForEmail(name[0]) + "@abc.com");
//			customer.setPassword(passwordEncoder.encode(generateRandomPassword()));
			customer.setPassword(passwordList.removeFirst());
			customer.setDateOfRegister(generateCustomerRegisteredTime(150, 20));
			customer.setTimeOfLastOnline(generateCustomerLastOnlineTime(16, 0));
			customer.setEnabled(true);
//			customerService.create(customer);
			roleUser.setUser(customer);
			userRoleService.create(roleUser);
			customerCount++;
		}
				
		// Routes And Stops
		LinkedList<String> stopLists = getListOfDataFromTxtFile("routeAndStops.txt");
		Stop stop;
		int stopListSize = stopLists.size();
		for(int i=0; i<stopListSize; i++) {
			String[] stopData = stopLists.removeFirst().split(",");
			Route routeFromDb = routeService.findByRouteName(stopData[0]);
			Route route = null;
			if(routeFromDb == null) {
				route = new Route(stopData[0]);
			} else {
				route = routeFromDb;
			}
			
			stop = new Stop(
					route, 
					cityService.findById(Long.valueOf(stopData[1])), 
					Integer.valueOf(stopData[2]),
					Integer.valueOf(100)
					);
			stopService.create(stop);
		}
		
		// Vehicle Brand and Models
		LinkedList<String> vehicleModelList = getListOfDataFromTxtFile("vehicleList.txt");
		VehicleModel vehicleModel;
		int vehicleModelListSize = vehicleModelList.size();
		for(int i=0; i<vehicleModelListSize; i++) {
			String[] vehicleModelData = vehicleModelList.removeFirst().split(",");
			VehicleBrand brand = vehicleBrandService.findByName(vehicleModelData[0]);
			if(brand == null) {
				brand = new VehicleBrand(vehicleModelData[0]);
			}
			vehicleModel = new VehicleModel(vehicleModelData[1], brand);
			brand.getVehicleModelList().add(vehicleModel);
			vehicleBrandService.create(brand);
		}
		
		// Vehicles
		for(int i=0; i<12; i++) {
			int platePostfix = 40 + i;
			String plate = "14BL1" + platePostfix;
//			Integer seatCount = 37;
			VehicleModel model = vehicleModelService.findById(Long.valueOf(String.valueOf(randBetween(1, 7))));
			int year = randBetween(2013, 2016);
			Integer milage = 0;
			switch (year) {
			case 2013:
				milage = Integer.valueOf(randBetween(400000, 500000));
				break;
			case 2014:
				milage = Integer.valueOf(randBetween(250000, 400000));
				break;
			case 2015:
				milage = Integer.valueOf(randBetween(100000, 250000));
				break;
			case 2016:
				milage = Integer.valueOf(randBetween(0, 100000));
				break;
			}
			Vehicle vehicle = new Vehicle(plate, model, String.valueOf(year), milage);
			vehicleService.create(vehicle);
		}
		
		// Voyages
		// Add voyages between 2 days before today and 2 days after today
//		LinkedList<Long> vehicleIdList = vehicleService.findAllIds();
		LinkedList<Voyage> voyages = new LinkedList<Voyage>();
		for(int i=0; i<5; i++) {
			LinkedList<String> voyageList = getListOfDataFromTxtFile("voyageDataList.txt");
			int dayInterval = i-2;
			GregorianCalendar departureTime = new GregorianCalendar();
			departureTime.setTime(new Date());
			departureTime.set(Calendar.DAY_OF_YEAR, departureTime.get(Calendar.DAY_OF_YEAR)+dayInterval);
			for(int j=0; j<4; j++) {
				String[] voyageListData = voyageList.removeFirst().split(",");
				Route route = routeService.findByRouteName(voyageListData[0]);
				List<Vehicle> vehicleListFromDb = vehicleService.findAll();
				LinkedList<Vehicle> vehicleList = new LinkedList<Vehicle>();
				vehicleList.addAll(vehicleListFromDb);
				Collections.shuffle(vehicleList);
				int hour = Integer.parseInt(voyageListData[1]);
				int minute = Integer.parseInt(voyageListData[2]);
				departureTime.set(Calendar.HOUR_OF_DAY, hour-5);
				for(int k=0; k<3; k++) {
					/*
					String[] time = routeData[2].split(":");
					int hour = Integer.parseInt(time[0]);
					int minute = Integer.parseInt(time[1]);
					*/
					departureTime.set(Calendar.HOUR_OF_DAY, departureTime.get(Calendar.HOUR_OF_DAY)+5);
					departureTime.set(Calendar.MINUTE, minute);
					departureTime.set(Calendar.SECOND, 0);
					GregorianCalendar registerTime = new GregorianCalendar();
					registerTime.setTime(departureTime.getTime());
					registerTime.set(Calendar.DAY_OF_YEAR, departureTime.get(Calendar.DAY_OF_YEAR)-14);
					registerTime.set(Calendar.HOUR_OF_DAY, randBetween(0, 23));
					registerTime.set(Calendar.MINUTE, randBetween(0, 59));
					registerTime.set(Calendar.SECOND, randBetween(0, 59));
					Vehicle vehicle = vehicleList.removeFirst();
					Voyage voyage = new Voyage(
							vehicle, 
							route, 
							departureTime.getTime(),
							registerTime.getTime()
							);
					voyageService.create(voyage);
					voyages.add(voyage);
				}
			}
		}
		
		// Tickets
		int voyageNumber = 0;
		for(Voyage voyage : voyages) {
			
			/*
			 * En uzak duraklar arası için biletleri ekle
			 * - Her rota için oluşturulacak bilet sayısını hesapla
			 * - Kalkış ve varış notları için maksimum koltuk sayısını geçmeyecek şekilde biletleri oluştur.
			 * Güne göre rastgele eklenecek bilet sayısını belirle
			 * Rasgele seçilecek kalkış ve varış durakları için maksimum koltuk sayısını geçmeyecek şekilde biletleri oluştur.
			 * - Seçilen kalkış ve varış duraklarına göre boş koltukları belirle.
			 * - Boş koltuk listesini karıştırarak bir numara seçerek bileti oluştur.
			 */
			
			Stop[] stops = (Stop[]) voyage.getRoute().getStops().toArray(new Stop[voyage.getRoute().getStops().size()]);
			int stopCount = stops.length;
			int maxPassengerCount = 20;
			int combinationCount = stopCount*((stopCount-1+1)/2);
			int passengerCountPerCombination = (maxPassengerCount - (maxPassengerCount % combinationCount)) / combinationCount;
			int seatCount = 37;
			BigDecimal totalTicketPrice = new BigDecimal(0);
			
			GregorianCalendar today = new GregorianCalendar();
			today.setTime(new Date());
			int dayOfYearForToday = today.get(Calendar.DAY_OF_YEAR);
			
			GregorianCalendar departureTime = new GregorianCalendar();
			departureTime.setTime(voyage.getDepartureTime());
			
			
			Integer minExtraTicketCount = 0;
			Integer maxExtraTicketCount = 0;
			
			
			long differenceOfDepartureAndToday = calculateDifferenceBetweenDatesInDay(departureTime, today);
			if(differenceOfDepartureAndToday == -2l) {
				minExtraTicketCount = 0;
				maxExtraTicketCount = 7;
			} else if (differenceOfDepartureAndToday == -1l) {
				minExtraTicketCount = 7;
				maxExtraTicketCount = 14;
			} else if(differenceOfDepartureAndToday == 0) {
				minExtraTicketCount = 14;
				maxExtraTicketCount = 21;
			} else if(differenceOfDepartureAndToday == 1l) {
				minExtraTicketCount = 21;
				maxExtraTicketCount = 28;
			} else if(differenceOfDepartureAndToday == 2l) {
				minExtraTicketCount = 28;
				maxExtraTicketCount = 35;
			}
			
			
			List<Ticket> ticketList = new ArrayList<Ticket>();
			boolean ticketForTestCustomer = true;
			
			Date lastTicketRegisteredTime = null;
			// Adding tickets for first stop as departure and last stop as arrival
			for(int i=0; i<stops.length-1; i++) {
				for(int j=i+1; j<stops.length; j++) {
					if(!isMaxCountReachForDepartureAndArrival(i, j, ticketList, seatCount, stops)) {
						for(int k=0; k<passengerCountPerCombination; k++) {
							Ticket ticket = createTicket(voyageNumber, voyage, stops, seatCount, totalTicketPrice, dayOfYearForToday, departureTime,
									ticketList, i, j);
							totalTicketPrice = totalTicketPrice.add(ticket.getPrice());
						}
					}
				}
			}
			
			// Adding extra tickets for random departure and arrival stop
			int extraPassengerCountTemp = 0;
			int extraPassengerCount = randBetween(minExtraTicketCount, maxExtraTicketCount);
			while(extraPassengerCountTemp <= extraPassengerCount) {
				int departureIndex = randBetween(0, stops.length-2);
				int arrivalIndex = randBetween(departureIndex+1, stops.length-1);
				if(!isMaxCountReachForDepartureAndArrival(departureIndex, arrivalIndex, ticketList, seatCount, stops)) {
					Ticket ticket = new Ticket();
					if(voyageNumber%10 == 0 && ticketForTestCustomer) {
						userSerhan.setTimeOfLastOnline(generateCustomerLastOnlineTimeForTicket(departureTime.getTime()));
						customerService.update(userSerhan);
						ticket = createTicletForTestCustomer(userSerhan, voyage, stops, seatCount, ticketList, departureIndex, arrivalIndex);
						ticketForTestCustomer = false;
					} else {
						ticket = createTicket(voyageNumber, voyage, stops, seatCount, totalTicketPrice, dayOfYearForToday, departureTime, 
								ticketList, departureIndex, arrivalIndex);
					}
					totalTicketPrice = totalTicketPrice.add(ticket.getPrice());
					lastTicketRegisteredTime = ticket.getRegisterTime();						
					extraPassengerCountTemp++;
				}
			}
			
			updateIncomeByTotalTicketPrice(voyage, lastTicketRegisteredTime, totalTicketPrice);
			
//			for(int i=0; i<stops.length; i++) {
//				for(int j=i+1; j<stops.length; j++) {
//					passengerCount = ticketCountMatrix[i][j];
//					while(passengerCount > 0) {
//						
//						// Create empty seat number list
//						LinkedList<Byte> emptySeatNumberList = new LinkedList<Byte>();
//						for(int number=1; number<=seatCount; number++) {
//							emptySeatNumberList.add(new Byte(String.valueOf(number)));
//						}
//						
//						// Remove seat number that is selected before
//						if(!ticketList.isEmpty()) {
//							for(Ticket ticket : ticketList) {
//								for(int stopIndex = i+1; stopIndex<stops.length; stopIndex++) {
//									if(ticket.getArrival().equals(stops[stopIndex].getCity())) {
//										emptySeatNumberList.remove(ticket.getSeatNumber());
//									}
//								}
//							}
//						}
//						
//						City departure = stops[i].getCity();
//						City arrival = stops[j].getCity();
//						Collections.shuffle(emptySeatNumberList);
//						if(emptySeatNumberList.isEmpty()) {
//							continue;
//						}
//						Ticket ticket = new Ticket();
//						ticket.setDeparture(departure);
//						ticket.setArrival(arrival);
//						Byte seatNumber = emptySeatNumberList.removeFirst();
//						ticket.setSeatNumber(seatNumber);
//						
//						
//						// Ticket details
//						boolean isReservation = new  Random().nextBoolean();
////						String pricePerDistance = "0.15";
////						CityDistance distance = cityDistanceService.findByDepartureAndArrival(ticket.getDeparture(), ticket.getArrival());
//						BigDecimal ticketPrice = ticketService.calculateTicketPriceByDepartureAndArrival(ticket.getDeparture(), ticket.getArrival());
//						if(!isReservation) {
//							ticket.setPrice(ticketPrice);							
//						} else {
//							ticket.setPrice(new BigDecimal(0));
//						}
//						Integer[] seatIndex = findIndexOfSeat(seatNumbers, seatNumber.intValue());
//						Gender gender = generateGenderForSeat(seats, seatIndex);
//						int dayOfYearForDepartureTime = departureTime.get(Calendar.DAY_OF_YEAR);
//						Date ticketRegisterTime = new Date();
//						int randomNumber = randBetween(0, 150);
//						if (randomNumber <= 100) { 
//							// Customer or By Customer
//							Customer customer = null;
//							if(voyageNumber%10 == 0 && ticketForTestCustomer) {
//								customer = customerService.findByTcNumber("12345678910");
//								customer.setTimeOfLastOnline(generateCustomerLastOnlineTimeForTicket(departureTime.getTime()));
//								customerService.update(customer);
//								ticketForTestCustomer = false;
//							} else {
//								UserRole userRoleForCustomer = new UserRole("ROLE_USER");
//								customer = new Customer();
//								customer.setGender(gender);
//								String[] name = generateName(customer.getGender());
//								customer.setTcNumber(generateRandomTcNumber());
//								customer.setName(name[0]);
//								customer.setSurname(name[1]); 
//								customer.setDateOfBirth(generateRandomDateMaximum18Year());
//								customer.setMobileNumber(generateRandomGsmNumber());
//								customer.seteMail(generateNameForEmail(name[0]) + "@abc.com");
////							customer.setPassword(passwordEncoder.encode(generateRandomPassword()));
//								Collections.shuffle(passwordList);
//								customer.setPassword(passwordList.getFirst());
//								customer.setDateOfRegister(generateCustomerRegisteredTime(150, dayOfYearForToday-dayOfYearForDepartureTime));
//								customer.setTimeOfLastOnline(generateCustomerLastOnlineTimeForTicket(departureTime.getTime()));
//								customer.setEnabled(true);
//								userRoleForCustomer.setUser(customer);
//								userRoleService.create(userRoleForCustomer);	
//							}
//							if(randomNumber <50) {
//								// Customer
//								ticket.setIsReservation(isReservation);
//								ticket.setVoyage(voyage);
//								GregorianCalendar gc = new GregorianCalendar();
//								gc.setTime(customer.getTimeOfLastOnline());
//								gc.add(Calendar.MINUTE, randBetween(5, 20));
//								ticketRegisterTime = gc.getTime();
//								ticket.setRegisterTime(ticketRegisterTime);
//								ticket.setCustomer(customer);
//								ticket.setPassangerTcNumber(customer.getTcNumber());
//								ticket.setPassangerName(customer.getName());
//								ticket.setPassangerSurname(customer.getSurname());
//								ticket.setPassangerGender(customer.getGender());
//								Long ticketId = ticketService.create(ticket);
//							} else if(randomNumber >= 50 & randomNumber <= 100) {
//								// By Customer
//								ticket.setIsReservation(isReservation);
//								ticket.setVoyage(voyage);
//								GregorianCalendar gc = new GregorianCalendar();
//								gc.setTime(customer.getTimeOfLastOnline());
//								gc.add(Calendar.MINUTE, randBetween(5, 20));
//								ticketRegisterTime = gc.getTime();
//								ticket.setRegisterTime(ticketRegisterTime);
//								ticket.setCustomer(customer);
//								ticket.setPassangerTcNumber(generateRandomTcNumber());
//								String[] passangerName = generateName(gender);
//								ticket.setPassangerName(passangerName[0]);
//								ticket.setPassangerSurname(passangerName[1]);
//								ticket.setPassangerGender(gender);
//								Long ticketId = ticketService.create(ticket);
//							}
//						} else {
//							// Not customer
//							ticket.setIsReservation(isReservation);
//							ticket.setVoyage(voyage);
//							ticketRegisterTime = generateTicketRegisteredTimeForNotCustomer(departureTime.getTime());
//							ticket.setRegisterTime(ticketRegisterTime);
//							ticket.setPassangerTcNumber(generateRandomTcNumber());
//							String[] passangerName = generateName(gender);
//							ticket.setPassangerName(passangerName[0]);
//							ticket.setPassangerSurname(passangerName[1]);
//							ticket.setPassangerGender(gender);
//							Long ticketId = ticketService.create(ticket);
//						}
//						ticketList.add(ticket);
//						totalTicketPrice = totalTicketPrice.add(ticket.getPrice());
//						Income incomeFromDb = incomeService.findByVoyage(voyage);
//						if(incomeFromDb == null) {
//							Income income = new Income(voyage, ticketRegisterTime, totalTicketPrice);							
//							incomeService.create(income);
//						} else {
//							incomeFromDb.setPrice(totalTicketPrice);
//							incomeFromDb.setRegisteredTime(ticketRegisterTime);
//							incomeService.update(incomeFromDb);
//						}
//						passengerCount--;
//					}
//				}
//			}
			
//			GregorianCalendar gc = new GregorianCalendar();
//			gc.setTime(voyage.getDepartureTime());
//			Set<Stop> stopList = voyage.getRoute().getStops();
//			Stop lastStop = (Stop) stopList.toArray()[stopList.size()-1];
//			gc.add(Calendar.MINUTE, lastStop.getDuration());
//			gc.add(Calendar.HOUR_OF_DAY, 6);
//			gc.add(Calendar.MINUTE, 15);
//			
//			GregorianCalendar now = new GregorianCalendar();
//			now.setTime(new Date());
//			if(gc.before(now)) {				
//				Integer ticketCount = ticketService.countTicketByVoyage(voyage);
//				// TODO: create method for calculate ticket price based on departure and arrival
//				BigDecimal totalPrice = new BigDecimal("1").multiply(new BigDecimal(ticketCount.intValue()));
//				Date incomeRegisterTime = gc.getTime();
//				Income income = new Income(voyage, incomeRegisterTime, totalPrice);
//				incomeService.create(income);
//			}
			voyageNumber++;
		}
		
			
//			Set<Stop> stopSet = voyage.getRoute().getStops();
//			for(Stop voyageStop : stopSet) {
//				
//			}
//			
//			Set<Byte> seatNumberSet = new LinkedHashSet<Byte>();
//			for(int i=1; i<=passengerCount; i++) {
//				Byte seatNumber = new Byte(String.valueOf(randBetween(1, 37)));
//				seatNumberSet.add(seatNumber);
//			}
			
//			for(Byte seatNumber : seatNumberSet) {
//				boolean isReservation = new  Random().nextBoolean();
//				Integer[] seatIndex = findIndexOfSeat(seatNumbers, seatNumber);
//				Gender gender = generateGenderForSeat(seats, seatIndex);
//				int dayOfYearForDepartureTime = departureTime.get(Calendar.DAY_OF_YEAR);
//				
//				Ticket ticket = new Ticket();
//				
//				int randomNumber = randBetween(0, 150);
//				if (randomNumber <= 100) {
//					Customer customer = new Customer();
//					customer.setGender(gender);
//					String[] name = generateName(customer.getGender());
//					customer.setTcNumber(generateRandomTcNumber());
//					customer.setName(name[0]);
//					customer.setSurname(name[1]); 
//					customer.setDateOfBirth(generateRandomDateMaximum18Year());
//					customer.setMobileNumber(generateRandomGsmNumber());
//					customer.seteMail(generateNameForEmail(name[0]) + "@abc.com");
////					customer.setPassword(passwordEncoder.encode(generateRandomPassword()));
//					customer.setPassword(passwordList.removeFirst());
//					customer.setDateOfRegister(generateCustomerRegisteredTime(150, dayOfYearForToday-dayOfYearForDepartureTime));
//					customer.setTimeOfLastOnline(generateCustomerLastOnlineTimeForTicket(departureTime.getTime()));
//					customer.setEnabled(true);
//					if(randomNumber <50) {
//						// Customer
//						ticket.setIsReservation(isReservation);
//						ticket.setVoyage(voyage);
//						ticket.setSeatNumber(seatNumber);
//						GregorianCalendar gc = new GregorianCalendar();
//						gc.setTime(customer.getTimeOfLastOnline());
//						gc.add(Calendar.MINUTE, randBetween(5, 20));
//						ticket.setRegisterTime(gc.getTime());
//						ticket.setCustomer(customer);
//						ticket.setPassangerTcNumber(customer.getTcNumber());
//						ticket.setPassangerName(customer.getName());
//						ticket.setPassangerSurname(customer.getSurname());
//						ticket.setPassangerGender(customer.getGender());
//						Long ticketId = ticketService.create(ticket);
//					} else if(randomNumber > 50 & randomNumber <= 100) {						
//						// By Customer
//						ticket.setIsReservation(isReservation);
//						ticket.setVoyage(voyage);
//						ticket.setSeatNumber(seatNumber);
//						GregorianCalendar gc = new GregorianCalendar();
//						gc.setTime(customer.getTimeOfLastOnline());
//						gc.add(Calendar.MINUTE, randBetween(5, 20));
//						ticket.setRegisterTime(gc.getTime());
//						ticket.setCustomer(customer);
//						ticket.setPassangerTcNumber(generateRandomTcNumber());
//						String[] passangerName = generateName(gender);
//						ticket.setPassangerName(passangerName[0]);
//						ticket.setPassangerSurname(passangerName[1]);
//						ticket.setPassangerGender(gender);
//						Long ticketId = ticketService.create(ticket);
//					}
//				} else {
//					// Not Customer
//					ticket.setIsReservation(isReservation);
//					ticket.setVoyage(voyage);
//					ticket.setSeatNumber(seatNumber);
//					ticket.setRegisterTime(generateTicketRegisteredTimeForNotCustomer(departureTime.getTime()));
//					ticket.setPassangerTcNumber(generateRandomTcNumber());
//					String[] passangerName = generateName(gender);
//					ticket.setPassangerName(passangerName[0]);
//					ticket.setPassangerSurname(passangerName[1]);
//					ticket.setPassangerGender(gender);
//					Long ticketId = ticketService.create(ticket);
//				}	
//			}
//			
//			GregorianCalendar gc = new GregorianCalendar();
//			gc.setTime(voyage.getDepartureTime());
//			Set<Stop> stopList = voyage.getRoute().getStops();
//			Stop lastStop = (Stop) stopList.toArray()[stopList.size()-1];
//			gc.add(Calendar.MINUTE, lastStop.getDuration());
//			gc.add(Calendar.HOUR_OF_DAY, 6);
//			gc.add(Calendar.MINUTE, 15);
//			
//			GregorianCalendar now = new GregorianCalendar();
//			now.setTime(new Date());
//			if(gc.before(now)) {				
//				Integer ticketCount = ticketService.countTicketByVoyage(voyage);
//				BigDecimal totalPrice = voyage.getTicketPrice().multiply(new BigDecimal(ticketCount.intValue()));
//				Date incomeRegisterTime = gc.getTime();
//				Income income = new Income(voyage, incomeRegisterTime, totalPrice);
//				incomeService.create(income);
//			}
		
		
		// Expenses
		// Expense Types
		ExpenseType fuelExpenseType = expenseTypeService.findById(1L);
		ExpenseType maintenanceExpenseType = expenseTypeService.findById(2L);
		ExpenseType snackExpenseType = expenseTypeService.findById(3L);
		ExpenseType terminalExpenseType = expenseTypeService.findById(4L);
		ExpenseType penaltyExpenseType = expenseTypeService.findById(5L);
		
		int fuelPrice = 0;
		int terminalPrice = 0;
		int snackPrice = 0;
		int maintenancePrice = 0;
		int penaltyPrice = 0;
		List<Voyage> voyageList = voyageService.findAll();
		for(Voyage voyage : voyageList) {
			switch (voyage.getRoute().getRouteName()) {
				case "Ankara-Kocaeli" :
					fuelPrice = randBetween(403-40, 403+40);
					terminalPrice = randBetween(45-5, 45+5);
					snackPrice = randBetween(183-20, 183+20);
					maintenancePrice = randBetween(0, 200);
					penaltyPrice = randBetween(0, 250);
				case "Ankara-Düzce" :
					fuelPrice = randBetween(275-40, 275+40);
					terminalPrice = randBetween(28-5, 28+5);
					snackPrice = randBetween(97-20, 97+20);
					maintenancePrice = randBetween(0, 100);
					penaltyPrice = randBetween(0, 200);
				case "İstanbul-İzmir" :
					fuelPrice = randBetween(555-40, 555+40);
					terminalPrice = randBetween(52-5, 52+5);
					snackPrice = randBetween(183-20, 183+20);
					maintenancePrice = randBetween(0, 400);
					penaltyPrice = randBetween(0, 350);
				case "Ankara-Antalya" :
					fuelPrice = randBetween(603-40, 603+40);
					terminalPrice = randBetween(44-5, 44+5);
					snackPrice = randBetween(183-20, 183+20);
					maintenancePrice = randBetween(0, 600);
					penaltyPrice = randBetween(0, 400);
			}
			
			GregorianCalendar expenseTime = new GregorianCalendar();
			expenseTime.setTime(voyage.getDepartureTime());
			expenseTime.set(Calendar.HOUR_OF_DAY, 18);
			Expense fuelExpense = new Expense(new BigDecimal(fuelPrice), fuelExpenseType, expenseTime.getTime(), voyage); 
			expenseService.create(fuelExpense);
			Expense terminalExpense = new Expense(new BigDecimal(terminalPrice), terminalExpenseType, expenseTime.getTime(), voyage);
			expenseService.create(terminalExpense);
			Expense snackExpense = new Expense(new BigDecimal(snackPrice), snackExpenseType, expenseTime.getTime(), voyage);
			expenseService.create(snackExpense);
//			voyage.getExpenseList().add(fuelExpense);
//			voyage.getExpenseList().add(terminalExpense);
//			voyage.getExpenseList().add(snackExpense);
			
			int rnd = randBetween(0, 15);
			if(rnd > 5 & rnd <= 10) {
				Expense penaltyExpense = new Expense(new BigDecimal(penaltyPrice), penaltyExpenseType, expenseTime.getTime(), voyage);
				expenseService.create(penaltyExpense);
//				voyage.getExpenseList().add(penaltyExpense);
			} else if (rnd > 10) {
				Expense maintenanceExpense = new Expense(new BigDecimal(maintenancePrice), maintenanceExpenseType, expenseTime.getTime(), voyage);
				expenseService.create(maintenanceExpense);
//				voyage.getExpenseList().add(maintenanceExpense);
			}
//			voyageService.update(voyage);
		}
		
		
		
		/*
		 * 
		 */
//		fuelPrice = randBetween(403-40, 403+40);
//		terminalPrice = randBetween(45-5, 45+5);
//		snackPrice = randBetween(183-20, 183+20);
//		maintenancePrice = randBetween(0, 200);
//		penaltyPrice = randBetween(0, 250);
//		Voyage voyage = voyageService.findById(10L);
//		GregorianCalendar expenseTime = new GregorianCalendar();
//		expenseTime.setTime(voyage.getDepartureTime());
//		expenseTime.set(Calendar.HOUR_OF_DAY, 18);
//		Expense fuelExpense = new Expense(new BigDecimal(fuelPrice), fuelExpenseType, expenseTime.getTime()); 
//		Expense terminalExpense = new Expense(new BigDecimal(terminalPrice), penaltyExpenseType, expenseTime.getTime());
//		Expense snackExpense = new Expense(new BigDecimal(snackPrice), snackExpenseType, expenseTime.getTime());
//		voyage.getExpenseList().add(fuelExpense);
//		voyage.getExpenseList().add(terminalExpense);
//		voyage.getExpenseList().add(snackExpense);
//		
//		int rnd = randBetween(0, 15);
//		if(rnd > 5 & rnd <= 10) {
//			Expense penaltyExpense = new Expense(new BigDecimal(penaltyPrice), penaltyExpenseType, expenseTime.getTime());
//			voyage.getExpenseList().add(penaltyExpense);
//		} else if (rnd > 10) {
//			Expense maintenanceExpense = new Expense(new BigDecimal(maintenancePrice), maintenanceExpenseType, expenseTime.getTime());
//			voyage.getExpenseList().add(maintenanceExpense);
//		}
//		voyageService.update(voyage);
	}
	
		

	private Gender generateGenderForSeat(String[][] seats, Integer[] seatIndex) {
		Gender gender = null;
		switch(seatIndex[1]) {
		case 0 :
			gender = Gender.getRandom();
			break;
		case 1 :
			if(seats[seatIndex[0]][seatIndex[1]+1] == null) {
				gender = Gender.getRandom();
			} else {
				switch(seats[seatIndex[0]][seatIndex[1]+1]) {
				case "F" :
					gender = Gender.KADIN;
				case "M" :
					gender = Gender.ERKEK;
				}
			}
			break;
		case 2 :
			if(seats[seatIndex[0]][seatIndex[1]-1] == null) {
				gender = Gender.getRandom();
			} else {
				switch(seats[seatIndex[0]][seatIndex[1]-1]) {
				case "F" :
					gender = Gender.KADIN;
				case "M" :
					gender = Gender.ERKEK;
				}
			}
			break;
		}
		return gender;
	}

	@SuppressWarnings("unused")
	private Ticket createTicletForTestCustomer(Customer testCustomer, Voyage voyage, Stop[] stops, int seatCount, List<Ticket> ticketList, int departureIndex,
			int arrivalIndex) {
		LinkedList<Byte> emptySeatList = getEmptySeatNumbersForDepartureAndArrival(departureIndex, arrivalIndex, ticketList, seatCount, stops);
		Collections.shuffle(emptySeatList);
		Byte seatNumber = emptySeatList.removeFirst();
		boolean isReservation = new Random().nextBoolean();
		Ticket ticket = new Ticket();
		ticket.setDeparture(stops[departureIndex].getCity());
		ticket.setArrival(stops[arrivalIndex].getCity());
		ticket.setIsReservation(isReservation);
		ticket.setSeatNumber(seatNumber);							
		BigDecimal ticketPrice = getTicketPrice(ticket.getDeparture(), ticket.getArrival(), isReservation);
		ticket.setPrice(ticketPrice);
		ticket.setVoyage(voyage);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(testCustomer.getTimeOfLastOnline());
		gc.add(Calendar.MINUTE, randBetween(5, 20));
		ticket.setRegisterTime(gc.getTime());
		ticket.setCustomer(testCustomer);
		ticket.setPassangerTcNumber(testCustomer.getTcNumber());
		ticket.setPassangerName(testCustomer.getName());
		ticket.setPassangerSurname(testCustomer.getSurname());
		ticket.setPassangerGender(testCustomer.getGender());
		Long ticketId = ticketService.create(ticket);
		return ticket;
	}
	
	private Ticket createTicket(int voyageNumber, Voyage voyage, Stop[] stops, int seatCount, BigDecimal totalTicketPrice,
			int dayOfYearForToday, GregorianCalendar departureTime, List<Ticket> ticketList, int departureIndex,
			int arrivalIndex) {
		LinkedList<Byte> emptySeatList = getEmptySeatNumbersForDepartureAndArrival(departureIndex, arrivalIndex, ticketList, seatCount, stops);
		Collections.shuffle(emptySeatList);
		Byte seatNumber = emptySeatList.removeFirst();
		City departure = stops[departureIndex].getCity();
		City arrival = stops[arrivalIndex].getCity();
//		System.out.println("Departure = " + departure.getCityName() + " Arrival = " + arrival.getCityName());
		boolean isReservation = new Random().nextBoolean();
		Ticket ticket = new Ticket();
		ticket.setDeparture(departure);
		ticket.setArrival(arrival);
		ticket.setIsReservation(isReservation);
		ticket.setSeatNumber(seatNumber);							
		BigDecimal ticketPrice = getTicketPrice(ticket.getDeparture(), ticket.getArrival(), isReservation);
		ticket.setPrice(ticketPrice);
		ticket.setVoyage(voyage);
		populateTicketWithCustomerAndPassengerInfo(ticket, voyageNumber, departureTime, dayOfYearForToday);
		ticketList.add(ticket);
		return ticket;
	}
	
	private List<String> populateNames(String fileName) {
		List<String> name = new ArrayList<String>();

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				name.add(line);
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	private String[] generateName(Gender gender) {
		String name = "";
		if(gender == Gender.ERKEK) {
			name = maleNames.get(randBetween(0, maleNames.size()-1));
		} else if(gender == Gender.KADIN) {
			name = femaleNames.get(randBetween(0, femaleNames.size()-1)); 
		}
		String surname = surnames.get(randBetween(0, surnames.size()-1));
		String[] nameAndSurname = {name, surname};
		return nameAndSurname;
	}
	
	private LinkedList<String> getListOfDataFromTxtFile(String fileName) {
		
		LinkedList<String> listOfData = new LinkedList<String>();
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				listOfData.add(line);
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listOfData;
	}

	private String generateRandomTcNumber() {
		long number = 11111111111L + (long) (Math.random() * (59999999999L - 11111111111L));
		String tcNumber = Long.toString(number);
		return tcNumber;
	}

	private Date generateRandomDateMaximum18Year() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());

		int year = randBetween(1950, (gc.get(Calendar.YEAR) - 18));
		gc.set(Calendar.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

		return gc.getTime();
	}

	private int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	private String generateRandomGsmNumber() {
		String[] prefix = new String[] { "533", "532", "535", "536", "543", "542", "544", "505", "506" };

		int number = 1111111 + (int) (Math.random() * (9999999 - 1111111));

		return prefix[randBetween(0, 8)] + String.valueOf(number);
	}

	private String generateRandomPassword() {
		String saltChars = "abcdefghijklmanoprstuvyz";
		StringBuilder sb = new StringBuilder();

		sb.append(randBetween(0, 9));
		while (sb.length() < 8) {
			int index = (int) (Math.random() * randBetween(0, 10));
			sb.append(saltChars.charAt(index));
		}
		String pass = sb.toString();
		return pass;
	}

	private Date generateCustomerRegisteredTime(int start, int end) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());

		int dayOfYear = randBetween(gc.get(Calendar.DAY_OF_YEAR) - start,
				gc.get(Calendar.DAY_OF_YEAR) - end);
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

		changeRandomHourAndMinute(gc);

		return gc.getTime();
	}

	private Date generateCustomerLastOnlineTime(int start, int end) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());

		int dayOfYear = randBetween(gc.get(Calendar.DAY_OF_YEAR) - start, gc.get(Calendar.DAY_OF_YEAR) - end);
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

		changeRandomHourAndMinute(gc);

		return gc.getTime();
	}
	
	private Date generateCustomerLastOnlineTimeForTicket(Date departureTime) {

		GregorianCalendar departureCalendar = new GregorianCalendar();
		departureCalendar.setTime(departureTime);
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(departureTime);

		gc.set(Calendar.DAY_OF_YEAR, randBetween(gc.get(Calendar.DAY_OF_YEAR)-5, gc.get(Calendar.DAY_OF_YEAR)));
		if(gc.get(Calendar.DAY_OF_YEAR) == departureCalendar.get(Calendar.DAY_OF_YEAR)) {
			GregorianCalendar temp = new GregorianCalendar();
			temp.setTime(departureTime);
			temp.add(Calendar.MINUTE, -30);
			gc.set(Calendar.HOUR_OF_DAY, randBetween(gc.getActualMinimum(Calendar.HOUR_OF_DAY), temp.get(Calendar.HOUR_OF_DAY)));
			gc.set(Calendar.MINUTE, randBetween(gc.getActualMinimum(Calendar.MINUTE), gc.getActualMaximum(Calendar.MINUTE)));
		} else {
			changeRandomHourAndMinute(gc);
		}
		
		return gc.getTime();
	}

	private Date generateTicketRegisteredTime(Date CustomerLastOnlineTime) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(CustomerLastOnlineTime);
		int lastOnlineMin = gc.get(Calendar.MINUTE);

		if (lastOnlineMin > 45) {
			gc.set(Calendar.HOUR_OF_DAY, gc.get(Calendar.HOUR_OF_DAY) + 1);
			gc.set(Calendar.MINUTE, randBetween(0, 59));
		} else {
			gc.set(Calendar.MINUTE, randBetween(lastOnlineMin + 3, 59));
		}

		int second = randBetween(0, 59);
		gc.set(Calendar.SECOND, second);

		return gc.getTime();
	}
	
	private Date generateTicketRegisteredTimeForNotCustomer(Date departureTime) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(departureTime);
		
		gc.set(Calendar.DAY_OF_YEAR, gc.get(Calendar.DAY_OF_YEAR)-randBetween(1, 10));
		
		changeRandomHourAndMinute(gc);
		
		return gc.getTime();
	}

	private boolean isMaxCountReachForDepartureAndArrival(int departureIndex, int arrivalIndex, List<Ticket> ticketList, int maxSeatCount, Stop[] stops) {
		int passengerCount = calculatePassengerCountForDepartureAndArrival(departureIndex, arrivalIndex, ticketList, maxSeatCount, stops);
		if(passengerCount == maxSeatCount) {
			return true;
		} else {
			return false;
		}
	}
	
	private int calculatePassengerCountForDepartureAndArrival(int departureIndex, int arrivalIndex, List<Ticket> ticketList, int maxSeatCount, Stop[] stops) {
		LinkedList<Byte> seatNumberList = generateSeatNumberList(maxSeatCount);
		int passengerCount = 0;
		for(int i=0; i<=arrivalIndex-1; i++) {
			int jIndex = departureIndex+1;
			if (i>=departureIndex+1) {
				jIndex = i+1;
			}
			for(int j=jIndex; j<stops.length; j++) {
				if(ticketList.size() != 0) {
					for(Ticket ticket : ticketList) {
						if(ticket.getDeparture().equals(stops[i].getCity()) && ticket.getArrival().equals(stops[j].getCity())) {
							if(isSeatNumberExist(seatNumberList, ticket.getSeatNumber())){
								seatNumberList.remove(ticket.getSeatNumber());
								passengerCount++;
							} else {
								continue;
							}
						}
					}
				}
			}
		}
		return passengerCount;
	}
	
	private LinkedList<Byte> getEmptySeatNumbersForDepartureAndArrival(int departureIndex, int arrivalIndex, List<Ticket> ticketList, int maxSeatCount, Stop[] stops) {
		LinkedList<Byte> emptySeatNumberList = generateSeatNumberList(maxSeatCount);
		for(int i=0; i<=arrivalIndex-1; i++) {
			int jIndex = departureIndex+1;
			if (i>=departureIndex+1) {
				jIndex = i+1;
			}
			for(int j=jIndex; j<stops.length; j++) {
				if(ticketList.size() != 0) {
					for(Ticket ticket : ticketList) {
						if(ticket.getDeparture().equals(stops[i].getCity()) && ticket.getArrival().equals(stops[j].getCity())) {
							emptySeatNumberList.remove(ticket.getSeatNumber());
						}
					}
				}
			}
		}
		return emptySeatNumberList;
	}
	
	private LinkedList<Byte> generateSeatNumberList(int maxSeatCount) {
		LinkedList<Byte> seatNumberList = new LinkedList<Byte>();
		for(int number=1; number<=maxSeatCount; number++) {
			seatNumberList.add(new Byte(String.valueOf(number)));
		}
		return seatNumberList;
	}
	
	private BigDecimal getTicketPrice(City departure, City arrival, boolean isReservation) {
		BigDecimal ticketPrice = new BigDecimal(0);
		if(!isReservation) {
			ticketPrice = ticketService.calculateTicketPriceByDepartureAndArrival(departure, arrival);
		}
		return ticketPrice;
	}
	
	@SuppressWarnings("unused")
	private void populateTicketWithCustomerAndPassengerInfo(Ticket ticket, int voyageNumber, GregorianCalendar departureTime, int dayOfYearForToday) {
		Integer[][] seatNumbers = generateSeatArray();
		String[][] seats = new String[13][3];
		Integer[] seatIndex = findIndexOfSeat(seatNumbers, ticket.getSeatNumber().intValue());
		Gender gender = generateGenderForSeat(seats, seatIndex);
		int dayOfYearForDepartureTime = departureTime.get(Calendar.DAY_OF_YEAR);
		Date ticketRegisterTime = new Date();
		int randomNumber = randBetween(0, 150);
		if (randomNumber <= 100) { 
			// Customer or By Customer
			Customer customer = null;
			UserRole userRoleForCustomer = new UserRole("ROLE_USER");
			customer = new Customer();
			customer.setGender(gender);
			String[] name = generateName(customer.getGender());
			customer.setTcNumber(generateRandomTcNumber());
			customer.setName(name[0]);
			customer.setSurname(name[1]); 
			customer.setDateOfBirth(generateRandomDateMaximum18Year());
			customer.setMobileNumber(generateRandomGsmNumber());
			customer.seteMail(generateNameForEmail(name[0]) + "@abc.com");
//			customer.setPassword(passwordEncoder.encode(generateRandomPassword()));
//			customer.setPassword(passwordList.removeFirst());
//				customer.setPassword("$2a$10$oZWHYFiGSZo/EtUL1S6MzuCRgb9CWDWyXBQZZdvzOBEyjAmzqhDou"); // obsProject
			customer.setPassword("obsProject1"); // obsProject1
			customer.setDateOfRegister(generateCustomerRegisteredTime(150, dayOfYearForToday-dayOfYearForDepartureTime));
			customer.setTimeOfLastOnline(generateCustomerLastOnlineTimeForTicket(departureTime.getTime()));
			customer.setEnabled(true);
//				customerService.create(customer);
			userRoleForCustomer.setUser(customer);
			userRoleService.create(userRoleForCustomer);
			if(randomNumber <50) {
				// Customer
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(customer.getTimeOfLastOnline());
				gc.add(Calendar.MINUTE, randBetween(5, 20));
				ticketRegisterTime = gc.getTime();
				ticket.setRegisterTime(ticketRegisterTime);
				ticket.setCustomer(customer);
				ticket.setPassangerTcNumber(customer.getTcNumber());
				ticket.setPassangerName(customer.getName());
				ticket.setPassangerSurname(customer.getSurname());
				ticket.setPassangerGender(customer.getGender());
				Long ticketId = ticketService.create(ticket);
			} else if(randomNumber >= 50 & randomNumber <= 100) {
				// By Customer
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(customer.getTimeOfLastOnline());
				gc.add(Calendar.MINUTE, randBetween(5, 20));
				ticketRegisterTime = gc.getTime();
				ticket.setRegisterTime(ticketRegisterTime);
				ticket.setCustomer(customer);
				ticket.setPassangerTcNumber(generateRandomTcNumber());
				String[] passangerName = generateName(gender);
				ticket.setPassangerName(passangerName[0]);
				ticket.setPassangerSurname(passangerName[1]);
				ticket.setPassangerGender(gender);
				Long ticketId = ticketService.create(ticket);
			}
		} else {
			// Not customer
			ticketRegisterTime = generateTicketRegisteredTimeForNotCustomer(departureTime.getTime());
			ticket.setRegisterTime(ticketRegisterTime);
			ticket.setPassangerTcNumber(generateRandomTcNumber());
			String[] passangerName = generateName(gender);
			ticket.setPassangerName(passangerName[0]);
			ticket.setPassangerSurname(passangerName[1]);
			ticket.setPassangerGender(gender);
			Long ticketId = ticketService.create(ticket);
		}
	}
	
	private void changeRandomHourAndMinute(GregorianCalendar gc) {
		int hourOfDay = randBetween(0, 23);
		gc.set(Calendar.HOUR_OF_DAY, hourOfDay);

		int minute = randBetween(0, 59);
		gc.set(Calendar.MINUTE, minute);

		int second = randBetween(0, 59);
		gc.set(Calendar.SECOND, second);
	}
	
	private Integer[][] generateSeatArray() {
		Integer[][] seatArray = new Integer[13][3];
		Integer seatNumber = 1;
		for(int i=0; i<seatArray.length; i++) {
			for(int j=0; j<seatArray[i].length; j++) {
			    if(i==7) {
			        if(j>0) {
			            seatArray[i][j] = null;
			            continue;
			        }
			        seatArray[i][j] = seatNumber;
			    } else {
			        seatArray[i][j] = seatNumber;
			    }
			    seatNumber++;
			}
		}
		
		return seatArray;
	}
	
	
	private Integer[] findIndexOfSeat(Integer[][] seatArray, int seatNumber) {
		Integer[] index = new Integer[2];
		
		for(int i=0; i<seatArray.length; i++) {
			for(int j=0; j<seatArray[i].length; j++) {
				if(seatArray[i][j] != null && seatArray[i][j] == seatNumber) {
					index[0] = i;
					index[1] = j;
				}
			}
		}
		
		return index;
	}
	
	private String generateNameForEmail(String name) {
		for(char c : name.toCharArray()) {
			switch(c) {
			case 'ş':
			case 'Ş':
				name = name.replace(c, 's');
				break;
			case 'ç':
			case 'Ç':
				name = name.replace(c, 'c');
				break;
			case 'ı':
			case 'I':
			case 'İ':
				name = name.replace(c, 'i');
				break;
			case 'ğ':
			case 'Ğ':
				name = name.replace(c, 'g');
			case 'ü':
			case 'Ü':
				name = name.replace(c, 'u');
				break;
			case 'ö':
			case 'Ö':
				name = name.replace(c, 'o');
				break;
			}
		}
		return name.toLowerCase();
	}

	private void updateIncomeByTotalTicketPrice(Voyage voyage, Date ticketRegisterTime, BigDecimal totalTicketPrice) {
		Income incomeFromDb = incomeService.findByVoyage(voyage);
		if(incomeFromDb == null) {
			Income income = new Income(voyage, ticketRegisterTime, totalTicketPrice);							
			incomeService.create(income);
		} else {
			incomeFromDb.setPrice(totalTicketPrice);
			incomeFromDb.setRegisteredTime(ticketRegisterTime);
			incomeService.update(incomeFromDb);
		}
	}
	
	private boolean isSeatNumberExist(LinkedList<Byte> seatNumberList, Byte seatNumberForSearch) {
		boolean isFound = false;
		if(!seatNumberList.isEmpty()) {
			for(Byte seatNumber : seatNumberList) {
				if(seatNumber.equals(seatNumberForSearch)) {
					isFound = true;
					break;
				}
			}
		}
		return isFound;
	}
	
	private static long calculateDifferenceBetweenDatesInDay(GregorianCalendar startDate, GregorianCalendar endDate) {
		
		long diffInMilliSeconds = endDate.getTimeInMillis() - startDate.getTimeInMillis(); 
		return diffInMilliSeconds / (24 * 60 * 60 * 1000);
	}
	
	private BigDecimal calculateTicketPriceForDistance(City departure, City arrival) {
		BigDecimal price = new BigDecimal(0);
		Integer distance = cityDistanceService.findByDepartureAndArrival(departure, arrival).getDistance();
		double pricePerDistance = 0.0;
		if(distance <= 200) {
			pricePerDistance = 0.20;
		} else if(distance > 200 && distance <= 400) {
			pricePerDistance = 0.17;
		} else if(distance > 400 && distance <= 600) {
			pricePerDistance = 0.15;
		} else if(distance > 600 && distance <= 800) {
			pricePerDistance = 0.14;
		} else if(distance > 800 && distance <= 1000) {
			pricePerDistance = 0.12;
		} else if(distance > 1000 && distance <= 1200) {
			pricePerDistance = 0.11;
		} else if(distance > 1200 && distance <= 1600) {
			pricePerDistance = 0.10;
		} else if(distance > 1600) {
			pricePerDistance = 0.09;
		}
 		
		price = new BigDecimal(pricePerDistance).multiply(new BigDecimal(distance));
		return price;
	}
}
