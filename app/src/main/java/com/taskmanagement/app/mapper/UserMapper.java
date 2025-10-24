package com.taskmanagement.app.mapper;

import com.taskmanagement.app.dto.UserDTO;
import com.taskmanagement.app.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {JobMapper.class})
public interface UserMapper {

    @Mapping(target = "jobs", source = "jobs")
    UserDTO toDTO(User user);

    @Mapping(target = "jobs", ignore = true)
    User toEntityWithoutJobs(UserDTO dto);

    User toEntity(UserDTO dto);
}