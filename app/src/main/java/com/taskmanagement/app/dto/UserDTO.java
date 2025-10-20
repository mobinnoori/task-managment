package com.taskmanagement.app.dto;

import com.taskmanagement.app.enums.JobRole;

import java.util.Set;

public record UserDTO(
        Integer id,
        String name,
        String email,
        String password,
        JobRole role,
        Set<JobDTO> jobs
) {}