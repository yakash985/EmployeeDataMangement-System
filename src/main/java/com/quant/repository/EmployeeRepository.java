package com.quant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quant.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Optional<Employee> findEmployeeByEmail(String email);
	
	
}
