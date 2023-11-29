package com.dao;

import java.util.Optional;

import com.entity.User;

public interface IUserDao {
    User save(User user);
    Optional<User> findById(long userId);
}
	