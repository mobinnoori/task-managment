package com.taskmanagement.app.controller;

import com.taskmanagement.app.dto.UserDTO;
import com.taskmanagement.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO dto) {
        return service.createUser(dto);
    }


    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }


    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Integer id) {
        return service.getUserById(id);
    }


    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO dto) {
        return service.updateUser(id, dto);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
    }
}