package com.erhan.busticket.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.erhan.busticket.model.Expense;

public interface ExpenseService {
	public Long create(Expense expense);
	public Expense findById(Long id);
	public List<Expense> findAllOrderedByRegisterDate(Integer limit);
	public List<Expense> findAllByDate(Date date);
	public void update(Expense expense);
	public int delete(Long id);
	public BigDecimal getTotalForCurrentYear();
}
