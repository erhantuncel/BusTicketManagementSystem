package com.erhan.onlinebilet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.CustomerDAO;
import com.erhan.onlinebilet.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO customerDAO;

	@Override
	@Transactional
	public Long create(Customer customer) {
		Long id = customerDAO.create(customer);
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
	public void update(Customer customer) {
		customerDAO.update(customer);
	}

	@Override
	@Transactional
	public void delete(Customer customer) {
		customerDAO.delete(customer);
	}
}
