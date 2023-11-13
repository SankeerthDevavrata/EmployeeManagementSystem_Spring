package com.gl.assignment4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.gl.assignment4.entity.Employee;
import com.gl.assignment4.repository.EmployeeRepository;


@Service
public class employeeService implements employeeServiceInterface{
	
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployeesSorted() {
		 Sort sortByFirstNameAsc = Sort.by(Sort.Order.asc("firstName"));
		return employeeRepository.findAll(sortByFirstNameAsc);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeByID(int id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public Employee updateEmployee(int id,Employee employee) {
		Employee emp = getEmployeeByID(id);
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		return saveEmployee(employee);
	}

	@Override
	public void deleteEmployeeByID(int id) {
		employeeRepository.deleteById(id);
	}
	
	public List<Employee> searchByIdOrFirstName(int id,String firstName){
		return employeeRepository.findByIdOrFirstNameContaining(id, firstName);
	}

}
