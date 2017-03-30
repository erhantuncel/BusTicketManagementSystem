package com.erhan.onlinebilet.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Expense;
import com.erhan.onlinebilet.model.ExpenseType;
import com.erhan.onlinebilet.model.Gender;
import com.erhan.onlinebilet.model.Income;
import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Stop;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.UserRole;
import com.erhan.onlinebilet.model.Vehicle;
import com.erhan.onlinebilet.model.VehicleBrand;
import com.erhan.onlinebilet.model.VehicleModel;
import com.erhan.onlinebilet.model.Voyage;

@Service("sampleDataService")
public class SampleDataServiceImpl implements SampleDataService {
	// protected LinkedList<String> names;
	protected List<String> maleNames;
	protected List<String> femaleNames;
	protected List<String> surnames;
	protected List<String> passwords;

//	protected Customer[] customers;
//	protected Long[] voyageIds;
//	protected Long[] ticketIds;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	StopService stopService;
	
	@Autowired
	VehicleModelService vehicleModelService;
	
	@Autowired
	VehicleBrandService vehicleBrandService;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	VoyageService voyageService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	IncomeService incomeService;
	
	@Autowired
	ExpenseTypeService expenseTypeService;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unused")
	public void populateData() {
		maleNames = populateNames("maleNames.txt");
		femaleNames = populateNames("femaleNames.txt");
		surnames = populateNames("surnames.txt");
		passwords = populateNames("encodedPasswords.txt");
		
		LinkedList<String> passwordList = getListOfDataFromTxtFile("encodedPasswords.txt");
		

		
		// Creating Admin account (Erhan)
		UserRole userRoleErhan = new UserRole("ROLE_ADMIN");
		Customer adminErhan = new Customer();
		adminErhan.setTcNumber("10987654321");
		adminErhan.setName("Erhan");
		adminErhan.setSurname("TUNÇEL");
		adminErhan.setGender(Gender.ERKEK);
		adminErhan.setDateOfBirth(generateRandomDateMaximum18Year());
		adminErhan.setMobileNumber(generateRandomGsmNumber());
		adminErhan.seteMail(generateNameForEmail("erhan")+"@abc.com");
		adminErhan.setPassword(passwordEncoder.encode("admin"));
		adminErhan.setDateOfRegister(generateCustomerRegisteredTime(180, 150));
		adminErhan.setTimeOfLastOnline(generateCustomerLastOnlineTime(16, 0));
		adminErhan.setEnabled(true);
		userRoleErhan.setUser(adminErhan);
		userRoleService.create(userRoleErhan);
		
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
		userSerhan.setPassword(passwordEncoder.encode("user"));
		userSerhan.setDateOfRegister(generateCustomerRegisteredTime(180, 150));
		userSerhan.setTimeOfLastOnline(generateCustomerLastOnlineTime(16, 0));
		userSerhan.setEnabled(true);
		userRoleSerhan.setUser(userSerhan);
		userRoleService.create(userRoleSerhan);
		
		// Customers	
		int customerCount = 0;
		while (customerCount < 24) {
			Gender gender = Gender.getRandom();
			String[] name = generateName(gender);
			UserRole userRole = new UserRole("ROLE_USER");
			Customer customer = new Customer();
			customer.setTcNumber(generateRandomTcNumber());
			customer.setName(name[0]);
			customer.setSurname(name[1]);
			customer.setGender(gender);
			customer.setDateOfBirth(generateRandomDateMaximum18Year());
			customer.setMobileNumber(generateRandomGsmNumber());
			customer.seteMail(generateNameForEmail(name[0])+"@abc.com");
//			customer.setPassword(passwordEncoder.encode(generateRandomPassword()));
//			customer.setPassword(passwordList.removeFirst());
			customer.setPassword("$2a$10$oZWHYFiGSZo/EtUL1S6MzuCRgb9CWDWyXBQZZdvzOBEyjAmzqhDou");
			customer.setDateOfRegister(generateCustomerRegisteredTime(150, 20));
			customer.setTimeOfLastOnline(generateCustomerLastOnlineTime(16, 0));
			customer.setEnabled(true);
			userRole.setUser(customer);
			userRoleService.create(userRole);
			customerCount++;
		}
		
		// Route And Stops
		LinkedList<String> stopDataLists = getListOfDataFromTxtFile("routeAndStops.txt");
		Stop stop;
		int stopListSize = stopDataLists.size();
		for(int i=0; i<stopListSize; i++) {
			String[] stopData = stopDataLists.removeFirst().split(",");
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
			route.getStops().add(stop);
			routeService.create(route);
//			System.out.println("Stop Created : " + stop.getCity().getCityName());
		}
		
		// Vehicle Brands And Models
		LinkedList<String> vehicleModelDataList = getListOfDataFromTxtFile("vehicleList.txt");
		LinkedList<VehicleModel> vehicleModelList = new LinkedList<VehicleModel>();
		VehicleModel vehicleModel;
		int vehicleModelListSize = vehicleModelDataList.size();
		for(int i=0; i<vehicleModelListSize; i++) {
			String[] vehicleModelData = vehicleModelDataList.removeFirst().split(",");
			VehicleBrand brand = vehicleBrandService.findByName(vehicleModelData[0]);
			if(brand == null) {
				brand = new VehicleBrand(vehicleModelData[0]);
			}
			vehicleModel = new VehicleModel(vehicleModelData[1], brand);
			brand.getVehicleModelList().add(vehicleModel);
			vehicleBrandService.create(brand);
			vehicleModelList.add(vehicleModel);
		}
		
		// Vehicles
		for(int i=0; i<12; i++) {
			int platePostfix = 40 + i;
			String plate = "14BL1" + platePostfix;
			Integer seatCount = 37;
//			VehicleModel model = vehicleModelService.findById(Long.valueOf(String.valueOf(randBetween(1, 7))));
//			VehicleModel model = vehicleModelService.findById(new Long(randBetween(1, 7)));
			VehicleModel model = vehicleModelList.get(randBetween(0, 6));
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
			Vehicle vehicle = new Vehicle(plate, seatCount, model, String.valueOf(year), milage);
			vehicleService.create(vehicle);
		}
		
		// Voyages
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
		
		for(Voyage voyage : voyages) {
			
			/*
			 * Güne göre her durakta olması gereken en az ve en çok yolcu sayısını belirle
			 * Kalkış ve varış duraklarına göre olan tüm kombinasyonlar için rastgele yolcu 
			   sayısı atayarak matris oluştur.
			 * Yolcu sayısı kadar kalkış ve varış noktalarına göre koltuk numaralarını belirle.
			 * - Rastgele Kalkış ve varış şehirlerini seç. (Rotaya uyumlu olarak)
			 * - Seçilen rotada boş koltukları bul
			 * - Boş koltuklardan birini seçip yolcu listesine ekle
			 */
			
			int seatCount = 37;
			Stop[] stops = (Stop[]) voyage.getRoute().getStops().toArray(new Stop[voyage.getRoute().getStops().size()]);
			
			GregorianCalendar today = new GregorianCalendar();
			today.setTime(new Date());
			int dayOfYearForToday = today.get(Calendar.DAY_OF_YEAR);
			
			GregorianCalendar departureTime = new GregorianCalendar();
			departureTime.setTime(voyage.getDepartureTime());
			
			int passengerCount = 0;
			Integer[][] seatNumbers = generateSeatArray();
			String[][] seats = new String[13][3];
			
			Integer minPassengerCount = 0;
			Integer maxPassengerCount = 0;
			
			
//			Set<Stop> voyageStopSet = voyage.getRoute().getStops();
//			List<Stop> voyageStopList = new ArrayList<Stop>(voyage.getRoute().getStops());
			long differenceOfDepartureAndToday = calculateDifferenceBetweenDatesInDay(departureTime, today);
			if(differenceOfDepartureAndToday == -2l) {
//				passengerCount = Math.round((voyageStopSet.size() - 1) * seatCount * 80 / 100);
				minPassengerCount = 6;
				maxPassengerCount = 12;
			} else if (differenceOfDepartureAndToday == -1l) {
//				passengerCount = randBetween(20, 37);
				minPassengerCount = 12;
				maxPassengerCount = 18;
			} else if(differenceOfDepartureAndToday == 0) {
//				passengerCount = randBetween(5, 30);
				minPassengerCount = 18;
				maxPassengerCount = 24;
			} else if(differenceOfDepartureAndToday == 1l) {
//				passengerCount = randBetween(0, 20);
				minPassengerCount = 24;
				maxPassengerCount = 30;
			} else if(differenceOfDepartureAndToday == 2l) {
//				passengerCount = randBetween(0, 10);
				minPassengerCount = 30;
				maxPassengerCount = 37;
			}
			
			
			// Initialize ticketCountMatrix
			Integer[][] ticketCountMatrix = new Integer[stops.length][stops.length];
			populateTicketCountMatrix(stops, ticketCountMatrix, minPassengerCount, maxPassengerCount);
			
			List<Ticket> ticketList = new ArrayList<Ticket>();
			for(int i=0; i<stops.length; i++) {
				for(int j=i+1; j<stops.length; j++) {
					passengerCount = ticketCountMatrix[i][j];
					while(passengerCount > 0) {
						
						// Create empty seat number list
						LinkedList<Byte> emptySeatNumberList = new LinkedList<Byte>();
						for(int number=1; number<=seatCount; number++) {
							emptySeatNumberList.add(new Byte(String.valueOf(number)));
						}
						
						// Remove seat number that is selected before
						if(!ticketList.isEmpty()) {
							for(Ticket ticket : ticketList) {
								for(int stopIndex = i+1; stopIndex<stops.length; stopIndex++) {
									if(ticket.getArrival().equals(stops[stopIndex].getCity())) {
										emptySeatNumberList.remove(ticket.getSeatNumber());
									}
								}
							}
						}
						
						City departure = stops[i].getCity();
						City arrival = stops[j].getCity();
						Collections.shuffle(emptySeatNumberList);
						if(emptySeatNumberList.isEmpty()) {
							continue;
						}
						Ticket ticket = new Ticket();
						ticket.setDeparture(departure);
						ticket.setArrival(arrival);
						Byte seatNumber = emptySeatNumberList.removeFirst();
						ticket.setSeatNumber(seatNumber);
						
						
						// Ticket details
						boolean isReservation = new  Random().nextBoolean();
						Integer[] seatIndex = findIndexOfSeat(seatNumbers, seatNumber.intValue());
						Gender gender = generateGenderForSeat(seats, seatIndex);
						int dayOfYearForDepartureTime = departureTime.get(Calendar.DAY_OF_YEAR);
						int randomNumber = randBetween(0, 150);
						if (randomNumber <= 100) { 
							// Customer or By Customer
							Customer customer = new Customer();
							customer.setGender(gender);
							String[] name = generateName(customer.getGender());
							customer.setTcNumber(generateRandomTcNumber());
							customer.setName(name[0]);
							customer.setSurname(name[1]); 
							customer.setDateOfBirth(generateRandomDateMaximum18Year());
							customer.setMobileNumber(generateRandomGsmNumber());
							customer.seteMail(generateNameForEmail(name[0]) + "@abc.com");
//							customer.setPassword(passwordEncoder.encode(generateRandomPassword()));
//							customer.setPassword(passwordList.removeFirst());
							customer.setPassword("$2a$10$oZWHYFiGSZo/EtUL1S6MzuCRgb9CWDWyXBQZZdvzOBEyjAmzqhDou");
							customer.setDateOfRegister(generateCustomerRegisteredTime(150, dayOfYearForToday-dayOfYearForDepartureTime));
							customer.setTimeOfLastOnline(generateCustomerLastOnlineTimeForTicket(departureTime.getTime()));
							customer.setEnabled(true);
							if(randomNumber <50) {
								// Customer
								ticket.setIsReservation(isReservation);
								ticket.setVoyage(voyage);
								GregorianCalendar gc = new GregorianCalendar();
								gc.setTime(customer.getTimeOfLastOnline());
								gc.add(Calendar.MINUTE, randBetween(5, 20));
								ticket.setRegisterTime(gc.getTime());
								ticket.setCustomer(customer);
								ticket.setPassangerTcNumber(customer.getTcNumber());
								ticket.setPassangerName(customer.getName());
								ticket.setPassangerSurname(customer.getSurname());
								ticket.setPassangerGender(customer.getGender());
								Long ticketId = ticketService.create(ticket);
							} else if(randomNumber > 50 & randomNumber <= 100) {
								// By Customer
								ticket.setIsReservation(isReservation);
								ticket.setVoyage(voyage);
								GregorianCalendar gc = new GregorianCalendar();
								gc.setTime(customer.getTimeOfLastOnline());
								gc.add(Calendar.MINUTE, randBetween(5, 20));
								ticket.setRegisterTime(gc.getTime());
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
							ticket.setIsReservation(isReservation);
							ticket.setVoyage(voyage);
							ticket.setRegisterTime(generateTicketRegisteredTimeForNotCustomer(departureTime.getTime()));
							ticket.setPassangerTcNumber(generateRandomTcNumber());
							String[] passangerName = generateName(gender);
							ticket.setPassangerName(passangerName[0]);
							ticket.setPassangerSurname(passangerName[1]);
							ticket.setPassangerGender(gender);
							Long ticketId = ticketService.create(ticket);
						}
						ticketList.add(ticket);
						passengerCount--;
					}
				}
			}
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(voyage.getDepartureTime());
			Set<Stop> stopList = voyage.getRoute().getStops();
			Stop lastStop = (Stop) stopList.toArray()[stopList.size()-1];
			gc.add(Calendar.MINUTE, lastStop.getDuration());
			gc.add(Calendar.HOUR_OF_DAY, 6);
			gc.add(Calendar.MINUTE, 15);
			
			GregorianCalendar now = new GregorianCalendar();
			now.setTime(new Date());
			if(gc.before(now)) {				
				Integer ticketCount = ticketService.countTicketByVoyage(voyage);
				// TODO: create method for calculate ticket price based on departure and arrival
				BigDecimal totalPrice = new BigDecimal("1").multiply(new BigDecimal(ticketCount.intValue()));
				Date incomeRegisterTime = gc.getTime();
				Income income = new Income(voyage, incomeRegisterTime, totalPrice);
				incomeService.create(income);
			}
		}
		
		// Backup start
//		for(Voyage voyage : voyages) {
//			
//			/*
//			 * Güne göre her durakta olması gereken en az ve en çok yolcu sayısını belirle
//			 * Kalkış ve varış duraklarına göre olan tüm kombinasyonlar için rastgele yolcu 
//			   sayısı atayarak matris oluştur.
//			 * Yolcu sayısı kadar kalkış ve varış noktalarına göre koltuk numaralarını belirle.
//			 * - Rastgele Kalkış ve varış şehirlerini seç. (Rotaya uyumlu olarak)
//			 * - Seçilen rotada boş koltukları bul
//			 * - Boş koltuklardan birini seçip yolcu listesine ekle
//			 */
//			
//			GregorianCalendar today = new GregorianCalendar();
//			today.setTime(new Date());
//			int dayOfYearForToday = today.get(Calendar.DAY_OF_YEAR);
//			
//			GregorianCalendar departureTime = new GregorianCalendar();
//			departureTime.setTime(voyage.getDepartureTime());
//			
//			int passengerCount = 0;
//			Integer[][] seatNumbers = generateSeatArray();
//			String[][] seats = new String[13][3];
//			
//			if(departureTime.get(Calendar.DAY_OF_YEAR) == dayOfYearForToday-2) {
//				passengerCount = randBetween(20, 37);
//			} else if (departureTime.get(Calendar.DAY_OF_YEAR) == dayOfYearForToday-1) {
//				passengerCount = randBetween(20, 37);
//			} else if(departureTime.get(Calendar.DAY_OF_YEAR) == dayOfYearForToday) {
//				passengerCount = randBetween(5, 30);
//			} else if(departureTime.get(Calendar.DAY_OF_YEAR) == dayOfYearForToday+1) {
//				passengerCount = randBetween(0, 20);
//			} else if(departureTime.get(Calendar.DAY_OF_YEAR) == dayOfYearForToday+2) {
//				passengerCount = randBetween(0, 10);
//			}
//			
//			Set<Byte> seatNumberSet = new LinkedHashSet<Byte>();
//			for(int i=1; i<=passengerCount; i++) {
//				Byte seatNumber = new Byte(String.valueOf(randBetween(1, 37)));
//				seatNumberSet.add(seatNumber);
//			}
//			
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
//					
//					String[] name = generateName(gender);
//					
//					UserRole userRole = new UserRole("ROLE_USER");
//					Customer customer = new Customer();
//					customer.setTcNumber(generateRandomTcNumber());
//					customer.setName(name[0]);
//					customer.setSurname(name[1]);
//					customer.setGender(gender);
//					customer.setDateOfBirth(generateRandomDateMaximum18Year());
//					customer.setMobileNumber(generateRandomGsmNumber());
//					customer.seteMail(generateNameForEmail(name[0])+"@abc.com");
////					customer.setPassword(passwordEncoder.encode(generateRandomPassword()));
//					customer.setPassword(passwordList.removeFirst());
//					customer.setDateOfRegister(generateCustomerRegisteredTime(150, dayOfYearForToday-dayOfYearForDepartureTime));
//					customer.setTimeOfLastOnline(generateCustomerLastOnlineTimeForTicket(departureTime.getTime()));
//					customer.setEnabled(true);
//					userRole.setUser(customer);
//					userRoleService.create(userRole);
//					
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
////			System.out.println("Route : " + voyage.getRoute().getRouteName());
////			System.out.println("Stop List Size :" + stopList.size());
//			Stop lastStop = (Stop) stopList.toArray()[stopList.size()-1];
//			gc.add(Calendar.MINUTE, lastStop.getDuration());
//			gc.add(Calendar.HOUR_OF_DAY, 6);
//			gc.add(Calendar.MINUTE, 15);
//			
//			GregorianCalendar now = new GregorianCalendar();
//			now.setTime(new Date());
//			if(gc.before(now)) {				
//				Integer ticketCount = ticketService.countTicketByVoyage(voyage);
//				BigDecimal totalPrice = new BigDecimal(0.0);
//				Date incomeRegisterTime = gc.getTime();
//				Income income = new Income(voyage, incomeRegisterTime, totalPrice);
//				incomeService.create(income);
//			}
//		}
		
		// Backup End
		
		// Expenses
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
			Expense fuelExpense = new Expense(new BigDecimal(fuelPrice), fuelExpenseType, expenseTime.getTime()); 
			Expense terminalExpense = new Expense(new BigDecimal(terminalPrice), terminalExpenseType, expenseTime.getTime());
			Expense snackExpense = new Expense(new BigDecimal(snackPrice), snackExpenseType, expenseTime.getTime());
			voyage.getExpenseList().add(fuelExpense);
			voyage.getExpenseList().add(terminalExpense);
			voyage.getExpenseList().add(snackExpense);
			
			int rnd = randBetween(0, 15);
			if(rnd > 5 & rnd <= 10) {
				Expense penaltyExpense = new Expense(new BigDecimal(penaltyPrice), penaltyExpenseType, expenseTime.getTime());
				voyage.getExpenseList().add(penaltyExpense);
			} else if (rnd > 10) {
				Expense maintenanceExpense = new Expense(new BigDecimal(maintenancePrice), maintenanceExpenseType, expenseTime.getTime());
				voyage.getExpenseList().add(maintenanceExpense);
			}
			voyageService.update(voyage);
		}
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
	
	private int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
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
	
	private String generateRandomGsmNumber() {
		String[] prefix = new String[] { "533", "532", "535", "536", "543", "542", "544", "505", "506" };

		int number = 1111111 + (int) (Math.random() * (9999999 - 1111111));

		return prefix[randBetween(0, 8)] + String.valueOf(number);
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
	
	private void changeRandomHourAndMinute(GregorianCalendar gc) {
		int hourOfDay = randBetween(0, 23);
		gc.set(Calendar.HOUR_OF_DAY, hourOfDay);

		int minute = randBetween(0, 59);
		gc.set(Calendar.MINUTE, minute);

		int second = randBetween(0, 59);
		gc.set(Calendar.SECOND, second);
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
	
	private String generateRandomTcNumber() {
		long number = 11111111111L + (long) (Math.random() * (59999999999L - 11111111111L));
		String tcNumber = Long.toString(number);
		return tcNumber;
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
	
	private Integer[][] generateSeatArray() {
		Integer[][] seatArray = new Integer[13][3];
		Integer seatNumber = 1;
		for(int i=0; i<seatArray.length; i++) {
			for(int j=0; j<seatArray[i].length; j++) {
			    if(i == 7 && j > 0) {
		            seatArray[i][j] = null;
		            continue;
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
	
	private Date generateTicketRegisteredTimeForNotCustomer(Date departureTime) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(departureTime);
		
		gc.set(Calendar.DAY_OF_YEAR, gc.get(Calendar.DAY_OF_YEAR)-randBetween(1, 10));
		
		changeRandomHourAndMinute(gc);
		
		return gc.getTime();
	}
	
	private void populateTicketCountMatrix(Stop[] stops, Integer[][] ticketCountMatrix,
			Integer minPassengerCount, Integer maxPassengerCount) {
		for(int i=0; i<ticketCountMatrix.length; i++) {
			int randStart = 0;
			int randEnd = 0;
			if(i == 0) {
				randStart = minPassengerCount / (stops.length-1);
				randEnd = maxPassengerCount / (stops.length-1);
			} else  {
				int passangerCount = calculatePassangerCountForStop(ticketCountMatrix, i-1, i+1);
				int remainStopsCount = stops.length - (i+1);
				if(remainStopsCount != 0) {					
					randStart = (minPassengerCount - passangerCount) / remainStopsCount;
					if(randStart<0) {
						randStart = 0;
					}
					randEnd = (maxPassengerCount - passangerCount) / remainStopsCount;
				} else {
					randStart = 0;
					randEnd = maxPassengerCount / (stops.length - 1);
				}
			} 
			for(int j=i+1; j<ticketCountMatrix[i].length; j++) {
				ticketCountMatrix[i][j] = randBetween(randStart, randEnd);
			}
		}
	}
	
	private static Integer calculatePassangerCountForStop(Integer[][] ticketCountMatrix, int rowIndex, int columnIndex) {
		Integer sum = 0;
		for(int i=rowIndex; i>=0; i--) {
			for(int j=columnIndex; j<ticketCountMatrix[rowIndex].length; j++) {
				if(ticketCountMatrix[i][j] == null) {
					continue;
				} else {					
					sum = sum + ticketCountMatrix[i][j];
				}
			}
		}
		return sum;
	}
	
	private static long calculateDifferenceBetweenDatesInDay(GregorianCalendar startDate, GregorianCalendar endDate) {
		
		long diffInMilliSeconds = endDate.getTimeInMillis() - startDate.getTimeInMillis(); 
		return diffInMilliSeconds / (24 * 60 * 60 * 1000);
	}
	
	
}