package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.EmployeeRepository;
import com.example.projectmanagement.dao.ProjectRepository;
import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects") // root dir for controller
public class ProjectController {

    @Autowired // give this responsibility to the spring container to inject an instance of the ProjectRepository
    ProjectRepository projRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/new")
    public String displayProjectForm(Model model){ // model used to exchange data between view and controller
        Project project = new Project();

        List<Employee> employees = empRepo.findAll();
        model.addAttribute("allEmployees", employees); // maps to <option th:each="employee : ${allEmployees}" in new-projects

        model.addAttribute("project", project); // attributeName maps to th:object value in .html
        return "projects/new-projects";
    }

    @PostMapping(value = "/save")
    public String createProject(Project project, @RequestParam List<Long> employees, Model model){
        projRepo.save(project);

        // Save the project to the Employee - aka. add the FK of proj_id to Employee correctly
        Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);

        for (Employee e : chosenEmployees){
            e.setProject(project);
            empRepo.save(e);
        }

        // redirect to prevent duplicate submissions
        return "redirect:/projects/new";
    }

    @GetMapping("/")
    public String displayProjects(Model model){
        List<Project> projects = projRepo.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }
}
