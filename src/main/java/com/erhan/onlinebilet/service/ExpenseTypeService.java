package com.erhan.onlinebilet.service;

import java.util.List;

import com.erhan.onlinebilet.model.ExpenseType;

public interface ExpenseTypeService {
	
	public Long create(ExpenseType expenseType);
	public ExpenseType findById(Long id);
	public List<ExpenseType> findAll();
}
