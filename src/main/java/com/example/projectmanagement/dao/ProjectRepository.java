package com.example.projectmanagement.dao;

import com.example.projectmanagement.dto.ChartData;
import com.example.projectmanagement.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    public List<Project> findAll();

    // Count how many projects are in NOTSTARTED, COMPLETED and INPROGRESS
    @Query(nativeQuery = true, value = "SELECT STAGE as label, count(*) as value " +
            "FROM project " +
            "GROUP BY stage")
    public List<ChartData> getProjectStages();
}
