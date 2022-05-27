package com.erhan.busticket.dao;

import java.util.List;

import com.erhan.busticket.model.ExpenseType;

public interface ExpenseTypeDAO {

	public Long create(ExpenseType expenseType);
	public ExpenseType findById(Long id);
	public List<ExpenseType> findAll();
}
