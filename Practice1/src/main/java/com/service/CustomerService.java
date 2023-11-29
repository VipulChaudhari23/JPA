package com.service;

import com.dao.ICustomerDao;
import com.exception.CustomerNotFoundException;
import com.exception.InsufficientBalanceException;
import com.exception.InvalidIdException;
import com.exception.InvalidNameException;
import com.model.Customer;


public class CustomerService implements ICustomerService {

    private ICustomerDao customerDao;
        public CustomerService(ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer findById(Long id) throws InvalidIdException, CustomerNotFoundException {
        if (id <= 0) {
            throw new InvalidIdException("Customer ID: " + id+" is Invalid!!!");
        }

        return customerDao.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer is not available for given ID: " + id));
    }

    @Override
    public Customer register(String name, double balance) throws InvalidNameException, InsufficientBalanceException {
        if (name == null || !name.matches("^[a-zA-Z]+$") || name.length() < 2 || name.length() > 15) {
            throw new InvalidNameException("Customer Name: " + name +"is Invalid!!!");
        }
        if (balance <= 5000) {
            throw new InsufficientBalanceException("Insufficient balance: " + balance);
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setBalance(balance);

        return customerDao.save(customer);
    }
}
