package org.ssn.strategic.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="strategic_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Long id;
	
	@Column(name = "firstname")
	private String eFname;
	
	@Column(name = "lastname")
	private String eLname;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "gender")
	private String gender;
	
	protected Employee(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String geteFname() {
		return eFname;
	}

	public void seteFname(String eFname) {
		this.eFname = eFname;
	}

	public String geteLname() {
		return eLname;
	}

	public void seteLname(String eLname) {
		this.eLname = eLname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Employee( String eFname, String eLname, int age, String gender) {
		
		this.eFname = eFname;
		this.eLname = eLname;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", eFname=" + eFname + ", eLname=" + eLname + ", age=" + age + ", gender=" + gender
				+ "]";
	}
	
	
	
}
