package com.gb.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gb.pma.entities.Employee;
import com.gb.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeRepo;

	@GetMapping
	public String displayEmployeeList(Model model) {
		List<Employee> employees = this.employeeRepo.getAll();
		model.addAttribute("employeesList", employees);
		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee aEmployee = new Employee();
		model.addAttribute("employee", aEmployee);
		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		this.employeeRepo.save(employee);
		return "redirect:/employees/new";
	}

}
