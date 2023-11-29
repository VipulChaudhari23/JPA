package com.main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

import com.dao.FeedbackDao;
import com.dao.IFeedbackDao;
import com.dao.IUserDao;
import com.dao.UserDao;
import com.entity.Feedback;
import com.entity.IssueType;
import com.entity.User;
import com.service.FeedbackService;
import com.service.IFeedbackService;
import com.service.IUserService;
import com.service.UserService;

public class Main {

	public static void main(String[] args) {
		// Initialize EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercePU");

		// Initialize DAOs and Services
		IUserDao userDao = new UserDao(emf.createEntityManager());
		IUserService userService = new UserService(userDao);

		IFeedbackDao feedbackDao = new FeedbackDao(emf.createEntityManager());
		IFeedbackService feedbackService = new FeedbackService(feedbackDao, userDao, emf.createEntityManager());

		try {
			// Register User
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter First Name: ");
			String firstName = scanner.nextLine();
			System.out.print("Enter Last Name: ");
			String lastName = scanner.nextLine();

			User user = userService.register(firstName, lastName);
			System.out.println("Registered User: " + user.getFirstName() + " " + user.getLastName());

			// Create Feedback
			System.out.print("Enter Feedback Description: ");
			String feedbackDescription = scanner.nextLine();
			System.out.println("Select Issue Type (PRODUDCT_BROKEN, PRODUCT_QUALITY, DELIVERY_ISSUE): ");
			String issueTypeString = scanner.nextLine();
			IssueType issueType = IssueType.valueOf(issueTypeString.toUpperCase());

			// Merge the user entity to make it managed
			user = userService.findById(user.getId());

			Feedback feedback = feedbackService.add(user.getId(), feedbackDescription, issueType);
			System.out.println(
					"Added Feedback: " + feedback.getDescription() + " by User ID: " + feedback.getCreatedBy().getId());

			// Find User by ID
			System.out.print("Enter User ID to find: ");
			long userId = scanner.nextLong();
			User foundUser = userService.findById(userId);
			System.out.println("Found User: " + foundUser.getFirstName() + " " + foundUser.getLastName());

			// Find Feedback by ID
			System.out.print("Enter Feedback ID to find: ");
			long feedbackId = scanner.nextLong();
			Feedback foundFeedback = feedbackService.findById(feedbackId);
			System.out.println("Found Feedback: " + foundFeedback.getDescription() + " by User ID: "
					+ foundFeedback.getCreatedBy().getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close EntityManagerFactory
			emf.close();
		}
	}
}
