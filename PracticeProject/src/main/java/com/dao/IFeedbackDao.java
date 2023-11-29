package com.dao;

import java.util.Optional;

import com.entity.Feedback;

public interface IFeedbackDao {
    Feedback save(Feedback feedback);
    Optional<Feedback> findById(long id);
}
