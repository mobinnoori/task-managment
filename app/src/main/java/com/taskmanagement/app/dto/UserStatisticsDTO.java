package com.taskmanagement.app.dto;

public record UserStatisticsDTO(
        Integer userId,
        String userName,
        long totalJobs,
        long completedJobs,
        long inProgressJobs,
        long pendingJobs
) {}
