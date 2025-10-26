package com.taskmanagement.app.dto;

import com.taskmanagement.app.enums.JobRole;

public record UserStatisticsDTO(
        Integer userId,
        String name,
        String  email,
        JobRole role,
        int totalJobs
) {}
