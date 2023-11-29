package com.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "department")
	private String department;

	@Column(name = "balance")
	private double balance;

	public Customer() {
	}

	public Customer(int id, String name, String department, double balance) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.balance = balance;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public double getBalance() {
		return balance;
	}

	public void setId(int id) {
		// Custom validation for ID length
		try {
			if (String.valueOf(id).length() <= 5) {
				this.id = id;
			} 
//			else {
//				throw new IllegalArgumentException("ID length must be at most 5 characters");
//			}
		} catch (IllegalArgumentException e) {
			System.out.println("ID length must be at most 5 characters");
			// TODO: handle exception
		}
		
	}

	public void setName(String name) {
		// Custom validation for Name length
		try {
			if (name.length() <= 50) {
				this.name = name;
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Name length must be at most 50 characters");
			// TODO: handle exception
		}
//		 else {
//			throw new IllegalArgumentException("Name length must be at most 50 characters");
//		}
	}

	public void setDepartment(String department) {
		// Custom validation for Department length
		try {
			if (department.length() <= 10) {
				this.department = department;
			}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println("Department length must be at most 10 characters");
		}
//		 else {
//			throw new IllegalArgumentException("Department length must be at most 10 characters");
//		}
	}

	public void setBalance(double balance) {
		// Custom validation for Balance format
		// Assuming balance should have at most 15 digits with 2 decimal places
		String balanceString = String.valueOf(balance);
		int indexOfDecimal = balanceString.indexOf('.');
		if (indexOfDecimal != -1 && balanceString.length() - indexOfDecimal - 1 <= 2 && balanceString.length() <= 17) {
			this.balance = balance;
		} else {
			throw new IllegalArgumentException("Balance must have at most 15 digits with 2 decimal places");
		}
	}

	@Override
	public String toString() {
		return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", department='" + department + '\'' + ", balance="
				+ balance + '}';
	}

}
