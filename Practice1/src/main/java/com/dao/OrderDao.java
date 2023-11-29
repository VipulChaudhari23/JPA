package com.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.model.CreatedOrder;

public class OrderDao implements IOrderDao {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public CreatedOrder save(CreatedOrder createdOrder) {
		if (entityManager == null) {
			return null;
		}
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(createdOrder);
			transaction.commit();
			return createdOrder;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Optional<CreatedOrder> findById(Long id) {
		if (entityManager == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(entityManager.find(CreatedOrder.class, id));
	}
}
