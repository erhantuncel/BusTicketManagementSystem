package com.erhan.busticket.service;

import java.util.List;

import com.erhan.busticket.model.ExpenseType;

public interface ExpenseTypeService {
	
	public Long create(ExpenseType expenseType);
	public ExpenseType findById(Long id);
	public List<ExpenseType> findAll();
}
