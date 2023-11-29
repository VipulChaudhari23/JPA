package com.training.org;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentManagement {
	
	private static EntityManager em;
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentPU");
		em = emf.createEntityManager();
		
		// INSERT 
//		em.getTransaction().begin();
//		Student student = new Student(106, "Neha", "Pai", 95);
//		em.persist(student);
//		em.getTransaction().commit();
		
		// SEARCH
		Student s = em.find(Student.class, 101);
		
		if(s != null) {
		System.out.println("Student First Name: " + s.getFirstname());
		System.out.println("Student Id: " + s.getId());
		System.out.println("Student Last Name: " + s.getLastname());
		System.out.println("Students Marks: " + s.getMarks());
		}else {
			System.out.println("Record is not present in the database");
		}
		
		// UPDATE
		em.getTransaction().begin();
			Student s1 = em.find(Student.class, 102);
			System.out.println("Student First Name: " + s.getFirstname());
			System.out.println("Student Id: " + s.getId());
			System.out.println("Student Last Name: " + s.getLastname());
			System.out.println("Students Marks: " + s.getMarks());
			
			s.setMarks(100);
			
			System.out.println("\n\nAfter Updation");
			System.out.println("Student First Name: " + s.getFirstname());
			System.out.println("Student Id: " + s.getId());
			System.out.println("Student Last Name: " + s.getLastname());
			System.out.println("Students Marks: " + s.getMarks());
		em.getTransaction().commit();
		
		// DELETE
		em.getTransaction().begin();
		Student s2 = em.find(Student.class, 106);
		System.out.println(s2);
		em.remove(s2);
		em.getTransaction().commit();

		
		
		
		// Close Connection
		em.close();
		emf.close();
	}
}
	