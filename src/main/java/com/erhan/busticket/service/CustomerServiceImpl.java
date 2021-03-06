package com.erhan.busticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.dao.CustomerDAO;
import com.erhan.busticket.model.Customer;
import com.erhan.busticket.model.UserRole;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	@Transactional
	public Long create(Customer customer) {
		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
		Long id = customerDAO.create(customer);
		UserRole userRole = new UserRole("ROLE_USER");
		userRole.setUser(customer);
		userRoleService.create(userRole);
		return id;
	}

	@Override
	@Transactional
	public Customer findById(Long id) {
		Customer customer = customerDAO.findById(id);
		return customer;
	}

	@Override
	@Transactional
	public List<Customer> findAll() {
		List<Customer> customerList = customerDAO.findAll();
		return customerList;
	}

	@Override
	@Transactional
	public Customer findByTcNumber(String tcNumber) {
		Customer customer = customerDAO.findByTcNumber(tcNumber);
		return customer;
	}

	@Override
	@Transactional
	public Customer findByTcNumber(String tcNumber, String userRole) {
		Customer customer = customerDAO.findByTcNumber(tcNumber, userRole);
		return customer;
	}

	@Override
	@Transactional
	public Integer countAll() {
		Integer count = customerDAO.countAll();
		return count;
	}

	@Override
	@Transactional
	public List<String[]> countMonthly(Integer year) {
		List<Object[]> customerCountMonthly = customerDAO.countMonthly(year);
		List<String[]> monthlyCountList = new ArrayList<String[]>();
		for(int i=1; i<=12; i++) {
			String countForMonth[] = new String[2];
			countForMonth[0] = String.valueOf(i);
			for(Object[] countOfCustomer : customerCountMonthly) {
				if(i == Integer.parseInt(countOfCustomer[0].toString())) {
					countForMonth[1] = countOfCustomer[1].toString();
					break;
				}
			}
			if(countForMonth[1] == null) {
				countForMonth[1] = "0";
			}
			monthlyCountList.add(countForMonth);
		}
		return monthlyCountList;
	}

	@Override
	@Transactional
	public List<Customer> findAllSortedByLastRegistered() {
		List<Customer> customerList = customerDAO.findAllSortedByLastRegistered();
		return customerList;
	}

	@Override
	@Transactional
	public List<Customer> findAllSortedByLastRegistered(Integer count) {
		List<Customer> customerList = customerDAO.findAllSortedByLastRegistered(count);
		return customerList;
	}

	@Override
	@Transactional
	public void update(Customer customer) {
		customerDAO.update(customer);
	}

	@Override
	@Transactional
	public void delete(Customer customer) {
		customerDAO.delete(customer);
	}
}
