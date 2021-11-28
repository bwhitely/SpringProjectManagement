package com.example.projectmanagement.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // important to define this as an Entity - indicates it will be saved into a database
public class Project {
    @Id // indicates that this value will be the id/primary key
    @SequenceGenerator(name = "project_seq", sequenceName = "PROJECT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq") // indicates that we want this value to be auto generated
    private long projectId;

    private String name;
    private String stage; // to categorize a project - NOT_STARTED, COMPLETED, IN_PROGRESS
    private String description;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY) // define this "project" property in the Employee entity
    @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"),
                                    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    public Project(String name, String stage, String description) {
        super();
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public Project(){}

    public void addEmployee(Employee employee){
        if (employees == null) employees = new ArrayList<>();
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
