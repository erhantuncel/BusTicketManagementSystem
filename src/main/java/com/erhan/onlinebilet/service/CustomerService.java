package com.erhan.onlinebilet.service;

import java.util.List;

import com.erhan.onlinebilet.model.Customer;

public interface CustomerService {
	public Long create(Customer customer);
	public Customer findById(Long id);
	public List<Customer> findAll();
	public Customer findByTcNumber(String tcNumber);
	public Customer findByTcNumber(String tcNumber, String userRole);
	public Integer countAll();
	public List<String[]> countMonthly(Integer year);
	public List<Customer> findAllSortedByLastRegistered();
	public List<Customer> findAllSortedByLastRegistered(Integer count);
	public void update(Customer customer);
	public void delete(Customer customer);
}
