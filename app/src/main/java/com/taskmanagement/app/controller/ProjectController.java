package com.taskmanagement.app.controller;

import com.taskmanagement.app.dto.ProjectDTO;
import com.taskmanagement.app.service.ProjcetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjcetService projcetService;

    public ProjectController(ProjcetService projcetService) {
        this.projcetService = projcetService;
    }


    @PostMapping("/create")
    public ProjectDTO createProject(@RequestBody ProjectDTO dto) {
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


    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable Integer id) {
        return projcetService.getProjectById(id);
    }


    @PutMapping("/update/{projectId}")
    public ProjectDTO updateProjectById(@PathVariable Integer projectId, @RequestBody ProjectDTO dto) {
        return projcetService.updateProject(projectId, dto);
    }


    @DeleteMapping("/delete/{projectId}")
    public ProjectDTO deleteProjectById(@PathVariable Integer projectId) {
        return projcetService.deleteProject(projectId);
    }
}
