package com.erhan.onlinebilet.dao;


import java.math.BigDecimal;

import com.erhan.onlinebilet.model.Expense;
import com.erhan.onlinebilet.model.Voyage;

public interface ExpenseDAO {
	public Long create(Expense expense);
	public Expense findById(Long id);
	public BigDecimal getTotalForCurrentYear();
}
