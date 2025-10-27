package com.taskmanagement.app.controller;

import com.taskmanagement.app.dto.ProjectDTO;
import com.taskmanagement.app.service.ProjcetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class ProjectController {

    private final ProjcetService projcetService;

    public ProjectController(ProjcetService projcetService) {
        this.projcetService = projcetService;
    }

    @PostMapping("/create")
    public ProjectDTO createProject(ProjectDTO dto) {
        return projcetService.createProject(dto);
    }

    @PostMapping("/{projectId}/add-member/{userId}")
    public void addMember(@PathVariable Integer projectId, @PathVariable Integer userId) {
        projcetService.addMember(projectId, userId);
    }

    @GetMapping("/all")
    public List<ProjectDTO> getAllProjects() {
        return projcetService.findAllProjects();
    }


    public ProjectDTO getProjectById(@PathVariable Integer id) {
        return projcetService.getProjectById(id);
    }
}
