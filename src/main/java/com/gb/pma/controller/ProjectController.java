package com.gb.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gb.pma.entities.Employee;
import com.gb.pma.entities.Project;
import com.gb.pma.services.EmployeeService;
import com.gb.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService proRepo;

	@Autowired
	EmployeeService empRepo;

	@GetMapping // la route demandée
	public String displayProjectsList(Model model) {
		List<Project> projects = this.proRepo.getAll();
		model.addAttribute("projectsList", projects);
		return "projects/list-projects"; // retourne template
	}

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		List<Employee> employees = this.empRepo.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, Model model) {

		this.proRepo.save(project);

//		loop en cas de ontetomany
//		@RequestParam List<Long> employees,
//		Iterable<Employee> chosenEmployees = this.empRepo.findAllById(employees);
//
//		for (Employee emp : chosenEmployees) {
//			emp.setProject(project);
//			this.empRepo.save(emp);
//		}

		// utilser le redirect pour eviter les dupplication des submissions
		return "redirect:/projects";
	}

}
