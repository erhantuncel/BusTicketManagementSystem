package com.erhan.onlinebilet.db;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.erhan.onlinebilet.model.Admin;
import com.erhan.onlinebilet.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
								 "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdminTest extends BaseTest {

	@Autowired
	AdminService adminService;
	
	@Test
	public void testFindByEmail() {
		Admin erhan = adminService.findByEmail("erhan@abc.com");
		assertNotNull(erhan);
		assertEquals(erhan.getName(), "Erhan");
		assertEquals(erhan.getPassword(), "e1223489");
	}
}
