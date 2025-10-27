package com.taskmanagement.app.dto;

import com.taskmanagement.app.enums.TaskStatus;

public record JobStatisticsDTO(
        Integer jobId,
        String title,
        TaskStatus status,
        Integer userId,
        String userName,
        int totalSubJobs
) {}
