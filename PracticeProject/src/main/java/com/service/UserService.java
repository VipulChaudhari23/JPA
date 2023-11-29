package com.service;

import java.util.Optional;

import com.dao.IUserDao;
import com.entity.User;

public class UserService implements IUserService {
    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User register(String firstname, String lastname) {
        // Validate input parameters
        validateUsername(firstname, lastname);

        // Create a new user
        User user = new User(null, lastname, lastname, null);
        user.setFirstName(firstname);
        user.setLastName(lastname);

        // Save the user to the database
        return userDao.save(user);
    }

    @Override
    public User findById(long userId) {
        // Validate user ID
        validateUserId(userId);

        // Find the user in the database
        Optional<User> userOptional = userDao.findById(userId);
        return userOptional.orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private void validateUserId(long userId) {
        if (userId <= 0) {
            throw new InvalidUserIdException("Invalid user ID. User ID must be greater than zero.");
        }
    }


	private void validateUsername(String firstname, String lastname) {
        if (firstname == null || lastname == null || firstname.length() < 2 || lastname.length() < 2 || firstname.length() > 10 || lastname.length() > 10) {
            throw new InvalidUsernameException("Invalid username");
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
    
}
