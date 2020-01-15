package com.gb.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.pma.dao.EmployeeRepository;
import com.gb.pma.dto.EmployeeProject;
import com.gb.pma.entities.Employee;

@Service
public class EmployeeService {
	// autowired injection
	@Autowired
	EmployeeRepository empRepo;

	// constructor injection
//	@Autowired
//	public EmployeeService(EmployeeRepository empRepo) {
//		 this.empRepo = empRepo;
//	}

	// Setter injection
//	public void setEmpRepo(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}

	public Employee save(Employee employee) {
		return this.empRepo.save(employee);
	}

	public List<Employee> getAll() {
		return this.empRepo.findAll();
	}

	public List<EmployeeProject> employeeProjects() {
		return this.empRepo.employeeProjects();
	}

}
