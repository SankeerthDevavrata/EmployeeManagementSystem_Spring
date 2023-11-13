package com.gl.assignment4.service;

import java.util.List;

import com.gl.assignment4.entity.Employee;

public interface employeeServiceInterface {
	
	List<Employee> getAllEmployees();
	
	Employee saveEmployee(Employee employee);
	
	Employee getEmployeeByID(int id);
	
	Employee updateEmployee(int id,Employee employee);
	
	void deleteEmployeeByID(int id);
}
