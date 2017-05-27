package com.employee.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
	}
}
