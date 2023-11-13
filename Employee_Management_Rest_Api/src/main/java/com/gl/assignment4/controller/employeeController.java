package com.gl.assignment4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.assignment4.service.employeeService;
import com.gl.assignment4.entity.Employee;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class employeeController {
	
	@Autowired
	employeeService empService;
	
	@GetMapping("/")
	private String HomePage() {
		return "home";
	}
	
	@GetMapping("/employees")
	private String listEmployees(Model model) {
		model.addAttribute("employees",empService.getAllEmployees());
		return "employees";
	}
	
	@GetMapping("/employees/sortedAsc")
	private String listEmployeesSorted(Model model) {
		model.addAttribute("employees",empService.getAllEmployeesSorted());
		return "employees";
	}
	
	@GetMapping("/employees/new")
	private String createEmployees(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee",employee);
		return "create_employees";
	}
	
	@PostMapping("/employees")
	private String saveEmployees(@ModelAttribute("employee") Employee employee ) {
		empService.saveEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/edit/{id}")
	private String editEmployees(@PathVariable int id , Model model) {
		model.addAttribute("employee",empService.getEmployeeByID(id));
		return "edit_employee";
	}
	
	@PostMapping("/employees/{id}")
	private String updateEmployeeById(@PathVariable int id ,@ModelAttribute("employee") Employee employee ,Model model) {
		empService.updateEmployee(id, employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/{id}")
	private String deleteEmployeeById(@PathVariable int id) {
		empService.deleteEmployeeByID(id);
		return "redirect:/employees";
	}
	
	@PostMapping("/employee/search")
    public String searchEmployees(@RequestParam(value = "searchTerm") String searchTerm,Model model) {
        try {
            int id = Integer.parseInt(searchTerm);
            // If the input is a valid integer, search by ID
            
            Employee employee= empService.getEmployeeByID(id);
            model.addAttribute("employees", employee);
            return "searchEmployee";
            
        } catch (NumberFormatException e) {
            // If the input is not a valid integer, search by first name
        	 List<Employee> employee= empService.searchByIdOrFirstName(-1, searchTerm);
             model.addAttribute("employees", employee);
             return "searchEmployee";
        }
    }
	
}

