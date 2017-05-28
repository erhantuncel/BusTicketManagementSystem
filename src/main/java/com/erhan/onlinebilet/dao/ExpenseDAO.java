package com.erhan.onlinebilet.dao;


import java.math.BigDecimal;
import java.util.List;

import com.erhan.onlinebilet.model.Expense;

public interface ExpenseDAO {
	public Long create(Expense expense);
	public Expense findById(Long id);
	public List<Expense> findAllOrderedByRegisterDate(Integer limit);
	public BigDecimal getTotalForCurrentYear();
}
