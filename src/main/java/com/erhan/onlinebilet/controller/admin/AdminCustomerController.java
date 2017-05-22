package com.erhan.onlinebilet.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.service.CustomerService;

@Controller
public class AdminCustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/admin/musteriler", method=RequestMethod.GET)
	public ModelAndView customers(ModelAndView model) {
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Müşteriler");
		
		List<Customer> customerList = customerService.findAllSortedByLastRegistered(20);
		
		model.addObject("customerList", customerList);
		model.setViewName("admin/musteriler");
		return model;
	}
	
	@RequestMapping(value="/admin/musteriAra", method=RequestMethod.POST)
	public ModelAndView searchCustomerByTcNumber(@RequestParam(value="tcNumber") String tcNumber, ModelAndView model, RedirectAttributes redir) {
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Müşteriler");
		List<Customer> customerList = new ArrayList<Customer>();
		
		String redirMessage = null;
		if(tcNumber == null) {
			redirMessage = "Lütfen 11 harften oluşan T.C numarasını giriniz.";
			redir.addFlashAttribute("warningType", "danger");
			redir.addFlashAttribute("msg", redirMessage);
			model.setViewName("redirect:" + "/admin/musteriler");
		} else if(tcNumber.length() != 11){			
			redirMessage = "T.C numarası 11 karakter olmalıdır.";
			redir.addFlashAttribute("warningType", "danger");
			redir.addFlashAttribute("msg", redirMessage);
			model.setViewName("redirect:" + "/admin/musteriler");
		} else {
			Customer customer = customerService.findByTcNumber(tcNumber, "ROLE_USER");
			if(customer == null) {
				redirMessage = "Müşteri bulunamadı.";
				redir.addFlashAttribute("warningType", "warning");
				redir.addFlashAttribute("msg", redirMessage);
				model.setViewName("redirect:" + "/admin/musteriler");
			} else {
				customerList.add(customer);
				model.addObject("customerList", customerList);
				model.setViewName("admin/musteriler");				
			}
		}		
		return model;
	}
	
	@RequestMapping(value="/admin/musteri/{id}/detay", method=RequestMethod.GET)
	public ModelAndView customerDetails(@PathVariable(value="id") String id, ModelAndView model) {
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Müşteri Detayları");
		
		Customer customer = customerService.findById(new Long(id));
		List<Ticket> ticketList = customer.getTicketList(); 
		
		model.addObject("customerForDetails", customer);
		if(ticketList.size() != 0) {			
			model.addObject("ticketListForCustomerDetails", ticketList);
		}
		
		model.setViewName("admin/musteriDetay");
		return model;
	}
	
}
