package com.taskmanagement.app.mapper;

import com.taskmanagement.app.dto.JobDTO;
import com.taskmanagement.app.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface JobMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "parentJobId", source = "parentJob.id")
    @Mapping(target = "subJobs", qualifiedByName = "mapSubJobs")
    JobDTO toDTO(Job job);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "parentJob", ignore = true)
    @Mapping(target = "subJobs", ignore = true)
    Job toEntity(JobDTO dto);

    @Named("mapSubJobs")
    default Set<JobDTO> mapSubJobs(Set<Job> subJobs) {
        if (subJobs == null) return new HashSet<>();
        return subJobs.stream()
                .map(subJob -> new JobDTO(
                        subJob.getId(),
                        subJob.getTitle(),
                        subJob.getDescription(),
                        subJob.getStatus(),
                        subJob.getUser() != null ? subJob.getUser().getId() : null,
                        subJob.getParentJob() != null ? subJob.getParentJob().getId() : null,
                        null
                ))
                .collect(Collectors.toSet());
    }
}