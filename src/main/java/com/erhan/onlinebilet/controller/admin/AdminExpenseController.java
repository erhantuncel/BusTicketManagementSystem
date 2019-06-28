package com.erhan.onlinebilet.controller.admin;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.onlinebilet.model.Expense;
import com.erhan.onlinebilet.model.ExpenseType;
import com.erhan.onlinebilet.model.Voyage;
import com.erhan.onlinebilet.service.ExpenseService;
import com.erhan.onlinebilet.service.ExpenseTypeService;
import com.erhan.onlinebilet.service.VoyageService;

@Controller
public class AdminExpenseController {
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	ExpenseTypeService expenseTypeService;
	
	@Autowired
	VoyageService voyageService;

	@InitBinder("expenseForm")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Voyage.class, new PropertyEditorSupport() {
			
			@Override
			public void setAsText(String id) {
				if(id == "") {
					setValue(null);
				} else {
					setValue(voyageService.findById(new Long(id)));
				}
			}
			
			@Override
			public String getAsText() {
				Voyage voyage = (Voyage) this.getValue();
				String voyageId = "";
				if(voyage != null) {
					voyageId = voyage.getId().toString();
				}
				
				return voyageId;
			}
		});
		
		binder.registerCustomEditor(ExpenseType.class, new PropertyEditorSupport() {
			
			@Override
			public void setAsText(String id) {
				if(id == "") {
					setValue(null);
				} else {
					setValue(expenseTypeService.findById(new Long(id)));
				}
			}
		});
	}
	
	@RequestMapping(value="/admin/giderler", method=RequestMethod.GET)
	public ModelAndView showExpenses(ModelAndView model) {
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Giderler");
		
		List<Expense> expenseList = expenseService.findAllOrderedByRegisterDate(50);
		
		model.addObject("expenseList", expenseList);
		model.setViewName("admin/giderler");
		return model;
	}
	
	@RequestMapping(value="/admin/giderler", method=RequestMethod.POST)
	public ModelAndView expensesForDate(@RequestParam(value="date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date, ModelAndView model, 
			RedirectAttributes redir) {
		String resultMessage = null;
		if(date != null) {
			model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Giderler");
			List<Expense> expenseList = expenseService.findAllByDate(date);
			model.addObject("expenseList", expenseList);
			model.setViewName("admin/giderler");			
		} else {
			resultMessage = "Tarih seçmelisiniz.";
			redir.addFlashAttribute("warningType", "danger");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/admin/giderler");
		}
		return model;
	}
	
	@RequestMapping(value="/admin/gider/{id}/sil", method=RequestMethod.GET)
	public ModelAndView deleteExpense(@PathVariable(value="id") String id, HttpServletRequest request, RedirectAttributes redir, ModelAndView model) {
		String resultMessage = null;
		try {
			expenseService.delete(new Long(id));
		} catch (HibernateException e) {
			resultMessage = "" + id + " numaralı gider iptal edilmedi.";
			redir.addFlashAttribute("warningType", "danger");
		}
		
		resultMessage = "" + id + " numaralı gider iptal edildi.";
		redir.addFlashAttribute("warningType", "info");

		String referer = request.getHeader("Referer");
		model.setViewName("redirect:" + referer);

		redir.addFlashAttribute("msg", resultMessage);
		
		return model;
	}
	
	@RequestMapping(value="/admin/giderEkle", method=RequestMethod.GET)
	public ModelAndView expenseFormForAdd(ModelAndView model) {
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Gider Ekle");
		
		List<ExpenseType> expenseTypeList = expenseTypeService.findAll();
		populateModelWithExpenseType(expenseTypeList, model);
		Expense expenseForm = new Expense();
		model.addObject("expenseForm", expenseForm);
		model.setViewName("admin/giderFormu");
		return model;
	}
	
	@RequestMapping(value="/admin/giderEkle/sefer/{voyageId}", method=RequestMethod.GET)
	public ModelAndView expenseFormForAdd(@PathVariable(value="voyageId") String voyageId, ModelAndView model) {
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Gider Ekle");
		
		List<ExpenseType> expenseTypeList = expenseTypeService.findAll();
		populateModelWithExpenseType(expenseTypeList, model);
		Expense expenseForm = new Expense();
		expenseForm.setVoyage(voyageService.findById(new Long(voyageId)));
		model.addObject("expenseForm", expenseForm);
		model.setViewName("admin/giderFormu");
		return model;
	}
	
	@RequestMapping(value="/admin/giderEkle", method=RequestMethod.POST)
	public ModelAndView addExpense(@ModelAttribute("expenseForm") @Valid Expense expense, BindingResult result, 
									RedirectAttributes redir, ModelAndView model) {
		
		String resultMessage = null;
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error.toString());
			}
			List<ExpenseType> expenseTypeList = expenseTypeService.findAll();
			populateModelWithExpenseType(expenseTypeList, model);
			model.setViewName("/admin/giderFormu");
		} else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date());
			expense.setRegisteredTime(gc.getTime());
			Long id = expenseService.create(expense);
			Expense createdExpense = expenseService.findById(id);
			resultMessage = createdExpense.getType().getName() + " gideri eklendi.";
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/admin/sefer/" + createdExpense.getVoyage().getId() + "/detay");
		}
		
		return model;
	}
	
	@RequestMapping(value="/admin/gider/{id}/guncelle", method=RequestMethod.GET)
	public ModelAndView expenseFormForUpdate(@PathVariable(value="id") String id, ModelAndView model) {
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Gider Guncelle");
		
		List<ExpenseType> expenseTypeList = expenseTypeService.findAll();
		populateModelWithExpenseType(expenseTypeList, model);
		Expense expenseForm = expenseService.findById(new Long(id));
		model.addObject("expenseForm", expenseForm);
		
		model.setViewName("admin/giderFormu");
		return model;
	}
	
	@RequestMapping(value="/admin/gider/{id}/guncelle", method=RequestMethod.POST)
	public ModelAndView updateExpense(@PathVariable(value="id") String id, @ModelAttribute("expenseForm") @Valid Expense expense, 
								BindingResult result, RedirectAttributes redir, ModelAndView model) {
		
		String resultMessage = null;
		if(result.hasErrors()) {
			List<ExpenseType> expenseTypeList = expenseTypeService.findAll();
			populateModelWithExpenseType(expenseTypeList, model);
			model.addObject("expenseForm", expense);
			model.setViewName("admin/giderFormu");
		} else {
			expenseService.update(expense);
			resultMessage = "Gider güncellendi.";
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/admin/giderler"); 
		}
		
		return model;
	}
	
	private void populateModelWithExpenseType(List<ExpenseType> expenseTypeList, ModelAndView model) {
		Map<String, String> expenseTypeMap = new LinkedHashMap<String, String>();
		for(ExpenseType type : expenseTypeList) {
			expenseTypeMap.put(type.getId().toString(), type.getName());
		}
		model.addObject("expenseTypeMap", expenseTypeMap);
	}
}
