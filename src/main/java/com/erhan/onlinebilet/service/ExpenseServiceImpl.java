package com.erhan.onlinebilet.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.ExpenseDAO;
import com.erhan.onlinebilet.model.Expense;

@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	ExpenseDAO expenseDAO;
	
	@Override
	@Transactional
	public Long create(Expense expense) {
		Long id = expenseDAO.create(expense);
		return id;
	}

	@Override
	@Transactional
	public Expense findById(Long id) {
		Expense expense = expenseDAO.findById(id);
		return expense;
	}

	@Override
	@Transactional
	public BigDecimal getTotalForCurrentYear() {
		BigDecimal total = expenseDAO.getTotalForCurrentYear();
		return total;
	}	
}
