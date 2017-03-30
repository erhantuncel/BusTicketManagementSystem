package com.erhan.onlinebilet.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlinebilet.model.Expense;
import com.erhan.onlinebilet.model.Voyage;

@Repository("expenseDAO")
public class ExpenseDAOImpl implements ExpenseDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Long create(Expense expense) {
		sessionFactory.getCurrentSession().saveOrUpdate(expense);
		return expense.getId();
	}

	@Override
	public Expense findById(Long id) {
		Expense expense = sessionFactory.getCurrentSession().get(Expense.class, id);
		return expense;
	}

	@Override
	public BigDecimal getTotalForCurrentYear() {
		GregorianCalendar firstDayOfCurrentYear = new GregorianCalendar();
		firstDayOfCurrentYear.setTime(new Date());
		firstDayOfCurrentYear.set(Calendar.DAY_OF_YEAR, 1);
		
		GregorianCalendar lastDayOfCurrentYear = new GregorianCalendar();
		lastDayOfCurrentYear.setTime(new Date());
		lastDayOfCurrentYear.set(Calendar.DAY_OF_YEAR, lastDayOfCurrentYear.getActualMaximum(Calendar.DAY_OF_YEAR));
		
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Expense.class);
		crt.add(Restrictions.ge("registeredTime", firstDayOfCurrentYear.getTime()));
		crt.add(Restrictions.le("registeredTime", lastDayOfCurrentYear.getTime()));
		crt.setProjection(Projections.sum("price"));
		BigDecimal totalPrice = (BigDecimal) crt.uniqueResult();
		return totalPrice;
	}
}