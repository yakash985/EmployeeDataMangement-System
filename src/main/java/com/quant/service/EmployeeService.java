package com.quant.service;

import java.util.List;

import com.quant.entity.Employee;
import com.quant.exception.EmployeeMailAlreadyExists;
import com.quant.exception.EmployeeNotFoundException;

public interface EmployeeService {
	
	Employee getEmployeeByEmployeeId(Long employeeId) throws  EmployeeNotFoundException;
	
	Employee createNewEmployee(Employee employee) throws EmployeeMailAlreadyExists;
	
	Employee updateEmployeeByEmployeeId(Long employeeId,Employee employee ) throws  EmployeeNotFoundException;
	
	void deleteEmployeeByEmployeeId(Long employeeId);	
	
	

}
