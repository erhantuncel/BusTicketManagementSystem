package com.erhan.onlinebilet;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Stop;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.Voyage;
import com.erhan.onlinebilet.service.CustomerService;
import com.erhan.onlinebilet.service.ExpenseService;
import com.erhan.onlinebilet.service.IncomeService;
import com.erhan.onlinebilet.service.StopService;
import com.erhan.onlinebilet.service.TicketService;
import com.erhan.onlinebilet.service.VoyageService;

@Controller
public class AdminController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	IncomeService incomeService;
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	VoyageService voyageService;
	
	@Autowired
	StopService stopService;
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli");
		
		// Customer - Admin
		request.getSession().setAttribute("customer", getCustomer());
		
		// Ticket Count
		Integer ticketCount = ticketService.countAll();
		model.addObject("ticketCount", ticketCount);
		
		// Customer count
		Integer customerCount = customerService.countAll();
		model.addObject("customerCount", customerCount);
		
		// Income Annual
		BigDecimal incomeAnnual = incomeService.getTotalForCurrentYear();
		model.addObject("incomeAnnual", incomeAnnual);
		
		// Expense Annual
		BigDecimal expenseAnnual = expenseService.getTotalForCurrentYear();
		model.addObject("expenseAnnual", expenseAnnual);
		
		Map<Voyage, String> voyageMapStartedFiveHour = voyageService.findAllStartedInHourWithCompletionPercentage(5);
		model.addObject("voyageMapStartedFiveHour", voyageMapStartedFiveHour);
		
		List<Voyage> voyagesNextTime = voyageService.findAllSortedByDepartureTimeForRecords(10);
		model.addObject("voyagesNextTime", voyagesNextTime);
		
		model.setViewName("admin/index");
		return model;
		
		
	}
	
//	@RequestMapping(value = "/admin/{page}", method = RequestMethod.GET)
//	public ModelAndView seferler(@PathVariable(value="page") String page) {
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli");
//		model.addObject("customer", getCustomer());
//		model.setViewName("admin/" + page);
//		return model;
//	}
	
	@RequestMapping(value = "/admin/aktifSeferler", method = RequestMethod.GET)
	public ModelAndView activeVoyages() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Aktif Seferler");
		Map<Voyage, String> voyageMapStartedFiveHour = voyageService.findAllStartedInHourWithCompletionPercentage(5);
		model.addObject("voyageMapStartedFiveHour", voyageMapStartedFiveHour);
		model.setViewName("admin/aktifSeferler");
		return model;
	}
	
	@RequestMapping(value = "/admin/yaklasanSeferler", method = RequestMethod.GET)
	public ModelAndView upcomingVoyages() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Yaklaşan Seferler");
		List<Voyage> upComingvoyages = voyageService.findAllSortedByDepartureTimeForRecords(10);
		model.addObject("upComingvoyages", upComingvoyages);
		model.setViewName("admin/yaklasanSeferler");
		return model;
	}
	
	@RequestMapping(value = "/admin/seferDetay/{id}", method = RequestMethod.GET) 
	public ModelAndView seferDetail(@PathVariable(value = "id") String id) {
		ModelAndView model = new ModelAndView();
		Voyage voyage = voyageService.findById(new Long(id));
		model.addObject("voyage", voyage);
		Map<String, Date> stopMap = stopService.getStopMapByVoyage(voyage);
		model.addObject("stopMap", stopMap);
		Integer ticketCount = ticketService.countTicketByVoyage(voyage);
		model.addObject("ticketCount", ticketCount);
		Integer reservationCount = ticketService.countReservationByVoyage(voyage);
		model.addObject("reservationCount", reservationCount);
		BigDecimal incomeTotal = incomeService.getTotalForVoyage(voyage);
		model.addObject("incomeTotal", incomeTotal);
		List<Ticket> ticketList = ticketService.findByVoyage(voyage);
		Set<Ticket> ticketSet = new LinkedHashSet<Ticket>(ticketList);
		model.addObject("ticketSet", ticketSet);
		Ticket[][] ticketArray = ticketService.getTicketArray(ticketSet);
		model.addObject("ticketArray", ticketArray);
		
		
		model.setViewName("admin/seferDetay");
		return model;
		
	}
	
	@RequestMapping(value="/admin/bilet/{id}/sil", method = RequestMethod.GET)
	public ModelAndView deleteTicket(@PathVariable(value = "id") String id, HttpServletRequest request) {
		int result = ticketService.delete(new Long(id));
		String referer = request.getHeader("Referer");
		ModelAndView model = new ModelAndView("redirect:" + referer); 
		String resultMessage = null;
		if(result > 0) {			
			resultMessage = "" + id + " numaralı bilet silindi.";
			model.addObject("statusCss", "success");
		} else {
			resultMessage = "" + id + " numaralı bilet silinemedi.";
			model.addObject("statusCss", "danger");
		}
		model.addObject("msg", resultMessage);
		return model;
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