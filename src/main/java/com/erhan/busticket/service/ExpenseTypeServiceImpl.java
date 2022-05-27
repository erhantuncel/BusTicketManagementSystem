package com.erhan.busticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.busticket.dao.ExpenseTypeDAO;
import com.erhan.busticket.model.ExpenseType;

@Service("expenseTypeService")
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

	@Autowired
	ExpenseTypeDAO expenseTypeDAO;
	
	@Override
	@Transactional
	public Long create(ExpenseType expenseType) {
		Long id = expenseTypeDAO.create(expenseType);
		return id;
	}

	@Override
	@Transactional
	public ExpenseType findById(Long id) {
		ExpenseType expenseType = expenseTypeDAO.findById(id);
		return expenseType;
	}

	@Override
	@Transactional
	public List<ExpenseType> findAll() {
		List<ExpenseType> expenseTypeList = expenseTypeDAO.findAll();
		return expenseTypeList;
	}

}
