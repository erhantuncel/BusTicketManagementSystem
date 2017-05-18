package com.erhan.onlinebilet.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.onlinebilet.model.Vehicle;
import com.erhan.onlinebilet.service.VehicleService;

@Controller
public class AdminVehicleController {

	@Autowired
	VehicleService vehicleService;
	
	@RequestMapping(value = "/admin/araclar", method=RequestMethod.GET)
	public ModelAndView vehicles() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Araçlar");
		
		List<Vehicle> vehicleList = vehicleService.findAll();
		model.addObject("vehicleList", vehicleList);
		
		Vehicle vehicle = new Vehicle();
		model.addObject("updateMilageForm", vehicle);
		
		model.setViewName("admin/araclar");
		return model;
	}
	
	@RequestMapping(value="/admin/arac/{id}/guncelle", method=RequestMethod.POST)
	public ModelAndView updateMilage(@ModelAttribute("updateVehicleForm") Vehicle vehicle, @PathVariable(value = "id") String id,
															BindingResult result, ModelAndView model, RedirectAttributes redir) {
		String resultMessage = null;
		if(result.hasErrors()) {
			resultMessage = "Araç güncelleme başarısız!";
			redir.addFlashAttribute("warningType", "danger");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/admin/araclar");
		} else {
			Vehicle vehicleFromDb = vehicleService.findById(new Long(id));
			vehicleFromDb.setMilage(vehicle.getMilage());
			vehicleService.update(vehicleFromDb);
			resultMessage = vehicleFromDb.getPlateCode() + " plakalı araç güncellendi!";
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/admin/araclar");
		}
		return model;
	}
}
