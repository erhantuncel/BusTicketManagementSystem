package com.erhan.onlineticket.dao;

import java.util.List;

import com.erhan.onlineticket.model.ExpenseType;

public interface ExpenseTypeDAO {

	public Long create(ExpenseType expenseType);
	public ExpenseType findById(Long id);
	public List<ExpenseType> findAll();
}
