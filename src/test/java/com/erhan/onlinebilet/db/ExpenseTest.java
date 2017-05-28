package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.onlinebilet.model.Expense;
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

	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
