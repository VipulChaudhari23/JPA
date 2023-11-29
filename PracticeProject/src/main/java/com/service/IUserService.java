package com.service;

import com.entity.User;

public interface IUserService {
    User register(String firstname, String lastname);
    User findById(long userId);
}
