package com.erhan.onlinebilet;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.model.Gender;
import com.erhan.onlinebilet.model.UserRole;
import com.erhan.onlinebilet.service.CustomerService;
import com.erhan.onlinebilet.service.UserRoleService;

@Controller
public class UserController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserRoleService userRoleService;
	
	
	@RequestMapping(value = "/loadTestUsers", method=RequestMethod.GET)
	public String loadUsers() {
		
		UserRole roleAdmin = new UserRole();
		roleAdmin.setRole("ROLE_ADMIN");
		
		UserRole roleUser = new UserRole();
		roleUser.setRole("ROLE_USER");
		
		Customer user = new Customer();
		user.setGender(Gender.ERKEK);
		user.setTcNumber("12345678910");
		user.setName("Serhan");
		user.setSurname("TUNÇEL");
		GregorianCalendar dob = new GregorianCalendar();
		dob.set(1994, 1, 13);		
		user.setDateOfBirth(dob.getTime());
		user.setMobileNumber("5056983244");
		user.seteMail("erhan@abc.com");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode("serhan94"));
		GregorianCalendar dor = new GregorianCalendar();
		dor.set(2016, 8, 30, 15, 24, 16);
		user.setDateOfRegister(dor.getTime());
		GregorianCalendar lastOnlineTime = new GregorianCalendar();
		lastOnlineTime.set(2016, 10, 1, 10, 15, 07);
		user.setTimeOfLastOnline(lastOnlineTime.getTime());
		user.setEnabled(true);
//		user.getUserRole().add(roleUser);
		roleUser.setUser(user);
		userRoleService.create(roleUser);
//		customerService.create(user);
		
		Customer admin = new Customer();
		admin.setGender(Gender.ERKEK);
		admin.setTcNumber("10987654321");
		admin.setName("Erhan");
		admin.setSurname("TUNÇEL");	
		dob.set(1994, 1, 13);		
		admin.setDateOfBirth(dob.getTime());
		admin.setMobileNumber("5056983244");
		admin.seteMail("erhan@abc.com");
		admin.setPassword(passwordEncoder.encode("admin"));
		dor.set(2016, 9, 5, 18, 11, 51);
		admin.setDateOfRegister(dor.getTime());
		lastOnlineTime.set(2016, 10, 7, 20, 05, 27);
		admin.setTimeOfLastOnline(lastOnlineTime.getTime());
		admin.setEnabled(true);
		admin.getUserRole().add(roleAdmin);
		roleAdmin.setUser(admin);
		customerService.create(admin);
		
		
		return "public/index";
	}
}
