package com.erhan.onlinebilet;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.erhan.onlinebilet.model.City;
import com.erhan.onlinebilet.model.CityDistance;
import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Route;
import com.erhan.onlinebilet.model.Stop;
import com.erhan.onlinebilet.model.Ticket;
import com.erhan.onlinebilet.model.Vehicle;
import com.erhan.onlinebilet.model.Voyage;
import com.erhan.onlinebilet.service.CityDistanceService;
import com.erhan.onlinebilet.service.CityService;
import com.erhan.onlinebilet.service.CustomerService;
import com.erhan.onlinebilet.service.ExpenseService;
import com.erhan.onlinebilet.service.IncomeService;
import com.erhan.onlinebilet.service.RouteService;
import com.erhan.onlinebilet.service.StopService;
import com.erhan.onlinebilet.service.TicketService;
import com.erhan.onlinebilet.service.VehicleService;
import com.erhan.onlinebilet.service.VoyageService;
import com.erhan.onlinebilet.web.model.AddRouteForm;

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
	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	RouteService routeService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CityDistanceService cityDistanceService;
	
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
	public ModelAndView deleteTicket(@PathVariable(value = "id") String id, HttpServletRequest request, RedirectAttributes redir) {
		int result = ticketService.delete(new Long(id));
		String referer = request.getHeader("Referer");
		ModelAndView model = new ModelAndView("redirect:" + referer); 
		String resultMessage = null;
		if(result > 0) {			
			resultMessage = "" + id + " numaralı bilet iptal edildi.";
			redir.addFlashAttribute("warningType", "info");
		} else {
			resultMessage = "" + id + " numaralı bilet iptal edilmedi.";
			redir.addFlashAttribute("warningType", "danger");
		}
		redir.addFlashAttribute("msg", resultMessage);
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
	public ModelAndView voyagesForDate(@RequestParam(value = "date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Seferler");
		List<Voyage> voyageListForDate = voyageService.findAllByDate(date);
		model.addObject("voyageList", voyageListForDate);
		model.setViewName("admin/seferler");
		return model;
	}
	
	@RequestMapping(value="/admin/sefer/{id}/sil", method = RequestMethod.GET)
	public ModelAndView deleteVoyage(@PathVariable(value = "id") String id, HttpServletRequest request, RedirectAttributes redir) {		
		Voyage voyage = voyageService.findById(new Long(id));
		String resultMessage = null;

		try {
			voyageService.delete(voyage);			
		} catch (HibernateException e) {
			resultMessage = "" + id + " numaralı sefer iptal edilmedi.";
			redir.addFlashAttribute("warningType", "danger");
		}
		resultMessage = "" + id + " numaralı sefer iptal edildi.";
		redir.addFlashAttribute("warningType", "info");

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
	
	@RequestMapping(value = "/admin/guzergahEkle", method=RequestMethod.GET)
	public ModelAndView showAddRouteForm() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Güzergah Ekleme");
		populateModelWithCity(cityService.findAll(), model);
		
		model.addObject("addRouteForm", new AddRouteForm());
		model.setViewName("admin/guzergahEkle");
		return model;
	}
	
	@RequestMapping(value = "/admin/guzergahEkle", method=RequestMethod.POST)
	public ModelAndView saveRouteAndStops(@ModelAttribute("addRouteForm") @Valid AddRouteForm addRouteForm, BindingResult result,
											ModelAndView model, RedirectAttributes redir) {
		List<String> stopPlateCodes = addRouteForm.getStopList();
		if(result.hasErrors()) {
			populateModelWithCity(cityService.findAll(), model);
			model.setViewName("admin/guzergahEkle");
		} else {
			Route route = new Route();
			route.setRouteName(addRouteForm.getRouteName());
			City departure = null;
			Stop stop = null;
			Integer lastDuration = 0;
			for (int i=0; i<stopPlateCodes.size(); i++) {
				if(i==0) {
					departure = cityService.findById(new Long(stopPlateCodes.get(i)));
					stop = new Stop(route, departure, 0, 0);
				} else {
					City arrival = cityService.findById(new Long(stopPlateCodes.get(i)));
					
					Integer averageSpeed = 90;
					Integer timePeriodInMinutes = 15;
					Integer extraTime = 15;
					CityDistance distance = cityDistanceService.findByDepartureAndArrival(departure, arrival);
					Integer calculatedDurationMin = (int) Math.ceil((distance.getDistance().doubleValue() / averageSpeed.doubleValue())*60);
					Integer modDuration15 = calculatedDurationMin % timePeriodInMinutes;
					Integer duration = calculatedDurationMin + (timePeriodInMinutes - modDuration15) + extraTime;
					lastDuration = lastDuration + duration;
					stop = new Stop(route, arrival, lastDuration, 0);
					departure = arrival;
				}
				stopService.create(stop);
				route.getStops().add(stop);
				routeService.create(route);
			}
			
//			System.out.println("Route Name = " + addRouteForm.getRouteName());
//			for (int i=0; i<stopPlateCodes.size(); i++) {
//				System.out.println("i = " + i + " Stop = " + stopPlateCodes.get(i));
//			}
		}
		
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Güzergah Ekleme");
		populateModelWithCity(cityService.findAll(), model);
		model.setViewName("admin/guzergahlar");
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
	
	private void populateModelWithCity(List<City> cityList, ModelAndView model) {
		Map<String, String> cityMap = new LinkedHashMap<String, String>();
		for(City city : cityList) {
			cityMap.put(city.getId().toString(), city.getCityName());
		}
		model.addObject("cityMap", cityMap);
	}
	
	
}