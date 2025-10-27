package com.taskmanagement.app.mapper;

import com.taskmanagement.app.dto.ProjectDTO;
import com.taskmanagement.app.model.Project;
import com.taskmanagement.app.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {JobMapper.class}, builder = @org.mapstruct.Builder(disableBuilder = true))
public interface ProjectMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "memberIds", expression = "java(mapMembersToIds(project.getMembers()))")
    @Mapping(target = "jobs", source = "jobs")
    ProjectDTO toDTO(Project project);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(target = "jobs", ignore = true)
    Project toEntity(ProjectDTO dto);

    default Set<Integer> mapMembersToIds(Set<User> members) {
        return members == null ? Set.of() :
                members.stream().map(User::getId).collect(Collectors.toSet());
    }
}