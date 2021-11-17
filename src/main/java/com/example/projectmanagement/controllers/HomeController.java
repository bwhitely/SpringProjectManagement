package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.EmployeeRepository;
import com.example.projectmanagement.dao.ProjectRepository;
import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/")
    public String displayHome(Model model){ // Model is used to send/receive data from the View
        // Querying DB for projects
        List<Project> projects = projRepo.findAll();
        // Querying DB for employees
        List<Employee> employees = empRepo.findAll();
        // Add to model
        model.addAttribute("employees", employees);
        model.addAttribute("projects", projects);
        // Return home.html
        return "main/home";
    }
}
