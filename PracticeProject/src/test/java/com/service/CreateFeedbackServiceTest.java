package com.service;

import com.dao.FeedbackDao;
import com.dao.IFeedbackDao;
import com.dao.IUserDao;
import com.dao.UserDao;
import com.entity.Feedback;
import com.entity.IssueType;
import com.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class CreateFeedbackServiceTest {

	private IUserDao userDao;
	private IFeedbackDao feedbackDao;
	private IFeedbackService feedbackService;
	private EntityManagerFactory emf;
	private EntityManager em;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("ecommercePU");
		em = emf.createEntityManager();
		userDao = new UserDao(em);
		feedbackDao = new FeedbackDao(em);
		feedbackService = new FeedbackService(feedbackDao, userDao, em);
	}

	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

	@Test
	public void testCreateFeedbackValid() {
		User user = userDao.save(new User("John", "Doe"));

		Feedback feedback = feedbackService.add(user.getId(), "Great product!", IssueType.PRODUCT_QUALITY);

		assertNotNull(feedback);
		assertEquals("Great product!", feedback.getDescription());
		assertEquals(IssueType.PRODUCT_QUALITY, feedback.getIssue());
		assertEquals(user, feedback.getCreatedBy());
	}

	@Test
	public void testCreateFeedbackInvalidDescription() {
		User user = userDao.save(new User("John", "Doe"));

		try {
			feedbackService.add(user.getId(), "G", IssueType.PRODUCT_QUALITY);
			fail("Expected InvalidFeedbackDescription");
		} catch (InvalidFeedbackDescription e) {
			// Expected exception
		}
	}

	@Test
	public void testCreateFeedbackInvalidIssue() {
		User user = userDao.save(new User("John", "Doe"));

		try {
			feedbackService.add(user.getId(), "Great product!",IssueType.PRODUCT_QUALITY);
			fail("Expected InvalidIssueException");
		} catch (InvalidIssueException e) {
			// Expected exception
		}
	}

	@Test
	public void testCreateFeedbackInvalidUserId() {
		try {
			feedbackService.add(1, "Great product!", IssueType.PRODUCT_QUALITY);
			fail("Expected InvalidUserIdException");
		} catch (InvalidUserIdException e) {
			// Expected exception
		}
	}

	@Test
	public void testCreateFeedbackUserNotFoundException() {
		try {
			feedbackService.add(1, "Great product!", IssueType.PRODUCT_QUALITY);
			fail("Expected UserNotFoundException");
		} catch (UserNotFoundException e) {
			// Expected exception
		}
	}

	public class InvalidUsernameException extends RuntimeException {

		public InvalidUsernameException(String message) {
			super(message);
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

	public class InvalidFeedbackDescription extends RuntimeException {

		public InvalidFeedbackDescription(String message) {
			super(message);
		}
	}

	public class InvalidIssueException extends RuntimeException {

		public InvalidIssueException(String message) {
			super(message);
		}
	}
}
