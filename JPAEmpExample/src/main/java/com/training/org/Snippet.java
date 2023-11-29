package com.training.org;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Snippet {
	private static EntityManager em;
	public static void main(String[] args) {


		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentPU");
		em = emf.createEntityManager();


		em.getTransaction().begin();

//		Address a1=new Address();  
//		a1.setE_pincode(201301);  
//		a1.setE_city("Pune");  
//		a1.setE_state("Maharashtra");  
////
////
////
////
////
//		Address a2=new Address();  
//		a2.setE_pincode(302001);  
//		a2.setE_city("Bangalore");  
//		a2.setE_state("Karanataka");  
//
//
		
		Address a3=new Address();  
		a3.setE_pincode(412207);  
		a3.setE_city("Pune");  
		a3.setE_state("Maharashtra"); 
		
//
//		Employee e1=new Employee();  
//		e1.setE_id(1);  
//		e1.setE_name("Vijay");  
//		e1.getAddress().add(a1);  
////
//		Employee e2=new Employee();  
//		e2.setE_id(2);  
//		e2.setE_name("John");  
//		e2.getAddress().add(a2);  

		Employee e3=new Employee();  
		e3.setE_id(3);  
		e3.setE_name("Vipul");  
		e3.getAddress().add(a3);  
		e3.setDept_id(1);
		
		department d1 = new department();
		d1.setDeptid(1);
		d1.setDeptname("IT Department");
		d1.setDepartment_head(1);
		d1.getEmployee().add(e3);
		
//		em.persist(e1);  
//		em.persist(e2);  
//		em.persist(e3);  
		em.persist(d1);  

		em.getTransaction().commit();  

		em.close();  
		emf.close();  

	}
}