package com.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dao.IUserDao;
import com.dao.UserDao;
import com.entity.User;

public class RegisterUserServiceTest {

	private IUserDao userDao;
	private IUserService userService;
	private EntityManagerFactory emf;
	private EntityManager em;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("ecommercePU");
		em = emf.createEntityManager();
		userDao = new UserDao(em);
		userService = new UserService(userDao);
	}

	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

	@Test
	public void testRegisterUserValid() {
		User registeredUser = userService.register("John", "Doe");

		assertNotNull(registeredUser);
		assertEquals("John", registeredUser.getFirstName());
		assertEquals("Doe", registeredUser.getLastName());
	}

	@Test
	public void testRegisterUserInvalidUsernameException() {
		try {
			userService.register("Jack", "Dim");
			fail("Expected InvalidUsernameException");
		} catch (InvalidUsernameException e) {
			// Expected exception
			e.getMessage();
		}
	}

	public class InvalidUsernameException extends RuntimeException {

		public InvalidUsernameException(String message) {
			super(message);
		}
	}
}
