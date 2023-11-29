package com.training.entity;


import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ICustomerService customerService = new CustomerServiceImpl();


        // Inserting customers with user-defined values
//        Customer customer1 = createCustomerFromUserInput();
//        customerService.insertCustomer(customer1);
//
//        Customer customer2 = createCustomerFromUserInput();
//        customerService.insertCustomer(customer2);

//        // Fetching all customers sorted by ID
//        Set<Customer> allCustomers = customerService.findAllCustomersSortedById();
//        System.out.println("All Customers:");
//        allCustomers.forEach(System.out::println);

        // Fetching a specific customer by ID using Optional
        Scanner input = new Scanner(System.in);
        System.out.print("Enter customer ID to get details: ");
        int id = input.nextInt();
        int customerIdToFind = id; // You can modify this to take user input
        Optional<Customer> foundCustomer = customerService.findCustomerById(customerIdToFind);

        if (foundCustomer.isPresent()) {
            System.out.println("Found Customer: " + foundCustomer.get());
        } else {
            System.out.println("Customer not found for ID: " + customerIdToFind);
        }
        
        
//        Set<Customer> foundCustomers = customerService.findCustomerById(customerIdToFind);
//
//        if (!foundCustomers.isEmpty()) {
//            System.out.println("Found Customers:");
//            foundCustomers.forEach(System.out::println);
//        } else {
//            System.out.println("Customer not found for ID: " + customerIdToFind);
//        }
    }

    private static Customer createCustomerFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer department: ");
        String department = scanner.nextLine();

        System.out.print("Enter customer balance: ");
        double balance = scanner.nextDouble();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setDepartment(department);
        customer.setBalance(balance);

        return customer;
    }
}
