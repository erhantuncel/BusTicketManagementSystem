package com.erhan.onlineticket.controller.admin;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.onlineticket.model.Expense;
import com.erhan.onlineticket.model.Income;
import com.erhan.onlineticket.model.Route;
import com.erhan.onlineticket.model.Ticket;
import com.erhan.onlineticket.model.Vehicle;
import com.erhan.onlineticket.model.Voyage;
import com.erhan.onlineticket.service.IncomeService;
import com.erhan.onlineticket.service.RouteService;
import com.erhan.onlineticket.service.StopService;
import com.erhan.onlineticket.service.TicketService;
import com.erhan.onlineticket.service.VehicleService;
import com.erhan.onlineticket.service.VoyageService;

@Controller
public class AdminVoyageController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	IncomeService incomeService;
	
	@Autowired
	VoyageService voyageService;
	
	@Autowired
	StopService stopService;
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	RouteService routeService;
	
	@InitBinder("voyageForm")
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		binder.registerCustomEditor(Vehicle.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				if(id == "") {					
					setValue(null);
				} else {
					setValue(vehicleService.findById(new Long(id)));
				}
			}
		});
		
		binder.registerCustomEditor(Route.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				if(id == "") {					
					setValue(null);
				} else {
					setValue(routeService.findById(new Long(id)));
				}
			}
		});
		
		binder.setDisallowedFields("expenseList");
		binder.setDisallowedFields("ticketList");
	}
	
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
	
	@RequestMapping(value = "/admin/sefer/{id}/detay", method = RequestMethod.GET) 
	public ModelAndView voyageDetail(@PathVariable(value = "id") String id) {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Sefer Detayları");
		Voyage voyage = voyageService.findById(new Long(id));
		model.addObject("voyage", voyage);
		Map<String, Date> stopMap = stopService.getStopMapByVoyage(voyage);
		model.addObject("stopMap", stopMap);
		Integer ticketCount = ticketService.countTicketByVoyage(voyage);
		model.addObject("ticketCount", ticketCount);
		Integer reservationCount = ticketService.countReservationByVoyage(voyage);
		model.addObject("reservationCount", reservationCount);
		Income income = incomeService.findByVoyage(voyage);
		if(income != null) {			
			model.addObject("incomeTotal", income.getPrice());
		}
		BigDecimal totalExpense = BigDecimal.ZERO;
		for(Expense expense : voyage.getExpenseList()) {
			totalExpense = totalExpense.add(expense.getPrice());
		}
		model.addObject("totalExpense", totalExpense);
		List<Ticket> ticketList = ticketService.findByVoyage(voyage);
		Set<Ticket> ticketSet = new LinkedHashSet<Ticket>(ticketList);
		model.addObject("ticketSet", ticketSet);
		Ticket[][] ticketArray = ticketService.getTicketArray(ticketSet);
		model.addObject("ticketArray", ticketArray);
		
		GregorianCalendar arrivalTime = getArrivalTime(voyage.getRoute(), voyage.getDepartureTime());
		model.addObject("arrivalTime", arrivalTime.getTime());
		
		model.setViewName("admin/seferDetay");
		return model;
		
	}
	
	@RequestMapping(value = "/admin/seferler", method=RequestMethod.GET)
	public ModelAndView voyages() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Seferler");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		Date startDate = gc.getTime();
		gc.set(Calendar.DAY_OF_MONTH, gc.get(Calendar.DAY_OF_MONTH)+10);
		Date endDate = gc.getTime();
		List<Voyage> voyageListForthComingTenDays = voyageService.findAllBetweenDates(startDate, endDate);
		model.addObject("voyageList", voyageListForthComingTenDays);
		model.setViewName("admin/seferler");
		return model;
	}
	
	@RequestMapping(value = "/admin/seferler", method=RequestMethod.POST)
	public ModelAndView voyagesForDate(@RequestParam(value = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date,
			RedirectAttributes redir) {

		String resultMessage = null;
		ModelAndView model = new ModelAndView();
		if(date != null) {
			model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Seferler");
			List<Voyage> voyageListForDate = voyageService.findAllByDate(date);
			model.addObject("voyageList", voyageListForDate);
			model.setViewName("admin/seferler");			
		} else {
			resultMessage = "Tarih seçmelisiniz.";
			redir.addFlashAttribute("warningType", "danger");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/admin/seferler");
		}
		return model;
	}
	
	@RequestMapping(value="/admin/sefer/{id}/sil", method = RequestMethod.GET)
	public ModelAndView deleteVoyage(@PathVariable(value = "id") String id, HttpServletRequest request, RedirectAttributes redir) {		
		Voyage voyage = voyageService.findById(new Long(id));
		String resultMessage = null;

		List<Ticket> ticketListForVoyage = ticketService.findByVoyage(voyage);
		if(ticketListForVoyage.size() == 0) {
			try {
				voyageService.delete(voyage);			
			} catch (HibernateException e) {
				resultMessage = "" + id + " numaralı sefer iptal edilmedi.";
				redir.addFlashAttribute("warningType", "danger");
			}
			resultMessage = "" + id + " numaralı sefer iptal edildi.";
			redir.addFlashAttribute("warningType", "info");			
		} else {
			resultMessage = "" + id + " numaralı seferde alınmış biletler mevcut.";
			redir.addFlashAttribute("warningType", "danger");
		}

		String referer = request.getHeader("Referer");
		ModelAndView model = new ModelAndView("redirect:" + referer);

		redir.addFlashAttribute("msg", resultMessage);
		
		return model;
	}
	
	@RequestMapping(value = "/admin/seferEkle", method=RequestMethod.GET)
	public ModelAndView showAddVoyageForm() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Sefer Ekleme");
		 
		populateModelWithVehicle(vehicleService.findAll(), model);
		populateModelWithRoute(routeService.findAll(), model);
		
		model.addObject("voyageForm", new Voyage());
		
		model.setViewName("admin/seferEkle");
		return model;
	}
	
	@RequestMapping(value = "/admin/seferEkle", method=RequestMethod.POST)
	public ModelAndView saveVoyage(@ModelAttribute("voyageForm") @Valid Voyage voyage, BindingResult result, 
									ModelAndView model, RedirectAttributes redir) {
		
		if(result.hasErrors()) {
			populateModelWithVehicle(vehicleService.findAll(), model);
			populateModelWithRoute(routeService.findAll(), model);
			model.setViewName("/admin/seferEkle");
		} else {			
			String resultMessage = null;
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date());
			voyage.setRegisterTime(gc.getTime());
			Long id = voyageService.create(voyage);
			
			Voyage createdVoyage = voyageService.findById(id);
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			
			resultMessage = df.format(createdVoyage.getDepartureTime()) + " tarihli sefer oluşturuldu.";
			
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", resultMessage);
			
			model.setViewName("redirect:" + "/admin/seferler");
		}
		
		return model;
	}
	
	private void populateModelWithVehicle(List<Vehicle> vehicleList, ModelAndView model) {
		Map<String, String> vehicleMap = new LinkedHashMap<String, String>();
		for (Vehicle vehicle : vehicleList) {
			vehicleMap.put(vehicle.getId().toString(), vehicle.getPlateCode());
		}
		model.addObject("vehicleMap", vehicleMap);
	}
	
	private void populateModelWithRoute(List<Route> routeList, ModelAndView model) {
		Map<String, String> routeMap = new LinkedHashMap<String, String>();
		for (Route route: routeList) {
			routeMap.put(route.getId().toString(), route.getRouteName());
		}
		model.addObject("routeMap", routeMap);
	}
	
	private GregorianCalendar getArrivalTime(Route route, Date departureTime) {
		String[] distanceAndDurationForRoute = routeService.getTotalDistanceAndDurationForRoute(route, 90, 15, 15);
		String durationStr = distanceAndDurationForRoute[1];
		String[] splitHour = durationStr.split(" sa. ");
		String[] splitMinute = splitHour[1].split(" dk.");
		String hour = splitHour[0];
		String minute = splitMinute[0];
		GregorianCalendar arrivalTime = new GregorianCalendar();
		arrivalTime.setTime(departureTime);
		arrivalTime.add(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
		arrivalTime.add(Calendar.MINUTE, Integer.valueOf(minute));
		return arrivalTime;
	}
}
