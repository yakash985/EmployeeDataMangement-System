package com.quant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.quant.entity.Employee;
import com.quant.exception.EmployeeMailAlreadyExists;
import com.quant.exception.EmployeeNotFoundException;
import com.quant.repository.EmployeeRepository;


@SpringBootTest
class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService = new EmployeeServiceImpl();

	@Test
	void shouldGetEmployeeByEmployeeId() {
		Employee expectedEmployee = buildTestEmployee();
		when(employeeRepository.findById(any())).thenReturn( Optional.ofNullable(buildTestEmployee()));
		Employee actualEmployee = employeeService.getEmployeeByEmployeeId(10001l);
		assertEquals(expectedEmployee.getEmployeeId(), actualEmployee.getEmployeeId());
		assertEquals(expectedEmployee.getEmployeeName(),actualEmployee.getEmployeeName() );
		assertEquals(expectedEmployee.getEmployeeCity(), actualEmployee.getEmployeeCity());
		assertEquals(expectedEmployee.getEmployeePhoneNo(), actualEmployee.getEmployeePhoneNo());
		assertEquals(expectedEmployee.getEmail(),actualEmployee.getEmail());
	}

	@Test
	void shouldNotGetEmployeeForInvalidEmployeeId() {
		when(employeeRepository.findById(any())).thenThrow(EmployeeNotFoundException.class);		
		assertThrows(EmployeeNotFoundException.class, ()->employeeService.getEmployeeByEmployeeId(10001l));
	}
	

	@Test
	void shouldCreateNewEmployee() throws EmployeeMailAlreadyExists {
		Employee expectedEmployee = buildTestEmployee();
		when(employeeRepository.findEmployeeByEmail(any())).thenReturn( Optional.empty());
		when(employeeRepository.save(any())).thenReturn(buildTestEmployee());
		Employee actualEmployee = employeeService.createNewEmployee(buildTestEmployee());
		assertEquals(expectedEmployee.getEmployeeId(), actualEmployee.getEmployeeId());
		assertEquals(expectedEmployee.getEmployeeName(),actualEmployee.getEmployeeName() );
		assertEquals(expectedEmployee.getEmployeeCity(), actualEmployee.getEmployeeCity());
		assertEquals(expectedEmployee.getEmployeePhoneNo(), actualEmployee.getEmployeePhoneNo());
		assertEquals(expectedEmployee.getEmail(),actualEmployee.getEmail());
		
	}
	
	@Test
	void shouldNotCreateNewEmployeeForAlreadyExistingMail() {
		when(employeeRepository.findEmployeeByEmail(any())).thenReturn(Optional.ofNullable(buildTestEmployee()));		
		assertThrows(EmployeeMailAlreadyExists.class, ()->employeeService.createNewEmployee(buildTestEmployee()));
	}

	@Test
	void shouldUpdateEmployeeDetailByEmployeeId() {
		Employee expectedEmployee = buildTestEmployee();
		when(employeeRepository.findById(any())).thenReturn( Optional.ofNullable(expectedEmployee));
		when(employeeRepository.save(any())).thenReturn(expectedEmployee);
		Employee actualEmployee = employeeService.updateEmployeeByEmployeeId(expectedEmployee.getEmployeeId(), expectedEmployee);
		assertEquals(expectedEmployee.getEmployeeId(), actualEmployee.getEmployeeId());
		assertEquals(expectedEmployee.getEmployeeName(),actualEmployee.getEmployeeName() );
		assertEquals(expectedEmployee.getEmployeeCity(), actualEmployee.getEmployeeCity());
		assertEquals(expectedEmployee.getEmployeePhoneNo(), actualEmployee.getEmployeePhoneNo());
		assertEquals(expectedEmployee.getEmail(),actualEmployee.getEmail());
	}
	
	@Test
	void shouldNotUpdateEmployeeForInvalidEmployeeId() {
		Employee actualEmployee = buildTestEmployee();
		when(employeeRepository.findById(any())).thenThrow(EmployeeNotFoundException.class);		
		assertThrows(EmployeeNotFoundException.class, ()->
													employeeService.updateEmployeeByEmployeeId(actualEmployee.getEmployeeId(), actualEmployee));
	}
	
	@Test
	void shouldDeleteEmployeeDetailByEmployeeId() {
		when(employeeRepository.findById(any())).thenReturn( Optional.ofNullable(buildTestEmployee()));
		doNothing().when(employeeRepository).delete(any());		
		employeeService.deleteEmployeeByEmployeeId(1l);
		verify(employeeRepository,times(1)).delete(any());
		
	}
	
	@Test
	void shouldNotDeleteEmployeeDetailForInvalidEmployeeId() {
		when(employeeRepository.findById(any())).thenThrow(EmployeeNotFoundException.class);		
		assertThrows(EmployeeNotFoundException.class, ()->employeeService.deleteEmployeeByEmployeeId(10001l));
	}

	
	private Employee buildTestEmployee() {
		return new Employee(10001l, "Akash", "Mumbai", "9768919959", "akash@gmail.com");
	}
}
