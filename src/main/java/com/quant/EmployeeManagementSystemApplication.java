package com.quant;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}
	
	
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/employee/**"))
				.apis(RequestHandlerSelectors.basePackage("com.quant"))
				.build()
				.apiInfo(apiDetails());
	}

	
	private ApiInfo apiDetails() {
		return  new ApiInfo(
				"Employee Management System API",
				"Quantiphi Assignment for Java Developer role",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Akash Yadav", "https://github.com/yakash985", "akash.s.yadav1997@gmail.com"),
				"API License",
				"https://github.com/yakash985",
				Collections.emptyList());
				
	}
}
