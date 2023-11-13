package com.gl.assignment4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.assignment4.entity.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByIdOrFirstNameContaining(int id,String firstName);
//	Employee findById(int id);
} 