package com.erhan.busticket.dao;

import java.util.Date;
import java.util.GregorianCalendar;
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

import com.erhan.busticket.model.City;
import com.erhan.busticket.model.Customer;
import com.erhan.busticket.model.Ticket;
import com.erhan.busticket.model.Voyage;

@Repository("ticketDAO")
public class TicketDAOImpl implements TicketDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Long create(Ticket ticket) {
		sessionFactory.getCurrentSession().saveOrUpdate(ticket);
		Long id = ticket.getId();
		return id;
	}

	@Override
	public Ticket findById(Long id) {
		Ticket ticket = sessionFactory.getCurrentSession().get(Ticket.class, id);
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Ticket");
		List<Ticket> ticketList = query.list();
		return ticketList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findByCustomer(Customer customer) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.add(Restrictions.eq("customer", customer));
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Ticket> tickets = (List<Ticket>) crt.list();
		return tickets;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findForFutureByCustomer(Customer customer) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.createAlias("voyage", "voyage");
		crt.add(Restrictions.ge("voyage.departureTime", gc.getTime()));
		crt.add(Restrictions.eq("customer", customer));
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Ticket> tickets = (List<Ticket>) crt.list();
		return tickets;
	}

	@Override
	public int delete(Long id) {
		String hql = "delete Ticket where id = :ticketId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("ticketId", id);
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public Integer countAll() {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.setProjection(Projections.rowCount());
		Long countLong = (Long) crt.uniqueResult();
		Integer count = countLong.intValue();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> countMonthly(Integer year) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
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
	public List<Ticket> findByVoyage(Voyage voyage) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.add(Restrictions.eq("voyage", voyage));
		crt.addOrder(Order.asc("seatNumber"));
		crt.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Ticket> tickets = crt.list();
		return tickets;
	}

	@Override
	public Integer countReservationByVoyage(Voyage voyage) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.add(Restrictions.eq("voyage", voyage));
		crt.add(Restrictions.eq("isReservation", true));
		crt.setProjection(Projections.rowCount());
		Long countLong = (Long) crt.uniqueResult();
		Integer count = countLong.intValue();
		return count;
	}

	@Override
	public Integer countTicketByVoyage(Voyage voyage) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.add(Restrictions.eq("voyage", voyage));
		crt.add(Restrictions.eq("isReservation", false));
		crt.setProjection(Projections.rowCount());
		Long countLong = (Long) crt.uniqueResult();
		Integer count = countLong.intValue();
		return count;
	}

	@Override
	public Ticket findByVoyageAndSeatNumber(Voyage voyage, Byte seatNumber) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.add(Restrictions.eq("voyage", voyage));
		crt.add(Restrictions.eq("seatNumber", seatNumber));
		Ticket ticket = (Ticket) crt.uniqueResult();
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Byte> findSeatNumbersByVoyage(Voyage voyage) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.add(Restrictions.eq("voyage", voyage));
		crt.setProjection(Projections.property("seatNumber"));
		crt.addOrder(Order.asc("seatNumber"));
		List<Byte> seatNumbers = crt.list();
		return seatNumbers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Byte> findSeatNumbersByVoyageAndArrival(Voyage voyage, City arrival) {
		Criteria crt = sessionFactory.getCurrentSession().createCriteria(Ticket.class);
		crt.add(Restrictions.eq("voyage", voyage));
		crt.add(Restrictions.eq("arrival", arrival));
		crt.setProjection(Projections.property("seatNumber"));
		crt.addOrder(Order.asc("seatNumber"));
		List<Byte> seatNumbers = crt.list();
		return seatNumbers;
	}

	@Override
	public void update(Ticket ticket) {
		sessionFactory.getCurrentSession().update(ticket);
	}
}
