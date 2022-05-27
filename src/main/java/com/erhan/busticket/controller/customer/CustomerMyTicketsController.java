package com.erhan.busticket.controller.customer;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.busticket.model.Customer;
import com.erhan.busticket.model.Income;
import com.erhan.busticket.model.Ticket;
import com.erhan.busticket.service.CustomerService;
import com.erhan.busticket.service.IncomeService;
import com.erhan.busticket.service.TicketService;

@Controller
public class CustomerMyTicketsController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	IncomeService incomeService;
	
	
	@RequestMapping(value="/musteri/biletlerim", method=RequestMethod.GET)
	public ModelAndView myTickets(ModelAndView model) {
		
		Customer customer = getCustomer();
//		List<Ticket> ticketListForCustomer = ticketService.findByCustomer(customer);
		List<Ticket> ticketListForCustomer = ticketService.findForFutureByCustomer(customer);
		
		model.addObject("title", "Online Bilet Sistemi | Biletlerim");
		model.addObject("ticketLists", ticketListForCustomer);
		model.setViewName("musteri/biletlerim");
		return model;
	}
	
	@RequestMapping(value="/musteri/bilet/{id}/sil", method=RequestMethod.GET)
	public ModelAndView deleteTicket(@PathVariable(value="id") String id, ModelAndView model, HttpServletRequest request, RedirectAttributes redir) {
		
		Ticket ticket = ticketService.findById(new Long(id));
		int result = ticketService.delete(new Long(id));
		String referer = request.getHeader("Referer");
		model.setViewName("redirect:" + referer); 
		String resultMessage = null;
		if(result > 0) {
			Income voyageIncome = incomeService.findByVoyage(ticket.getVoyage());
			BigDecimal newVoyageIncomePrice = voyageIncome.getPrice().subtract(ticket.getPrice());
			voyageIncome.setPrice(newVoyageIncomePrice);
			incomeService.update(voyageIncome);
			resultMessage = "" + id + " numaralı bilet iptal edildi.";
			redir.addFlashAttribute("warningType", "info");
		} else {
			resultMessage = "" + id + " numaralı bilet iptal edilmedi.";
			redir.addFlashAttribute("warningType", "danger");
		}
		redir.addFlashAttribute("msg", resultMessage);
		return model;
	}
	
	@RequestMapping(value="/musteri/bileteCevir/{id}", method=RequestMethod.GET)
	public ModelAndView convertToTicket(@PathVariable(value="id") String id, 
				ModelAndView model, HttpServletRequest request, RedirectAttributes redir) {
		
		Ticket ticket = ticketService.findById(new Long(id));
		BigDecimal ticketPrice = ticketService.calculateTicketPriceByDepartureAndArrival(ticket.getDeparture(), ticket.getArrival());
		ticket.setPrice(ticketPrice);
		ticket.setIsReservation(false);
		String resultMessage = null;
		try {
			ticketService.update(ticket);			
		} catch (HibernateException e) {
			resultMessage = "İşlem başarısız!";
			redir.addFlashAttribute("warningType", "danger");
		}
		resultMessage = "İşlem başarılı.";
		redir.addFlashAttribute("warningType", "info");
		
		String referer = request.getHeader("Referer");
		model.setViewName("redirect:" + referer);
		redir.addFlashAttribute("msg", resultMessage);
		
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
