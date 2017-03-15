package com.erhan.onlinebilet.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.onlinebilet.model.Customer;

@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Long create(Customer customer) {
//		Long id = (Long) sessionFactory.getCurrentSession().save(customer);
//		return id;
		
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
		return customer.getId();
	}

	@Override
	public Customer findById(Long id) {
		Customer customer = sessionFactory.getCurrentSession().get(Customer.class, id);
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Customer");
		List<Customer> customerList = query.list();
		return customerList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer findByTcNumber(String tcNumber) {
		
		List<Customer> customers = new ArrayList<Customer>();
		
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		crt.add(Restrictions.eq("tcNumber", tcNumber));
		customers = crt.list();
		
		if(customers.size() > 0) {
			return customers.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Integer countAll() {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		crt.setProjection(Projections.rowCount());
		Long countLong = (Long) crt.uniqueResult();
		Integer count = countLong.intValue();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> countMonthly(Integer year) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		crt.add(Restrictions.sqlRestriction("year({alias}.KAYIT_ZAMANI) = ?", year, IntegerType.INSTANCE));
		crt.setProjection(Projections.projectionList()
				.add(Projections.sqlGroupProjection(
						"month({alias}.KAYIT_ZAMANI) as registerTime", 
						"month({alias}.KAYIT_ZAMANI)", 
						new String[]{"registerTime"}, 
						new Type[]{IntegerType.INSTANCE}))
				.add(Projections.rowCount())
				);
		List<Object[]> countsMonthly = crt.list();
		return countsMonthly;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAllSortedByLastRegistered() {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		crt.addOrder(Order.desc("dateOfRegister"));
		List<Customer> customerList = crt.list();
		return customerList;
	}

	@Override
	public void update(Customer customer) {
		sessionFactory.getCurrentSession().update(customer);
	}

	@Override
	public void delete(Customer customer) {
		sessionFactory.getCurrentSession().delete(customer);
	}

	
}