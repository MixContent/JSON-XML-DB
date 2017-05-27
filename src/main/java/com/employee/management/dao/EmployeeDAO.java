package com.employee.management.dao;

import java.util.List;

import com.employee.management.model.Employee;

public interface EmployeeDAO {
	void saveEmployee(Employee employee);

	public List<Employee> employeeList();

	public Employee getEmployee(int id);

	public String updatePassword(int id, String password, String confirmedPassword);

	public String deleteEmployee(int id);
}
