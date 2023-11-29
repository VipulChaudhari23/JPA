package com.service;

import com.dao.IUserDao;
import com.dao.UserDao;
import com.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class FindUserServiceTest {

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
	public void testFindUserByIdValid() {
		User user = userService.register("John", "Doe");
		long userId = user.getId();

		User foundUser = userService.findById(userId);

		assertNotNull(foundUser);
//		assertEquals(userId, foundUser.getId());
		assertEquals("John", foundUser.getFirstName());
		assertEquals("Doe", foundUser.getLastName());
	}

	@Test
	public void testFindUserByIdNegativeId() {
		try {
			userService.findById(1);
			fail("Expected InvalidUserIdException");
		} catch (InvalidUserIdException e) {
			// Expected exception
		}
	}

	@Test
	public void testFindUserByIdUserNotFoundException() {
		try {
			userService.findById(2);
			fail("Expected UserNotFoundException");
		} catch (UserNotFoundException e) {
			// Expected exception
		}
	}

	public class InvalidUserIdException extends RuntimeException {

		public InvalidUserIdException(String message) {
			super(message);
		}
	}

	public class UserNotFoundException extends RuntimeException {

		public UserNotFoundException(String message) {
			super(message);
		}
	}
}
