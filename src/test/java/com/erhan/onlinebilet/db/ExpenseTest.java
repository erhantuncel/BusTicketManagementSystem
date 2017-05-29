package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.onlinebilet.model.Expense;
import com.erhan.onlinebilet.model.Voyage;
import com.erhan.onlinebilet.service.ExpenseService;

public class ExpenseTest extends BaseTest{

	@Autowired
	ExpenseService expenseService;
	
	@Test
	public void testGetTotalForCurrentYear() {
		renewTransaction();
		
		BigDecimal totalPrice = expenseService.getTotalForCurrentYear();
		assertNotNull(totalPrice);
//		System.out.println("Total Price (2016) = " + totalPrice);
	}
	
	@Test
	public void testFindAllOrderedByRegisterDateUnlimited() {
		renewTransaction();
		
		List<Expense> expenseList = expenseService.findAllOrderedByRegisterDate(0);
		assertNotNull(expenseList);
		System.out.println("Expense List size = " + expenseList.size());
		
	}
	
	@Test
	public void testFindAllOrderedByRegisterDateLimited() {
		renewTransaction();
		
		Integer limit = 50;
		List<Expense> expenseList = expenseService.findAllOrderedByRegisterDate(limit);
		assertNotNull(expenseList);
		assertEquals(expenseList.size(), limit.intValue());
		System.out.println("Expense List size = " + expenseList.size());
		
	}
	
	@Test
	public void testFindAllByDate() {
		renewTransaction();
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		List<Expense> expenseList = expenseService.findAllByDate(gc.getTime());
		for(Expense expense : expenseList) {
			GregorianCalendar gcTemp = new GregorianCalendar();
			gcTemp.setTime(expense.getRegisteredTime());
			assertEquals(gc.get(Calendar.DAY_OF_YEAR), gcTemp.get(Calendar.DAY_OF_YEAR));
		}
		System.out.println("First expense registered Time = " + expenseList.get(0).getRegisteredTime());
	}
	
	@Test
	public void testUpdate() {
		renewTransaction();
		
		Expense expense10 = expenseService.findById(10L);
		BigDecimal price10 = expense10.getPrice();
		System.out.println("price before update = " + price10);
		
		expense10.setPrice(price10.add(new BigDecimal("100.00")));
		expenseService.update(expense10);
		
		renewTransaction();
		Expense expense10AfterUpdate = expenseService.findById(10L);
		BigDecimal price10AfterUpdate = expense10AfterUpdate.getPrice();
		assertEquals(price10AfterUpdate.subtract(price10), new BigDecimal("100.00"));
		System.out.println("price after update = " + price10AfterUpdate);
	}
	
	@Test
	public void testDelete() {
		renewTransaction();
		
		Expense expense10 = expenseService.findById(10L);
		Long voyageId = expense10.getVoyage().getId();
		System.out.println("Voyage Id = " + voyageId);
		
		int result = expenseService.delete(expense10.getId());
		assertEquals(result, 1);
		
		renewTransaction();
		Expense expense10AfterDelete = expenseService.findById(10L);
		assertNull(expense10AfterDelete);
		Voyage voyage = voyageService.findById(voyageId);
		assertNotNull(voyage);
	}

	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
