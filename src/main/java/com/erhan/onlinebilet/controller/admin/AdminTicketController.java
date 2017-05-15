package com.erhan.onlinebilet.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.onlinebilet.service.TicketService;

@Controller
public class AdminTicketController {
	
	@Autowired
	TicketService ticketService;

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
}
