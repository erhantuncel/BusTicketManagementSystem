package com.erhan.onlinebilet.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlinebilet.model.Income;
import com.erhan.onlinebilet.model.Voyage;

@Repository("incomeDAO")
public class IncomeDAOImpl implements IncomeDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Long create(Income income) {
		sessionFactory.getCurrentSession().saveOrUpdate(income);
		Long id = income.getId();
		return id;
	}

	@Override
	public Income findById(Long id) {
		Income income = sessionFactory.getCurrentSession().get(Income.class, id);
		return income;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Income> findAll() {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Income.class);
		List<Income> incomeList = crt.list();
		return incomeList;
	}

	@Override
	public BigDecimal getTotalForCurrentYear() {
		GregorianCalendar firstDayOfCurrentYear = new GregorianCalendar();
		firstDayOfCurrentYear.setTime(new Date());
		firstDayOfCurrentYear.set(Calendar.DAY_OF_YEAR, 1);
		
		GregorianCalendar lastDayOfCurrentYear = new GregorianCalendar();
		lastDayOfCurrentYear.setTime(new Date());
		lastDayOfCurrentYear.set(Calendar.DAY_OF_YEAR, lastDayOfCurrentYear.getActualMaximum(Calendar.DAY_OF_YEAR));
		
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Income.class);
		crt.add(Restrictions.ge("registeredTime", firstDayOfCurrentYear.getTime()));
		crt.add(Restrictions.le("registeredTime", lastDayOfCurrentYear.getTime()));
		crt.setProjection(Projections.sum("price"));
		BigDecimal sumTotal = (BigDecimal)crt.uniqueResult();
		return sumTotal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Income> findAllByDate(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int day = gc.get(Calendar.DAY_OF_YEAR);
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Income.class);
		crt.add(Restrictions.sqlRestriction("dayofyear({alias}.KAYIT_ZAMANI) = ?", day, IntegerType.INSTANCE));
		crt.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		crt.addOrder(Order.asc("registeredTime"));
		List<Income> incomeList = crt.list();
		return incomeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Income> findAllOrderByDate(Integer limit) {
		String hql = "from Income order by registeredTime desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(limit != 0) {
			query.setMaxResults(limit);
		}
		List<Income> incomeList = query.list(); 
		return incomeList;
	}

	@Override
	public Income findByVoyage(Voyage voyage) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Income.class);
		crt.add(Restrictions.eq("voyage", voyage));
		Income income = (Income) crt.uniqueResult();
		return income;
	}

	@Override
	public void update(Income income) {
		sessionFactory.getCurrentSession().update(income);
	}
}
