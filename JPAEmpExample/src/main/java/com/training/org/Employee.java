package com.training.org;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  
@Embeddable
@Entity
public class Employee {  

	@Id  
	private int e_id;  
	private String e_name;  

	@ElementCollection  
	private List<Address> address=new ArrayList<Address>();  

	private int dept_id;
	
//	private department dept;
	
	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

//	public department getDept() {
//		return dept;
//	}
//
//	public void setDept(department dept) {
//		this.dept = dept;
//	}



	public int getE_id() {  
		return e_id;  
	}  

	public void setE_id(int e_id) {  
		this.e_id = e_id;  
	}  

	public String getE_name() {  
		return e_name;  
	}  

	public void setE_name(String e_name) {  
		this.e_name = e_name;  
	}  

	public List<Address> getAddress() {  
		return address;  
	}  

	public void setAddress(List<Address> address) {  
		this.address = address;  
	}  


}  