package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.springframework.test.context.transaction.TestTransaction;

import com.erhan.onlinebilet.model.Income;
import com.erhan.onlinebilet.model.Voyage;


public class IncomeTest extends BaseTest {

	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyy HH:mm");
	
	@Test
	public void testFindById() {
		renewTransaction();
		
		Income income10 = incomeService.findById(10L);
		assertNotNull(income10);
		System.out.println("Voyage Id " + income10.getVoyage().getId());
		System.out.println("Route : " + income10.getVoyage().getRoute().getRouteName());
		System.out.println("Total Price : " + income10.getPrice());
		System.out.println("Register Time : " + df.format(income10.getRegisteredTime()));
	}
	
	@Test
	public void testFindAll() {
		renewTransaction();
		
		List<Income> incomeList = incomeService.findAll();
		assertNotNull(incomeList);
		System.out.println("Income count : " + incomeList.size());
		Income i = incomeList.get(incomeList.size()-1);
		System.out.println("Id : " + i.getId());
		System.out.println("Voyage Id " + i.getVoyage().getId());
		System.out.println("Route : " + i.getVoyage().getRoute().getRouteName());
		System.out.println("Total Price : " + i.getPrice());
		System.out.println("Register Time : " + df.format(i.getRegisteredTime()));
	}
	
	@Test
	public void testGetTotalForCurrentYear() {
		renewTransaction();
		
		BigDecimal sumTotal = incomeService.getTotalForCurrentYear();
		assertNotNull(sumTotal);
		System.out.println("Sum Total Income for Current Year : " + sumTotal);
	}
	
	@Test
	public void testFindAllByDate() {
		renewTransaction();
		
		GregorianCalendar yesterday = new GregorianCalendar();
		yesterday.setTime(new Date());
		yesterday.add(Calendar.DAY_OF_YEAR, -1);
		List<Income> incomeList = incomeService.findAllByDate(yesterday.getTime());
		assertNotNull(incomeList);
		System.out.println(incomeList.size());
		for(Income i : incomeList) {
			System.out.println("Id : " + i.getId());
			System.out.println("Voyage Id " + i.getVoyage().getId());
			System.out.println("Route : " + i.getVoyage().getRoute().getRouteName());
			System.out.println("Total Price : " + i.getPrice());
			System.out.println("Register Time : " + df.format(i.getRegisteredTime()));
		}
	}
	
	@Test
	public void testFindAllOrderedByDate() {
		renewTransaction();
		
		List<Income> incomeList = incomeService.findAllOrderByDate();
		assertNotNull(incomeList);
		int size = incomeList.size();
		System.out.println("Size : " + size);
		int index = 1;
		for(Income i : incomeList) {
			System.out.println(index + " : " + df.format(i.getRegisteredTime()));
			index++;
		}
	}
	
	@Test
	public void testFindByVoyge() {
		renewTransaction();
		
		Voyage voyage10 = voyageService.findById(10L);
		Income incomeForVoyage10 = incomeService.findByVoyage(voyage10);
		assertNotNull(incomeForVoyage10);
		System.out.println("Id : " + incomeForVoyage10.getId());
		System.out.println("Voyage Id " + incomeForVoyage10.getVoyage().getId());
		System.out.println("Route : " + incomeForVoyage10.getVoyage().getRoute().getRouteName());
		System.out.println("Total Price : " + incomeForVoyage10.getPrice());
	}
	
	@Test
	public void testUpdate() {
		renewTransaction();
		
		Income income10 = incomeService.findById(10L);
		BigDecimal price10 = income10.getPrice();
		System.out.println("price10 = " + price10);
		
		income10.setPrice(price10.add(new BigDecimal(100)));
		incomeService.update(income10);
		
		renewTransaction();
		
		Income income10afterUpd = incomeService.findById(10L);
		System.out.println("price10 = " + income10afterUpd.getPrice() + " (After Update)");
		
		assertNotEquals(income10afterUpd.getPrice(), price10);
		
	}
	
	private void renewTransaction() {
		TestTransaction.flagForCommit();
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		TestTransaction.start();
	}
}