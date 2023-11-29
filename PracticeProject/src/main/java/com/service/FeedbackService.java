package com.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.dao.IFeedbackDao;
import com.dao.IUserDao;
import com.entity.Feedback;
import com.entity.IssueType;
import com.entity.User;
import com.service.FeedbackService.InvalidFeedbackDescription;
import com.service.FeedbackService.InvalidIDException;

public class FeedbackService implements IFeedbackService {

	private final IFeedbackDao feedbackDao;
	private final IUserDao userDao;
	private final EntityManager entityManager;

	public FeedbackService(IFeedbackDao feedbackDao, IUserDao userDao, EntityManager entityManager) {
		this.feedbackDao = feedbackDao;
		this.userDao = userDao;
		this.entityManager = entityManager;
	}

	@Override
	public Feedback add(long createdByUserId, String description, IssueType issue) {
		Optional<User> optionalUser = userDao.findById(createdByUserId);
		if (optionalUser.isPresent()) {
			User createdBy = optionalUser.get();
			Feedback feedback = new Feedback(createdByUserId, description, createdBy, issue);
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			Feedback mergedFeedback = entityManager.merge(feedback); // Use merge to handle detached entity
			transaction.commit();
			return mergedFeedback;
		} else {
			throw new RuntimeException("User not found for ID: " + createdByUserId);
		}
	}

	@Override
	public Feedback findById(long feedbackId) {
		return feedbackDao.findById(feedbackId)
				.orElseThrow(() -> new RuntimeException("Feedback not found for ID: " + feedbackId));
	}

	private void validateFeedbackDescription(String description) {
		if (description == null || description.length() < 2 || description.length() > 50) {
			throw new InvalidFeedbackDescription("Invalid feedback description");
		}
	}

	private void validateFeedbackId(long feedbackId) {
		if (feedbackId <= 0) {
			throw new InvalidIDException("Invalid feedback ID");
		}
	}

	public class NoFeedbackFoundException extends RuntimeException {
		public NoFeedbackFoundException(String message) {
			super(message);
		}
	}

	public class InvalidIDException extends RuntimeException {
		public InvalidIDException(String message) {
			super(message);
		}
	}

	public class InvalidFeedbackDescription extends RuntimeException {
		public InvalidFeedbackDescription(String message) {
			super(message);
		}
	}

}
