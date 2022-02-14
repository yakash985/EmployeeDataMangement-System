package com.quant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quant.entity.Employee;
import com.quant.exception.EmployeeMailAlreadyExists;
import com.quant.exception.EmployeeNotFoundException;
import com.quant.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	@Autowired
	EmployeeRepository employeeRepository;

		@Override
		public Employee getEmployeeByEmployeeId(Long employeeId) throws  EmployeeNotFoundException {
		
			Optional<Employee> employee = employeeRepository.findById(employeeId);
			return employee.orElseThrow(()->
							new EmployeeNotFoundException("Employee id "+employeeId+" does not exist "));
		}

	
		
		
		@Override
		public Employee createNewEmployee(Employee employee) throws EmployeeMailAlreadyExists {
			Optional<Employee> empOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
			if(empOptional.isPresent())
				throw new EmployeeMailAlreadyExists(employee.getEmail()+" already exist, please create new Employee record with new mail");
			return employeeRepository.save(employee);
		}
		
		

		@Override
		public Employee updateEmployeeByEmployeeId(Long employeeId, Employee employee) throws  EmployeeNotFoundException{
	
			Employee updateEmployee = getEmployeeByEmployeeId(employeeId) ;
			updateEmployee.setEmployeeName(employee.getEmployeeName());
			updateEmployee.setEmployeeCity(employee.getEmployeeCity());
			updateEmployee.setEmployeePhoneNo(employee.getEmployeePhoneNo());
			updateEmployee.setEmail(employee.getEmail());
			return employeeRepository.save(updateEmployee);		
		}
		
		

		@Override
		public void deleteEmployeeByEmployeeId(Long employeeId) {
			Employee deleteEmployee = employeeRepository.findById(employeeId)
										.orElseThrow(()-> new EmployeeNotFoundException("Employee id "+employeeId+" does not exist ")) ;
			employeeRepository.delete(deleteEmployee);
		}

	

}
