package com.taskmanagement.app.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record ProjectDTO(
   Integer id,
   String name,
   String description,
   LocalDateTime createdAt,
   Integer ownerId,
   Set<Integer> memberIds,
   Set<JobDTO> jobs
) {}
