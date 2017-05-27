package com.erhan.onlinebilet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.onlinebilet.dao.IncomeDAO;
import com.erhan.onlinebilet.model.Income;
import com.erhan.onlinebilet.model.Voyage;

@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	IncomeDAO incomeDAO;
	
	@Override
	@Transactional
	public Long create(Income income) {
		Long id = incomeDAO.create(income);
		return id;
	}

	@Override
	@Transactional
	public Income findById(Long id) {
		Income income = incomeDAO.findById(id);
		return income;
	}

	@Override
	@Transactional
	public List<Income> findAll() {
		List<Income> incomeList = incomeDAO.findAll();
		return incomeList;
	}

	@Override
	@Transactional
	public BigDecimal getTotalForCurrentYear() {
		BigDecimal sumTotal = incomeDAO.getTotalForCurrentYear();
		return sumTotal;
	}

	@Override
	@Transactional
	public List<Income> findAllByDate(Date date) {
		List<Income> incomeList = incomeDAO.findAllByDate(date);
		return incomeList;
	}

	@Override
	@Transactional
	public List<Income> findAllOrderByDate() {
		List<Income> incomeList = incomeDAO.findAllOrderByDate();
		return incomeList;
	}

	@Override
	@Transactional
	public Income findByVoyage(Voyage voyage) {
		Income income = incomeDAO.findByVoyage(voyage);
		return income;
	}

	@Override
	@Transactional
	public void update(Income income) {
		incomeDAO.update(income);
	}

}
