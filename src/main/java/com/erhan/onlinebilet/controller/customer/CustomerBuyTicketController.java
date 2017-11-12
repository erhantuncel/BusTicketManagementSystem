package com.erhan.onlinebilet.controller.customer;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.onlinebilet.model.BuyTicketForm;
import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Gender;
import com.erhan.onlinebilet.model.SeatForBuyTicketForm;
import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.Voyage;
import com.erhan.onlinebilet.service.CityService;
import com.erhan.onlinebilet.service.CustomerService;
import com.erhan.onlinebilet.service.RouteService;
import com.erhan.onlinebilet.service.TicketService;
import com.erhan.onlinebilet.service.VoyageService;

@Controller
public class CustomerBuyTicketController {

	@Autowired
	VoyageService voyageService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	CustomerService customerService;
	
	
	@RequestMapping(value="/musteri/seferler", method=RequestMethod.GET)
	public ModelAndView showSearchVoyagePage(ModelAndView model) {
		
		populateModelWithCity(cityService.findAll(), model);	
		model.setViewName("musteri/seferler");
		return model;
	}
	
	@RequestMapping(value="musteri/seferler", method=RequestMethod.POST)
	public ModelAndView searchVoyageForBuyTicket(@RequestParam(value = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date, 
												@RequestParam("departureCity") String departureCityId,
												@RequestParam("arrivalCity") String arrivalCityId,
												@RequestParam(value = "isReservation", required=false) String[] isReservation,
												ModelAndView model, HttpSession session) {
		City departureCity = cityService.findById(new Long(departureCityId));
		City arrivalCity = cityService.findById(new Long(arrivalCityId));
		List<Route> routeList = routeService.findAllByDepartureAndArrival(departureCity, arrivalCity);
		List<Voyage> voyageList = voyageService.findAllByRouteAndDate(routeList, date);
		model.addObject("voyageList", voyageList);
				
		Ticket ticketForSave = new Ticket();
		ticketForSave.setDeparture(departureCity);
		ticketForSave.setArrival(arrivalCity);
		if((isReservation == null)) {
			ticketForSave.setIsReservation(false);
		} else {
			ticketForSave.setIsReservation(true);
		}
		
		BigDecimal ticketPrice = ticketService.calculateTicketPriceByDepartureAndArrival(departureCity, arrivalCity);
		ticketForSave.setPrice(ticketPrice);						
		session.setAttribute("ticketForSave", ticketForSave);
		
		populateModelWithCity(cityService.findAll(), model);
		model.setViewName("musteri/seferler");
		return model;
	}
	
	
	@RequestMapping(value="/musteri/yolcubilgileri", method=RequestMethod.POST)
	public ModelAndView passangerInfoPOST(@RequestParam("selectedSeatNumbers") List<String> seatNumbers, 
			HttpSession session, ModelAndView model) {
		List<SeatForBuyTicketForm> seatListForBuyTicket = new ArrayList<SeatForBuyTicketForm>(0);
		
		for(String seatNumber : seatNumbers) {
			SeatForBuyTicketForm seat = new SeatForBuyTicketForm();
			seat.setSeatNumber(new Byte(seatNumber));
			seatListForBuyTicket.add(seat);
		}
		
		session.setAttribute("seatListForBuyTicket", seatListForBuyTicket);
		model.setViewName("redirect:" + "/musteri/yolcubilgileri");	
		return model;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/musteri/yolcubilgileri", method=RequestMethod.GET)
	public ModelAndView passangerInfoGET(HttpSession session, SessionStatus status, ModelAndView model) {
		List<SeatForBuyTicketForm> seatListForBuyTicket = (List<SeatForBuyTicketForm>) session.getAttribute("seatListForBuyTicket");
		BuyTicketForm buyTicketForm = new BuyTicketForm();
		buyTicketForm.setSeatList(seatListForBuyTicket);
		model.addObject("buyTicketForm", buyTicketForm);
		model.addObject("genderValues", Gender.values());
		model.setViewName("musteri/yolcuBilgileri");	
		return model;
	}
	
	@RequestMapping(value="/musteri/odemeRezervasyon", method=RequestMethod.POST)
	public ModelAndView payTicketPrice(@ModelAttribute("buyTicketForm") @Valid BuyTicketForm buyTicketForm, BindingResult result,
			RedirectAttributes redir, ModelAndView model, HttpSession session) {
		
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error.toString());
			}
			model.setViewName("musteri/yolcuBilgileri");
		} else {			
			Ticket ticketForSave = (Ticket) session.getAttribute("ticketForSave");
			List<Long> createdTicketIdList = new ArrayList<Long>(0);
			
			GregorianCalendar gc = new GregorianCalendar();
			LocalTime localTime = LocalTime.now();
			System.out.println("Local Time = " + localTime);
			gc.setTime(new Date());
			
			for(SeatForBuyTicketForm seat : buyTicketForm.getSeatList()) {
				Ticket tempTicket = new Ticket();
				tempTicket.setIsReservation(ticketForSave.getIsReservation());
				tempTicket.setRegisterTime(gc.getTime());
				tempTicket.setDeparture(ticketForSave.getDeparture());
				tempTicket.setArrival(ticketForSave.getArrival());
				tempTicket.setVoyage(ticketForSave.getVoyage());
				tempTicket.setPrice(ticketForSave.getPrice());
				tempTicket.setSeatNumber(seat.getSeatNumber());
				tempTicket.setPassangerTcNumber(seat.getPassangerTcNumber());
				tempTicket.setPassangerGender(seat.getPassangerGender());
				tempTicket.setPassangerName(seat.getPassangerName());
				tempTicket.setPassangerSurname(seat.getPassangerSurname());
				Customer customer = getCustomer();
				if(customer != null) {
					tempTicket.setCustomer(customer);
				}
				Long id = ticketService.create(tempTicket);
				createdTicketIdList.add(id);
			}
			
			if(createdTicketIdList.size() == buyTicketForm.getSeatList().size()) {
				model.setViewName("redirect:" + "/musteri/biletlerim");				
			}
		}
		return model;
	}
	
	private void populateModelWithCity(List<City> cityList, ModelAndView model) {
		Map<String, String> cityMap = new LinkedHashMap<String, String>();
		for(City city : cityList) {
			cityMap.put(city.getId().toString(), city.getCityName());
		}
		model.addObject("cityMap", cityMap);
	}
	
	private Customer getCustomer() {
		String tcNumber = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			tcNumber = ((UserDetails)principal).getUsername();
		} else {
			tcNumber = principal.toString();
		}
		
		Customer customer = customerService.findByTcNumber(tcNumber);
		return customer;
	}
}
