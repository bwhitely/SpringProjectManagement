package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.EmployeeRepository;
import com.example.projectmanagement.dao.ProjectRepository;
import com.example.projectmanagement.dto.ChartData;
import com.example.projectmanagement.dto.EmployeeProject;
import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

//    @Value("${version}") // from appl.properties
//    String version;
//
//    @Value("${environmentvariableproperty}")
//    String test;

    @Autowired
    ProjectRepository projRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException { // Model is used to send/receive data from the View
        Map<String, Object> map = new HashMap<>();

//        model.addAttribute("versionNumber", version); // from app.props - can pass this to view
//        model.addAttribute("test", test);

        // Querying DB for projects
        List<Project> projects = projRepo.findAll();
        model.addAttribute("projects", projects);

        List<ChartData> projectData = projRepo.getProjectStages();
        // Convert projectData into JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String JSONString = objectMapper.writeValueAsString(projectData); // This is where the JsonProcessingException may come from
        model.addAttribute("projectStatusCount", JSONString);

        // Querying DB for employees
        List<EmployeeProject> empProjs = empRepo.employeeProjects();
        // Add to model
        model.addAttribute("empProjs", empProjs);
        // Return home.html
        return "main/home";
    }
}
