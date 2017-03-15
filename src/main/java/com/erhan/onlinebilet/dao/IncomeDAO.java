package com.erhan.onlinebilet.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.erhan.onlinebilet.model.Income;
import com.erhan.onlinebilet.model.Voyage;

public interface IncomeDAO {
	public Long create(Income income);
	public Income findById(Long id);
	public List<Income> findAll();
	public BigDecimal getTotalForCurrentYear();
	public List<Income> findAllByDate(Date date);
	public List<Income> findAllOrderByDate();
	public BigDecimal getTotalForVoyage(Voyage voyage);
}
