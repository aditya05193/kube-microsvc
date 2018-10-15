package com.cts.microservice.order.logic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cts.microservice.order.OrderApp;
import com.cts.microservice.order.clients.Customer;
import com.cts.microservice.order.clients.CustomerClient;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = OrderApp.class)
@ActiveProfiles("test")
public class CustomerConsumerDrivenContractTest {

	@Autowired
	CustomerClient customerClient;

	@Test
	public void testFindAll() {
		//Collection<Customer> result = customerClient.findAll();
		//assertEquals(1,result.stream().filter(c -> (c.getName().equals("kumar")&& c.getFirstname().equals("Aditya")&& c.getEmail().equals("aditya05193@gmail.com")&& c.getStreet().equals("Hinjewadi") && c.getCity().equals("Pune"))).count());
	}

	@Test
	public void testGetOne() {
		Collection<Customer> allCustomer = customerClient.findAll();
		Long id = allCustomer.iterator().next().getCustomerId();
		Customer result = customerClient.getOne(id);
		assertEquals(id.longValue(), result.getCustomerId());
	}

	@Test
	public void testValidCustomerId() {
		Collection<Customer> allCustomer = customerClient.findAll();
		Long id = allCustomer.iterator().next().getCustomerId();
		assertTrue(customerClient.isValidCustomerId(id));
		assertFalse(customerClient.isValidCustomerId(-1));
	}

}
