package com.service;

import java.util.Optional;

import com.dao.IOrderDao;
import com.exception.InvalidIdException;
import com.exception.InvalidPriceException;
import com.exception.InvalidProductNameException;
import com.exception.OrderNotFoundException;
import com.model.CreatedOrder;
import com.model.Customer;


public class OrderService implements IOrderService {

    
    private IOrderDao orderDao;
    private ICustomerService customerService;

    public OrderService(IOrderDao orderDao, ICustomerService customerService) {
        this.orderDao = orderDao;
        this.customerService = customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
	@Override
    public CreatedOrder createOrder(Long customerId, String productDesc, double productPrice)
            throws InvalidIdException, InvalidProductNameException, InvalidPriceException {
	    if (customerService == null) {
            throw new RuntimeException("CustomerService is not properly initialized.");
        }

        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            throw new InvalidIdException("Customer " + customerId+" not found for given ID");
        }
        if (customerId <= 0) {
            throw new InvalidIdException("Customer-ID " + customerId +" is Invalid!!!");
        }
        if (productDesc == null || !productDesc.matches("^[a-zA-Z0-9]+$") || productDesc.length() < 2 || productDesc.length() > 20) {
            throw new InvalidProductNameException("Product name " + productDesc +" is Invalid!!!");
        }

        if (productPrice <= 0) {
            throw new InvalidPriceException("Invalid product price: " + productPrice);
        }

        CreatedOrder createdOrder = new CreatedOrder();
        createdOrder.setCreatedBy(customer);
        createdOrder.setProductInfo(productDesc);
        createdOrder.setAmount(productPrice);
        createdOrder.setCreationDateTimeMillis(System.currentTimeMillis());

        return orderDao.save(createdOrder);
    }

    @Override
    public Optional<CreatedOrder> findById(Long orderId) throws InvalidIdException, OrderNotFoundException {
        if (orderId <= 0) {
            throw new InvalidIdException("Invalid order ID: " + orderId);
        }

        return orderDao.findById(orderId);
    }
}
