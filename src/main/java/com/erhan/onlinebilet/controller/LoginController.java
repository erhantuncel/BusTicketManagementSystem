package com.erhan.onlinebilet.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "public/index";
	}
	
	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public View commonWelcomePage(HttpServletRequest request) {
		Set<String> roles = AuthorityUtils.authorityListToSet(
				SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		if(roles.contains("ROLE_ADMIN")) {
			return new RedirectView("admin");
		} else {
			return new RedirectView("user/biletlerim");
		}
	}
}