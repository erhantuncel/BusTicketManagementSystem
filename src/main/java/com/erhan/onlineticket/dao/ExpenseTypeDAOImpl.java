package com.erhan.onlineticket.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlineticket.model.ExpenseType;

@Repository("expenseTypeDAO")
public class ExpenseTypeDAOImpl implements ExpenseTypeDAO {

	@Autowired
	SessionFactory sessionFactory;
		
	@Override
	public Long create(ExpenseType expenseType) {
		sessionFactory.getCurrentSession().saveOrUpdate(expenseType);
		Long id = expenseType.getId();
		
//		Long id = (Long) sessionFactory.getCurrentSession().save(expenseType);
		return id;
	}

	@Override
	public ExpenseType findById(Long id) {
		ExpenseType expenseType = sessionFactory.getCurrentSession().get(ExpenseType.class, id);
		return expenseType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpenseType> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from ExpenseType");
		List<ExpenseType> expenseTypeList = query.list();
		return expenseTypeList;
	}
}
