package com.taskmanagement.app.dto;

import com.taskmanagement.app.enums.TaskStatus;

import java.util.Set;

public record JobDTO(
        Integer id,
        String title,
        String description,
        TaskStatus status,
        Integer userId,
        Integer parentJobId,
        Set<JobDTO> subJobs
) {}