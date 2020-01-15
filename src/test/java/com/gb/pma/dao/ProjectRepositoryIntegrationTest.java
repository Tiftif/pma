package com.gb.pma.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gb.pma.entities.Project;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectRepositoryIntegrationTest {

	@Autowired
	ProjectRepository proRepo;

	@Test
	public void ifNewProjectSaved_thenSucess() {
		Project newProject = new Project("New Test project", "COMPLETE", "Test description");
		this.proRepo.save(newProject);

		Assert.assertEquals(1, this.proRepo.findAll().size());
	}
}
