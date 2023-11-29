package com.training.org;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class EmployeeManagement {
	private static EntityManager em;

//	public static void findEmployeesAboveAverageSalary(EntityManager em) {
//        em.getTransaction().begin();
// 
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
//        Root<Employee> root = query.from(Employee.class);  // Define the root entity
// 
//        // Get the average salary
//        CriteriaQuery<Double> avgQuery = cb.createQuery(Double.class);
//        Root<Employee> avgRoot = avgQuery.from(Employee.class);  // Define the root entity for the average query
//        avgQuery.select(cb.avg(avgRoot.get("salary")));
//        Double averageSalary = em.createQuery(avgQuery).getSingleResult();
// 
//        // Create a query to find employees with a salary greater than the average
//        query.select(root).where(cb.gt(root.get("salary"), averageSalary));
// 
//        List<Employee> employees = em.createQuery(query).getResultList();
//        em.getTransaction().commit();
// 
//        System.out.println("Employees with salary greater than average:");
//        for (Employee employee : employees) {
//            System.out.println("ID " + employee.getEmpid());
//            System.out.println("Salary: " + employee.getSalary());
//        }
//    }      
	
//	   public void findAverageSalary(EntityManager em) {
//	        em.getTransaction().begin();
//	 
//	        CriteriaBuilder cb = em.getCriteriaBuilder();
//	        CriteriaQuery<Double> query = cb.createQuery(Double.class);
//	        Root<Employee> root = query.from(Employee.class);
//	 
//	        query.select(cb.avg(root.get("salary")));
//	 
//	        Double averageSalary = em.createQuery(query).getSingleResult();
//	 
//	        em.getTransaction().commit();
//	 
//	        System.out.println("Average Salary: " + averageSalary);
//	    }
	
	
	public static void main(String[] args) throws ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentPU");
		em = emf.createEntityManager();
		
		// for single record Inserting
		// Operation on new table Employee
		// INSERT
//		em.getTransaction().begin();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		Employee employee = new Employee(1001, "Sahil", "Pune", dateFormat.parse("2023/10/23"), 20000);
//		em.persist(employee);
//		em.getTransaction().commit();
		
		
//		// INSERT multiple records
//		em.getTransaction().begin();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//
//		// Create an array or list of Employee objects
//		Employee[] employees = new Employee[11]; // You can adjust the size as needed
//
//		// Create and populate the Employee objects
//		employees[0] = new Employee(1000, "Sahil", "Pune", dateFormat.parse("2023/10/23"), 20000);
//		employees[1] = new Employee(1001, "Vipul", "New York", dateFormat.parse("2023/11/15"), 25000);
//		employees[2] = new Employee(1002, "Saiem", "Mumbai", dateFormat.parse("2022/09/05"), 22000);
//		employees[3] = new Employee(1003, "Vinay", "Akola", dateFormat.parse("2023/07/05"), 24000);
//		employees[4] = new Employee(1004, "Neha", "Amravati", dateFormat.parse("2019/09/05"), 42000);
//		employees[5] = new Employee(1005, "Mrunali", "Jalgaon", dateFormat.parse("2002/09/05"), 62000);
//		employees[6] = new Employee(1006, "Rupali", "Dhule", dateFormat.parse("2003/09/05"), 27000);
//		employees[7] = new Employee(1007, "Sied", "Aurangabad", dateFormat.parse("2005/09/05"), 47000);
//		employees[8] = new Employee(1008, "Yash", "Nashik", dateFormat.parse("2012/09/05"), 55000);
//		employees[9] = new Employee(1009, "raj", "Nagar", dateFormat.parse("2017/09/05"), 28000);
//		employees[10] = new Employee(1010, "MAdhur", "Kharadi", dateFormat.parse("2015/09/05"), 92000);
//
//		// Persist each Employee object
//		for (Employee employee : employees) {
//		    em.persist(employee);
//		}
//
//		em.getTransaction().commit();

		// Find records whose salary is greater than the average salary of all the employee
//		Employee E1 = em.find(Employee.class, );
//        List<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		
//		Employee[] employees = new Employee[11];
//        for (Employee employee : employees) {
//        	int empid = employee.getEmpid();
//			em.find(Employee.class, empid);
//		}
		
//		
//		while(true) {
//			em.find(Employee.class, );
//			em.
//		}
//		// Calculate the average salary
//        double totalSalary = 0.0;
//        for (Employee employee : employees) {
//            totalSalary += employee.getSalary();
//        }
//        double averageSalary = totalSalary / employees.length;
//
//        // Find employees with salaries greater than the average
//        for (Employee employee : employees) {
//            if (employee.getSalary() > averageSalary) {
//                System.out.println("Employee: " + employee.getName() + ", Salary: " + employee.getSalary());
//            }
//        }
//		
		
		
		

		// Close Connection
		em.close();
		emf.close();
	}

}
