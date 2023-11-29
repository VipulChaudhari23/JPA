package com.training.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "marks")
	private int marks;
	
	public Student() {
		System.out.println("Default Constructor is called");
		id = 0;
		firstname = "";
		lastname = "";
		marks = 0;		
	}
	

	
	public Student(int id, String firstname, String lastname, int marks) {
		System.out.println("Parametariazed Constructor is called");
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.marks = marks;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}
