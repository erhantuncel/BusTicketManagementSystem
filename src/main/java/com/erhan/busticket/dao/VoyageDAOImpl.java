package com.erhan.busticket.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erhan.busticket.model.Route;
import com.erhan.busticket.model.Voyage;

@Repository("voyageDAO")
public class VoyageDAOImpl implements VoyageDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Long create(Voyage voyage) {
//		Long id = (Long) sessionFactory.getCurrentSession().save(voyage);
//		return id;
		sessionFactory.getCurrentSession().saveOrUpdate(voyage);
		return voyage.getId();
	}

	@Override
	public Voyage findById(Long id) {
		Voyage voyage = sessionFactory.getCurrentSession().get(Voyage.class, id);
		return voyage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voyage> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Voyage");
		List<Voyage> voyageList = query.list();
		return voyageList;
	}

	@Override
	public void update(Voyage voyage) {
		sessionFactory.getCurrentSession().update(voyage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voyage> findAllByDate(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
//		gc.set(Calendar.HOUR_OF_DAY, 0);
//		gc.set(Calendar.MINUTE, 0);
//		gc.set(Calendar.SECOND, 0);
		Date startDate = gc.getTime();
		gc.set(Calendar.HOUR_OF_DAY, 23);
		gc.set(Calendar.MINUTE, 59);
		gc.set(Calendar.SECOND, 59);
		Date endDate = gc.getTime();
		
		
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Voyage.class);
		crt.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		crt.add(Restrictions.between("departureTime", startDate, endDate));
		crt.addOrder(Order.asc("departureTime"));
		List<Voyage> voyageList = crt.list();
		return voyageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voyage> findAllByRouteAndDate(Route route, Date date) {
		GregorianCalendar now = new GregorianCalendar();
		now.setTime(new Date());
		
		GregorianCalendar startGc = new GregorianCalendar();
		startGc.setTime(date);
		if(startGc.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR)) {
			startGc.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
			startGc.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
			startGc.set(Calendar.SECOND, now.get(Calendar.SECOND));			
		}
		Date startDate = startGc.getTime();
		
		GregorianCalendar endGc = new GregorianCalendar();
		endGc.setTime(date);
		endGc.set(Calendar.HOUR_OF_DAY, 23);
		endGc.set(Calendar.MINUTE, 59);
		endGc.set(Calendar.SECOND, 59);
		Date endDate = endGc.getTime();
		
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Voyage.class);
		crt.add(Restrictions.eq("route", route));
		crt.add(Restrictions.between("departureTime", startDate, endDate));
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crt.addOrder(Order.asc("departureTime"));
		List<Voyage> voyageList = crt.list();
		return voyageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voyage> findAllStartedInHour(Integer hour) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		Date now = gc.getTime();
		
		gc.set(Calendar.HOUR_OF_DAY, gc.get(Calendar.HOUR_OF_DAY) - hour);
		Date beforeFiveHour = gc.getTime();
		
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Voyage.class);
		crt.add(Restrictions.between("departureTime", beforeFiveHour, now));
		crt.addOrder(Order.desc("departureTime"));
		List<Voyage> voyageList = crt.list();
		return voyageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voyage> findAllSortedByDepartureTimeForRecords(Integer count) {
//		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Voyage.class);
//		crt.addOrder(Order.asc("departureTime"));
//		crt.setMaxResults(count);
//		crt.setProjection(Projections.distinct(Projections.property("id")));
//		List<Voyage> voyageList = crt.list();
		
		String hql = "from Voyage v order by v.departureTime desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setMaxResults(count);
		List<Voyage> voyageList = query.list();
		return voyageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voyage> findAllBetweenDates(Date start, Date end) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Voyage.class);
		crt.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		crt.add(Restrictions.between("departureTime", start, end));
		crt.addOrder(Order.asc("departureTime"));
		List<Voyage> voyageList = crt.list();
		return voyageList;
	}

	@Override
	public void delete(Voyage voyage) {
		sessionFactory.getCurrentSession().delete(voyage);
	}
}
