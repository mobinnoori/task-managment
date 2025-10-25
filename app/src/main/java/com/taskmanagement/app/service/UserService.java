package com.taskmanagement.app.service;

import com.taskmanagement.app.dto.UserDTO;
import com.taskmanagement.app.dto.UserStatisticsDTO;
import com.taskmanagement.app.enums.JobRole;
import com.taskmanagement.app.exception.UserNotFoundException;
import com.taskmanagement.app.mapper.UserMapper;
import com.taskmanagement.app.model.User;
import com.taskmanagement.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDTO createUser(UserDTO dto) {
        User user = userMapper.toEntityWithoutJobs(dto);
        userRepository.save(user);
        return dto;
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAllWithJobs().stream()
                .map(userMapper::toDTO)
                .toList();
    }


    public UserDTO getUserById(Integer id) {
        User user = userRepository.findByIdWithJobs(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toDTO(user);
    }


    public UserDTO updateUser(Integer id, UserDTO dto) {
        User existing = userRepository.findByIdWithJobs(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existing.setName(dto.name());
        existing.setEmail(dto.email());
        existing.setPassword(dto.password());
        existing.setRole(dto.role());

        return userMapper.toDTO(userRepository.save(existing));
    }


    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }


    public List<UserDTO> filterUsers(Integer id, String name, String email, JobRole role) {
        return userRepository.filterUsers(id, name, email, role)
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }
}