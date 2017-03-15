package com.erhan.onlinebilet.service;

import java.math.BigDecimal;

import com.erhan.onlinebilet.model.Expense;

public interface ExpenseService {
	public Long create(Expense expense);
	public Expense findById(Long id);
	public BigDecimal getTotalForCurrentYear();
}
