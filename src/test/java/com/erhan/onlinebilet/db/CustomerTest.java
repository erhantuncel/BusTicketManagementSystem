package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.onlinebilet.model.Customer;
import com.erhan.onlinebilet.service.CustomerService;

public class CustomerTest extends BaseTest {

	@Autowired
	CustomerService customerService;
	
	@Test
	public void testFindAll() {
		renewTransaction();
		
		List<Customer> customerList = customerService.findAll();
//		assertTrue(customerList.size()>24);
		assertNotNull(customerList);
		System.out.println("Customer Count : " + customerList.size());
		Customer customer0 = customerList.get(0);
		System.out.println("Customer 0 = " + customer0.getName() + " " + customer0.getSurname());
//		for(Customer c : customerList) {
//			System.out.print("Id = " + c.getId() + "\t");
//			System.out.print("Name = " + c.getName() + "\t");
//			System.out.print("Surname = " + c.getSurname() + "\t");
//			System.out.println();
//		}
	}

	@Test
	public void testFindCustomerById() {
		renewTransaction();
		
		Customer cust = customerService.findById(10L);
		assertNotNull(cust);
//		System.out.println("Customer Name : " + cust.getName());
//		System.out.println("Customer Surname : " + cust.getSurname());
//		System.out.println("Customer TC Number : " + cust.getTcNumber());
//		Set<UserRole> roleSet = cust.getUserRole();
//		for(UserRole role : roleSet) {			
//			System.out.println("Role : " + role.getRole());
//		}
	}
	
	@Test
	public void testFindByTcNumber() {
		renewTransaction();
		
		Customer CustomerForTestID10 = customerService.findById(10L);
		
		renewTransaction();
		
		Customer customer = customerService.findByTcNumber(CustomerForTestID10.getTcNumber());
		assertEquals(CustomerForTestID10.getName(), customer.getName());
//		Customer admin = customerService.findByTcNumber("10987654321");
//		assertNull(admin);
		
	}
	
	@Test
	public void testCountAll() {
		renewTransaction();
		
		Integer count = customerService.countAll();
		assertNotNull(count);
//		System.out.println("Customer Count : " + count);
	}
	
	@Test
	public void testCountMonthly() {
		renewTransaction();
		
		List<String[]> countList = customerService.countMonthly(2016);
		assertEquals(countList.size(), 12);
		for(String[] o : countList) {
			System.out.println(o[0] + "\t" + o[1]);
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testfindAllSortedByLastRegistered() {
		renewTransaction();
		
		List<Customer> customerList = customerService.findAllSortedByLastRegistered();
//		assertNotEquals(customerList.get(0).getName(), "Erhan");
//		System.out.println("First Name = " + customerList.get(0).getName());
		assertTrue(customerList.get(2).getDateOfRegister().before(customerList.get(1).getDateOfRegister()));
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//		for(Customer c : customerList) {
//			System.out.println(c.getName() + "\t" 
//								+ c.getSurname() + "\t" 
//								+ c.getGender() + "\t" 
//								+ df.format(c.getDateOfRegister())
//								);
//		}
	}
	
	@Test
	public void testUpdate() {
		renewTransaction();
		
		Customer customer1 = customerService.findById(1L);
		customer1.seteMail("erhan@abc.com");
		customer1.setTcNumber("11111111111");
		customerService.update(customer1);
		
		renewTransaction();
		
		Customer customerTest = customerService.findById(1L);
		assertEquals(customerTest.geteMail(), "erhan@abc.com");
		assertEquals(customerTest.getTcNumber(), "11111111111");
	}
	
	@Test
	public void testDelete() {
		renewTransaction();
		
		Customer customer10 = customerService.findById(10L);
		customerService.delete(customer10);
		
		renewTransaction();
		
		Customer customerTest = customerService.findById(10L);
		assertNull(customerTest);
	}
	
	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}	
}