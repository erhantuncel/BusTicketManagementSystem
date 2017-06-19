package com.erhan.onlinebilet.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
	
	@RequestMapping(value="/musteri", method=RequestMethod.GET)
	public ModelAndView home(ModelAndView model) {
		
		model.setViewName("redirect:" + "/musteri/biletlerim");
		return model;
	}
	
}
