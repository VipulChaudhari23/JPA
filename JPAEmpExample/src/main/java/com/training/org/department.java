package com.training.org;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class department {
	@Id
	private int deptid;
	
	private String deptname;
	
	private int department_head;

//	private Employee emp;
	@ElementCollection  
	private List<Employee> employee=new ArrayList<Employee>();
	
	

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public int getDepartment_head() {
		return department_head;
	}

	public void setDepartment_head(int department_head) {
		this.department_head = department_head;
	}	
}
