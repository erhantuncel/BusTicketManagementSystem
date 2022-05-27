package com.erhan.onlineticket.controller.admin;

import java.beans.PropertyEditorSupport;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.onlineticket.model.Vehicle;
import com.erhan.onlineticket.model.VehicleBrand;
import com.erhan.onlineticket.model.VehicleModel;
import com.erhan.onlineticket.service.VehicleBrandService;
import com.erhan.onlineticket.service.VehicleModelService;
import com.erhan.onlineticket.service.VehicleService;

@Controller
public class AdminVehicleController {

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	VehicleBrandService vehicleBrandService;
	
	@Autowired
	VehicleModelService vehicleModelService;
	
	@InitBinder("addVehicleForm")
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(VehicleModel.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				if(id == "") {
					setValue(null);
				} else {
					setValue(vehicleModelService.findById(new Long(id)));
				}
			}
		});
	}
	
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
	
	@RequestMapping(value="/admin/aracEkle", method=RequestMethod.GET)
	public ModelAndView showCreateVehicleForm(ModelAndView model) {
		
		model.addObject("title", "Online Bilet Sistemi | Yönetim Paneli - Araçlar Ekle");
		
		populateModelWithVehicleBrand(vehicleBrandService.findAll(), model);
		
		
		model.addObject("addVehicleForm", new Vehicle());
		model.setViewName("admin/aracEkle");
		return model;
	}
	
	@RequestMapping(value="/admin/aracEkle", method=RequestMethod.POST)
	public ModelAndView createVehicle(@ModelAttribute("addVehicleForm") @Valid Vehicle vehicle, BindingResult result,
						ModelAndView model, RedirectAttributes redir) {
		String resultMessage = null;
		if(result.hasErrors()) {
			populateModelWithVehicleBrand(vehicleBrandService.findAll(), model);
			model.setViewName("admin/aracEkle");
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error.toString());
			}
//			System.out.println(result.getAllErrors());
		} else {
			Long id = vehicleService.create(vehicle);
			
			Vehicle createdVehicle = vehicleService.findById(id);
			resultMessage = createdVehicle.getPlateCode() + " plakalı araç oluşturuldu.";
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", resultMessage);
			model.setViewName("redirect:" + "/admin/araclar");
		}
		return model;
	}
	
	private void populateModelWithVehicleBrand(List<VehicleBrand> vehicleBrandList, ModelAndView model) {
		Map<String, String> vehicleBrandMap = new LinkedHashMap<String, String>();
		for (VehicleBrand vehicleBrand : vehicleBrandList) {
			vehicleBrandMap.put(vehicleBrand.getId().toString(), vehicleBrand.getName());
		}
		model.addObject("vehicleBrandMap", vehicleBrandMap);
	}
}
