package com.quant.controller;


import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quant.entity.Employee;
import com.quant.exception.EmployeeMailAlreadyExists;
import com.quant.exception.EmployeeNotFoundException;
import com.quant.service.EmployeeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

	
	@Autowired
    private WebApplicationContext wac;	
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateEmployee() throws Exception {
		Employee actualEmployee = buildTestEmployee();
		when(employeeService.createNewEmployee(any(Employee.class))).thenReturn(buildTestEmployee());

		
		String employeeAsString = objectMapper.writeValueAsString(actualEmployee);
		mockMvc.perform(post("/employee/create")
										.contentType(MediaType.APPLICATION_JSON).content(employeeAsString))
										.andExpect(status().isOk())
										.andExpect(jsonPath("$.employeeId").value(1l));
	}

	@Test
	void shouldFetchEmployeeByEmployeeId() throws Exception {
		
		when(employeeService.getEmployeeByEmployeeId(any())).thenReturn(buildTestEmployee());		
		mockMvc.perform(get("/employee/fetch/1").contentType(MediaType.APPLICATION_JSON))
											.andExpect(status().isOk())
											.andExpect(jsonPath("$.employeeId").value(1l))
											.andExpect(jsonPath("$.employeeName").value("Akash"))
											.andExpect(jsonPath("$.employeeCity").value("Mumbai"))
											.andExpect(jsonPath("$.employeePhoneNo").value("9768919959"))
											.andExpect(jsonPath("$.email").value("akash@gmail.com"));			
	}

	
	@Test
	void shouldNotFetchEmployeeForInValidEmployeeId() throws Exception {
		
		when(employeeService.getEmployeeByEmployeeId(any())).thenThrow(EmployeeNotFoundException.class);		
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/fetch/1").contentType(MediaType.APPLICATION_JSON))
											.andExpect(status().isNotFound());			
	}

	
	

	private Employee buildTestEmployee() {
		return new Employee(1l, "Akash", "Mumbai", "9768919959", "akash@gmail.com");
	}
}
