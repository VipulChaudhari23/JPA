package com.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import com.dao.ICustomerDao;
import com.exception.CustomerNotFoundException;
import com.exception.InsufficientBalanceException;
import com.exception.InvalidIdException;
import com.exception.InvalidNameException;
import com.model.Customer;
import com.service.CustomerService;

public class CustomerServiceTest2 {

	private ICustomerDao customerDao;
	private CustomerService customerService;

	@Before
	public void setUp() {

		customerDao = new StubCustomerDao();

		customerService = new CustomerService(customerDao);
	}

	@Test
	public void testRegisterCustomer_ValidData() {
		Customer registeredCustomer = customerService.register("Sonali", 50000);

		assertNotNull(registeredCustomer);
		assertNotNull(registeredCustomer.getId()); // Assuming getId() returns the ID

	}

	@Test
	public void testRegisterCustomer_InvalidName_Numeric_ThrowsInvalidNameException() {
		assertThrows(InvalidNameException.class, () -> customerService.register("xyz98", 50000));

	}

	@Test
	public void testRegisterCustomer_InvalidName_LengthSmall_ThrowsInvalidNameException() {
		assertThrows(InvalidNameException.class, () -> {
			customerService.register("m", 50000);
		});

	}

	@Test
	public void testRegisterCustomer_InvalidName_LengthLarge_ThrowsInvalidNameException() {
		assertThrows(InvalidNameException.class, () -> {
			customerService.register("uuuuuuuuuuuuuuuuu", 50000);
		});

	}

	@Test
	public void testRegisterCustomer_InsufficientBalance_ThrowsInsufficientBalanceException() {
		// Insufficient balance, should throw InsufficientBalanceException
		assertThrows(InsufficientBalanceException.class, () -> {
			customerService.register("Sonali", 3000);
		});

	}

	@Test
	public void testFindCustomerById_ValidId_ReturnsCustomer() {
		long id = 1; // Assuming the first customer has ID 1
		Customer registeredCustomer = customerService.register("Sonali", 50000);
		Customer foundCustomer = customerService.findById(id);

		assertNotNull(foundCustomer);
		assertEquals(id, foundCustomer.getId().longValue());
		assertEquals(registeredCustomer.getName(), foundCustomer.getName());
		assertEquals(registeredCustomer.getBalance(), foundCustomer.getBalance(), 0);
	}

	@Test
	public void testFindCustomerById_InvalidId_ThrowsCustomerNotFoundException() {
		long invalidId = 100;
		assertThrows(CustomerNotFoundException.class, () -> {
			customerService.findById(invalidId);
		});
	}

	@Test
	public void testFindCustomerById_InvalidIdNegative_ThrowsCustomerNotFoundException() {
		long invalidId = -10;
		assertThrows(InvalidIdException.class, () -> {
			customerService.findById(invalidId);
		});
	}
}
