package com.erhan.onlineticket.service;

import java.util.List;

import com.erhan.onlineticket.model.ExpenseType;

public interface ExpenseTypeService {
	
	public Long create(ExpenseType expenseType);
	public ExpenseType findById(Long id);
	public List<ExpenseType> findAll();
}
