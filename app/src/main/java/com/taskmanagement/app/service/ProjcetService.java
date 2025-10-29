package com.taskmanagement.app.service;

import com.taskmanagement.app.dto.ProjectDTO;
import com.taskmanagement.app.exception.ProjectNotFoundException;
import com.taskmanagement.app.exception.UserNotFoundException;
import com.taskmanagement.app.mapper.ProjectMapper;
import com.taskmanagement.app.model.Project;
import com.taskmanagement.app.model.User;
import com.taskmanagement.app.repository.ProjectRepository;
import com.taskmanagement.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjcetService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projcetMapper;

    public ProjcetService(ProjectRepository projectRepository, UserRepository userRepository, ProjectMapper projcetMapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projcetMapper = projcetMapper;
    }



    public ProjectDTO createProject(ProjectDTO dto) {
        Project project = projcetMapper.toEntity(dto);
        User owner = userRepository.findById(dto.ownerId())
                .orElseThrow(() -> new UserNotFoundException(dto.ownerId()));

        project.setOwner(owner);
        project.getMembers().add(owner);

        Project saved = projectRepository.save(project);
        return projcetMapper.toDTO(saved);
    }


    public void addMember(Integer projectId, Integer userId) {
        Project project = projectRepository.findByIdWithDetails(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        project.getMembers().add(user);
        projectRepository.save(project);
    }


    public List<ProjectDTO> findAllProjects() {
        return projectRepository.findAll()
                .stream().map(projcetMapper::toDTO).toList();
    }


    public ProjectDTO getProjectById(Integer id) {
        Project project = projectRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
        return projcetMapper.toDTO(project);
    }


    public ProjectDTO updateProject(Integer projectId, ProjectDTO dto) {
        Project project = projectRepository.findByIdWithDetails(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        project.setName(dto.name());
        project.setDescription(dto.description());

        User owner = userRepository.findById(dto.ownerId())
                        .orElseThrow(() -> new UserNotFoundException(dto.ownerId()));
        project.setOwner(owner);

        projectRepository.save(project);
        return projcetMapper.toDTO(project);
    }


    public ProjectDTO deleteProject(Integer projectId) {
        Project project =  projectRepository.findByIdWithDetails(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        projectRepository.delete(project);
        return projcetMapper.toDTO(project);
    }
}