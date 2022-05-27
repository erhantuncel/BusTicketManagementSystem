package com.erhan.busticket.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.busticket.model.ExpenseType;
import com.erhan.busticket.service.ExpenseTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
								 "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ExpenseTypeTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	ExpenseTypeService expenseTypeService;
	
	@Test
	public void testCreateAndFindById() {
		ExpenseType expenseType = new ExpenseType("Yakıt");
		Long id = expenseTypeService.create(expenseType);
		
		TestTransaction.flagForCommit();
		TestTransaction.end();
		TestTransaction.start();
		
		ExpenseType expenseTypeReturn = expenseTypeService.findById(id);
		assertNotNull(expenseTypeReturn);
		System.out.println(expenseTypeReturn);
	}
	
	@Test
	public void testFindAll() {
		List<ExpenseType> expenseTypeList = expenseTypeService.findAll();
		assertEquals(expenseTypeList.size(), 5);
		for(ExpenseType type : expenseTypeList) {
			System.out.println(type);
		}
	}

}
