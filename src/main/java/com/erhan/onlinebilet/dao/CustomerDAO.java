package com.erhan.onlinebilet.dao;


import java.util.List;

import com.erhan.onlinebilet.model.Customer;

public interface CustomerDAO {
	public Long create(Customer customer);
	public Customer findById(Long id);
	public List<Customer> findAll();
	public Customer findByTcNumber(String tcNumber);
	public Integer countAll();
	public List<Object[]> countMonthly(Integer year);
	public List<Customer> findAllSortedByLastRegistered();
	public void update(Customer customer);
	public void delete(Customer customer);
}
