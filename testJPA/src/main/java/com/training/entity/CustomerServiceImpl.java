package com.training.entity;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerServiceImpl implements ICustomerService {
    private EntityManagerFactory emf;

    public CustomerServiceImpl() {
        emf = Persistence.createEntityManagerFactory("jpa-example");
    }

    public void insertCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }
    
    public Optional<Customer> findCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return Optional.ofNullable(customer);
        
        
//        EntityManager em = emf.createEntityManager();
//        Customer customer = em.find(Customer.class, id);
//        em.close();
//
//        Set<Customer> resultSet = new HashSet<>();
//        if (customer != null) {
//            resultSet.add(customer);
//        }
//
//        return resultSet;
    }
    
}
