package com.erhan.busticket.controller.customer;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.busticket.model.Customer;
import com.erhan.busticket.model.Gender;
import com.erhan.busticket.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@InitBinder("customerForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("enabled");
		binder.setDisallowedFields("dateOfRegister");
		binder.setDisallowedFields("timeOfLastOnline");
	}
	
	@RequestMapping(value="/musteri", method=RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, ModelAndView model) {
		
		// Customer
		request.getSession().setAttribute("customer", getCustomer());
		
		model.setViewName("redirect:" + "/musteri/biletlerim");
		return model;
	}
	
	@RequestMapping(value="/musteriKayit", method=RequestMethod.GET)
	public ModelAndView customerForForRegister(ModelAndView model) {
		
		model.addObject("customerForm", new Customer());
		model.addObject("genderValues", Gender.values());
		model.setViewName("musteri/musteriKayit");
		return model;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/musteriKayit", method=RequestMethod.POST)
	public ModelAndView addCustomer(@ModelAttribute("customerForm") @Valid Customer customer, BindingResult result, 
						RedirectAttributes redir, ModelAndView model) {
		String resultMessage = null;
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error.toString());
			}
			model.addObject("genderValues", Gender.values());
			model.setViewName("musteri/musteriKayit");
		} else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date());
			customer.setDateOfRegister(gc.getTime());
			customer.setTimeOfLastOnline(gc.getTime());
			customer.setEnabled(true);
			Long id = customerService.create(customer);
			resultMessage = "Kaydınız tamamlandı. Giriş yapabilirsiniz.";
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/");
		}
		return model;
	}
	
	@RequestMapping(value="/musteri/bilgiGuncelle", method=RequestMethod.GET)
	public ModelAndView showUpdateCustomerForm(ModelAndView model) {
		model.addObject("updateCustomerForm", getCustomer());
		model.addObject("genderValues", Gender.values());
		model.setViewName("musteri/bilgiGuncelle");
		return model;
	}
	
	@RequestMapping(value="/musteri/bilgiGuncelle", method=RequestMethod.POST)
	public ModelAndView updateCustomer(@ModelAttribute("updateCustomerForm") @Valid Customer customer, BindingResult result,
						RedirectAttributes redir, ModelAndView model) {
		String resultMessage = null;
		if(result.hasErrors()) {
			model.addObject("updateCustomerForm", customer);
			model.addObject("genderValues", Gender.values());
			model.setViewName("musteri/bilgiGuncelle");
		} else {
			customerService.update(customer);
			resultMessage = "Bilgileriniz güncellendi!";
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/musteri/biletlerim");
		}
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
