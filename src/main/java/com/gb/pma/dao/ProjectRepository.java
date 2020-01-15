package com.gb.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gb.pma.dto.ChartData;
import com.gb.pma.entities.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	@Override
	List<Project> findAll();

	@Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value " + "FROM project " + "GROUP BY stage")
	List<ChartData> getProjectStatus();
}
