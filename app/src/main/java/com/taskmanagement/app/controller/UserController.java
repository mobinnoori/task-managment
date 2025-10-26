package com.taskmanagement.app.controller;

import com.taskmanagement.app.dto.UserDTO;
import com.taskmanagement.app.dto.UserStatisticsDTO;
import com.taskmanagement.app.enums.JobRole;
import com.taskmanagement.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }


    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }


    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO dto) {
        return userService.updateUser(id, dto);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }



    @GetMapping("/filter")
    public List<UserStatisticsDTO> filterUsers(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) JobRole role
    ) {
        return userService.filterUsers(userId, name, email, role);
    }

}