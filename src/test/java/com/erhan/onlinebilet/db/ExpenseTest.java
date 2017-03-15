package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

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

	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}
