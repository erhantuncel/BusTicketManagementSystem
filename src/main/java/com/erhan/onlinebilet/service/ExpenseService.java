package com.erhan.onlinebilet.service;

import java.math.BigDecimal;
import java.util.List;

import com.erhan.onlinebilet.model.Expense;

public interface ExpenseService {
	public Long create(Expense expense);
	public Expense findById(Long id);
	public List<Expense> findAllOrderedByRegisterDate(Integer limit);
	public BigDecimal getTotalForCurrentYear();
}
