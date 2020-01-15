package com.gb.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.pma.dao.ProjectRepository;
import com.gb.pma.dto.ChartData;
import com.gb.pma.entities.Project;

@Service
public class ProjectService {
	@Autowired
	ProjectRepository proRepo;

	public Project save(Project project) {
		return this.proRepo.save(project);
	}

	public List<Project> getAll() {
		return this.proRepo.findAll();
	}

	public List<ChartData> getProjectStatus() {
		return this.proRepo.getProjectStatus();
	}

}
