package com.gb.pma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.pma.dto.ChartData;
import com.gb.pma.dto.EmployeeProject;
import com.gb.pma.entities.Project;
import com.gb.pma.services.EmployeeService;
import com.gb.pma.services.ProjectService;

@Controller
public class HomeController {

	@Value("${version}")
	private String ver;

	@Autowired
	ProjectService proRepo;
	@Autowired
	EmployeeService employeeRepo;

	@GetMapping("/") // la route demandée
	public String displayHome(Model model) throws JsonProcessingException // model est uiliser pour recevoir et envoyer
																			// des data a la vue
	{
		model.addAttribute("versionNumber", this.ver);
		Map<String, Object> map = new HashMap<>();

		List<Project> projects = this.proRepo.getAll();
		model.addAttribute("projectsList", projects);

		List<ChartData> projectData = this.proRepo.getProjectStatus();

		// convert object to jsn pour l'utiliser en js
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		model.addAttribute("projectStatusCtn", jsonString);

//		List<Employee> employees = this.employeeRepo.findAll();
		List<EmployeeProject> employeesProjectCnt = this.employeeRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCtn", employeesProjectCnt);
		return "main/home"; // retourne template
	}
}
