package com.erhan.busticket.controller.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.erhan.busticket.model.Customer;
import com.erhan.busticket.model.Voyage;
import com.erhan.busticket.service.CustomerService;
import com.erhan.busticket.service.ExpenseService;
import com.erhan.busticket.service.IncomeService;
import com.erhan.busticket.service.TicketService;
import com.erhan.busticket.service.VoyageService;

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
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli");
		
		// Customer - Admin
		request.getSession().setAttribute("admin", getCustomer());
		
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