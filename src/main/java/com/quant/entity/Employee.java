package com.quant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "Employee")
@ApiModel(description = "Details About Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="employee_id")
	@ApiModelProperty(notes = "Unqiue id of employee",readOnly = true)
	private Long employeeId;
	
	@Column(name="employee_name")
	@ApiModelProperty(notes = "Employee Name ")
	private String employeeName;
	
	@Column(name="employee_city")
	@ApiModelProperty(notes = "employee location")
	private String employeeCity;
	
	@Column(name="phone_no")
	@ApiModelProperty(notes = "employee phone number")
	private String employeePhoneNo;
	
	@Column(name="email", unique = true)
	@ApiModelProperty(notes = "Employee mail id")
	private String email;

	public Employee() {
		super();
	}

	public Employee(Long employeeId, String employeeName, String employeeCity, String employeePhoneNo, String email) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeCity = employeeCity;
		this.employeePhoneNo = employeePhoneNo;
		this.email = email;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCity() {
		return employeeCity;
	}

	public void setEmployeeCity(String employeeCity) {
		this.employeeCity = employeeCity;
	}

	public String getEmployeePhoneNo() {
		return employeePhoneNo;
	}

	public void setEmployeePhoneNo(String employeePhoneNo) {
		this.employeePhoneNo = employeePhoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
