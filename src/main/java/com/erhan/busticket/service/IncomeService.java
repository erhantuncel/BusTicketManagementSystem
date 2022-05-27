package com.erhan.busticket.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.erhan.busticket.model.Income;
import com.erhan.busticket.model.Voyage;

public interface IncomeService {
	public Long create(Income income);
	public Income findById(Long id);
	public List<Income> findAll();
	public BigDecimal getTotalForCurrentYear();
	public List<Income> findAllByDate(Date date);
	public List<Income> findAllOrderByDate(Integer limit);
	public Income findByVoyage(Voyage voyage);
	public void update(Income income);
}
