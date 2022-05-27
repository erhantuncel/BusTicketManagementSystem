package com.erhan.busticket.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.dao.CustomerDAO;
import com.erhan.busticket.model.Customer;
import com.erhan.busticket.model.UserRole;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService { 
	
	@Autowired
	private CustomerDAO customerDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(final String tcNumber) throws UsernameNotFoundException {
		Customer customer = customerDao.findByTcNumber(tcNumber);
		List<GrantedAuthority> authorities = buildUserAuthority(customer.getUserRole());
		
		return buildUserForAuthentication(customer, authorities);
	}
	
	private User buildUserForAuthentication(Customer customer, List<GrantedAuthority> authorities) {
		return new User(customer.getTcNumber(), 
					customer.getPassword(), 
					customer.getEnabled(), 
					true, 
					true, 
					true, 
					authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		
		// Build user's authorities
		for(UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		
		return result;
	}
	
}
