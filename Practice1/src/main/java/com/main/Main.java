package com.main;

import java.util.Optional;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dao.CustomerDao;
import com.dao.ICustomerDao;
import com.dao.IOrderDao;
import com.dao.OrderDao;
import com.model.CreatedOrder;
import com.model.Customer;
import com.service.CustomerService;
import com.service.ICustomerService;
import com.service.IOrderService;
import com.service.OrderService;

public class Main {
    public static void main(String[] args) {
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ecommerceapplication");
        EntityManager entityManager = emf.createEntityManager();
        ICustomerDao customerDao = new CustomerDao();
        ((CustomerDao) customerDao).setEntityManager(entityManager);

        ICustomerService customerService = new CustomerService(customerDao);       
        IOrderDao orderDao = new OrderDao();
        ((OrderDao) orderDao).setEntityManager(entityManager);

        IOrderService orderService = new OrderService(orderDao, customerService);


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the customer name to register:- ");
        String customerName = scanner.nextLine();

        System.out.print("Enter the customer balance of the customer:-  ");
        double customerBalance = scanner.nextDouble();

        try {
            Customer registeredCustomer = customerService.register(customerName, customerBalance);
            System.out.println("Congratulations!!!Customer is successfully registered. ID: " + registeredCustomer.getId());
        } catch (Exception e) {
            System.out.println("Error faced while registering customer: " + e.getMessage());
        }

        System.out.print("Enter customer ID that needs to be searched: ");
        long customerIdToFind = scanner.nextLong();

        try {
            Customer foundCustomer = customerService.findById(customerIdToFind);
            System.out.println("1 Customer found: " + foundCustomer);
        } catch (Exception e) {
            System.out.println("Error finding customer: " + e.getMessage());
        }

        System.out.print("\nEnter customer ID for the order that needs to be created:- ");
        long orderCustomerId = scanner.nextLong();

        System.out.print("Enter the product name for the order:-");
        String orderProductName = scanner.next();

        System.out.print("Enter the product price for the order:- ");
        double orderProductPrice = scanner.nextDouble();

        try {
            CreatedOrder createdOrder = orderService.createOrder(orderCustomerId, orderProductName, orderProductPrice);
            System.out.println("Congratulations!!! The order has been created successfully. Order ID: " + createdOrder.getId());
        } catch (Exception e) {
            System.out.println("Error creating order: " + e.getMessage());
        }

        System.out.print("Enter the order ID that needs to be searched: ");
        long orderIdToFind = scanner.nextLong();

        try {
            Optional<CreatedOrder> foundOrder = orderService.findById(orderIdToFind);
            foundOrder.ifPresentOrElse(
                    order -> System.out.println("1 Order details Found: " + order),
                    () -> System.out.println("Order not found for ID: " + orderIdToFind)
            );
        } catch (Exception e) {
            System.out.println("Error while finding order: " + e.getMessage());
        }
    }
}
