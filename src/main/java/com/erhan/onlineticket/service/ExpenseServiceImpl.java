package com.erhan.onlineticket.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlineticket.dao.ExpenseDAO;
import com.erhan.onlineticket.model.Expense;

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
	public List<Expense> findAllOrderedByRegisterDate(Integer limit) {
		List<Expense> expenseList = expenseDAO.findAllOrderedByRegisterDate(limit);
		return expenseList;
	}

	@Override
	@Transactional
	public List<Expense> findAllByDate(Date date) {
		List<Expense> expenseList = expenseDAO.findAllByDate(date);
		return expenseList;
	}

	@Override
	@Transactional
	public void update(Expense expense) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		expense.setRegisteredTime(gc.getTime());
		expenseDAO.update(expense);
	}

	@Override
	@Transactional
	public int delete(Long id) {
		int result = expenseDAO.delete(id);
		return result;
	}

	@Override
	@Transactional
	public BigDecimal getTotalForCurrentYear() {
		BigDecimal total = expenseDAO.getTotalForCurrentYear();
		return total;
	}	
}
