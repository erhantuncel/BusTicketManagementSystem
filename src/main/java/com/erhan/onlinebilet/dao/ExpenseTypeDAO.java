package com.erhan.onlinebilet.dao;

import java.util.List;

import com.erhan.onlinebilet.model.ExpenseType;

public interface ExpenseTypeDAO {

	public Long create(ExpenseType expenseType);
	public ExpenseType findById(Long id);
	public List<ExpenseType> findAll();
}
