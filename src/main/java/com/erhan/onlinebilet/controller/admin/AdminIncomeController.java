package com.erhan.onlinebilet.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.erhan.onlinebilet.model.Income;
import com.erhan.onlinebilet.service.IncomeService;

@Controller
public class AdminIncomeController {
	
	@Autowired
	IncomeService incomeService;
	
	@RequestMapping(value="/admin/gelirler", method=RequestMethod.GET)
	public ModelAndView incomes(ModelAndView model) {
		
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Gelirler");
		List<Income> incomeList = incomeService.findAllOrderByDate(50);
		
		model.addObject("incomeList", incomeList);
		model.setViewName("admin/gelirler");
		return model;
	}
	
	@RequestMapping(value="/admin/gelirler", method=RequestMethod.POST)
	public ModelAndView incomesForDate(@RequestParam(value="date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date, ModelAndView model) {
		
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Gelirler");
		List<Income> incomeList = incomeService.findAllByDate(date);
		model.addObject("incomeList", incomeList);
		model.setViewName("admin/gelirler");
		return model;
	}
	
}