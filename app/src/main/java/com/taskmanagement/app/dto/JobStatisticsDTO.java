package com.taskmanagement.app.dto;

import com.taskmanagement.app.enums.TaskStatus;

import java.util.Set;

public record JobStatisticsDTO(
        Integer jobId,
        String title,
        TaskStatus status,
        Integer userId,
        String userName,
        int totalSubJobs,
        int completedSubJobs,
        int inProgressSubJobs,
        int pendingSubJobs,
        int doneSubJobs,
        int canceledSubJobs,
        int todoSubJobs,
        int failedSubJobs,
        Set<JobStatisticsDTO> subJobs
) {}
