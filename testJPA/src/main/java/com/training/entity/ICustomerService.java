package com.training.entity;

import java.util.Optional;
import java.util.Set;

public interface ICustomerService {
    void insertCustomer(Customer customer);
    // You can add more methods as needed
    public Optional<Customer> findCustomerById(int id) ;
}
