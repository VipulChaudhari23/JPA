package com.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.entity.Feedback;

public class FeedbackDao implements IFeedbackDao {
    private final EntityManager entityManager;

    public FeedbackDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Feedback save(Feedback feedback) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(feedback);
        transaction.commit();
        return feedback;
    }

    @Override
    public Optional<Feedback> findById(long id) {
        return Optional.ofNullable(entityManager.find(Feedback.class, id));
    }
}
