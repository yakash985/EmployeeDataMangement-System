package com.quant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quant.entity.Employee;
import com.quant.exception.EmployeeMailAlreadyExists;
import com.quant.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Employee Management System
 * @author Akash
 *
 */

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	private static final String deleteMessage = "Successfully Deleted";
	@Autowired
	EmployeeService employeeService;
	
		
		/**
		 * Create new Employee
		 * @param employee
		 * @return
		 * @throws EmployeeMailAlreadyExists 
		 */
		@PostMapping(value ="/create",consumes = MediaType.APPLICATION_JSON_VALUE,
				headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
		@ApiOperation(value = "Create Employee details",notes = "Provide details of Employee to create new record",response = Employee.class)
		ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws EmployeeMailAlreadyExists {			
				Employee savedEmp = employeeService.createNewEmployee(employee);
				return 	new ResponseEntity<Employee>(savedEmp, HttpStatus.OK);
		}
		
		
		/**
		 * Get Employee By ID
		 * @param empId
		 * @return
		 */
		@GetMapping(value ="/fetch/{employeeId}",produces=MediaType.APPLICATION_JSON_VALUE)
		@ApiOperation(value = "Finds Employee By id",notes = "Provide id of Employee to get its details",response = Employee.class)
		ResponseEntity<Employee> fetchEmployeeById(@ApiParam( value = "Id value for the employee to be retrieved ",
												required = true, example="123")	@PathVariable("employeeId")Long employeeId) {
				Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
				return 	new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
	
	
	
		/**
		 * Update Employee details
		 * @param empId
		 * @param employee
		 * @return
		 */
		@PutMapping(value ="/update/{employeeId}",consumes = MediaType.APPLICATION_JSON_VALUE,
				headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
		@ApiOperation(value = "Update Employee By id",notes = "Update details of existing employee by its id",response = Employee.class)
		ResponseEntity<Employee> updateEmployeedetailsByEmpId(@ApiParam( value = "Id value of the employee to be updated ", required = true, example="123")
														@PathVariable("employeeId")Long employeeId,	@RequestBody Employee employee){
				Employee updatedEmp =employeeService.updateEmployeeByEmployeeId(employeeId, employee);		
				return new ResponseEntity<Employee>(updatedEmp, HttpStatus.OK);	
		}
		
		/**
		 * Delete Employee By Id
		 * @param employeeId
		 * @return
		 */
		@DeleteMapping(value ="/delete/{employeeId}",produces=MediaType.APPLICATION_JSON_VALUE)
		@ApiOperation(value = "Delete Employee By id",notes = "Provide id of Employee to delete its details",response = String.class)
		ResponseEntity<String> deleteEmployeeById(@ApiParam( value = "Id value for the employee to be retrieved ",
												required = true, example="123")	@PathVariable("employeeId")Long employeeId) {
			employeeService.deleteEmployeeByEmployeeId(employeeId);
				return 	new ResponseEntity<String>(deleteMessage, HttpStatus.OK);
		}
		
	
	
	
	
}
