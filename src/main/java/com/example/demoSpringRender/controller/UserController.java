package com.example.demoSpringRender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demoSpringRender.model.User;
import com.example.demoSpringRender.repo.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findById(username).orElse(null);
    }

    // Các phương thức thêm, sửa và xóa người dùng
}
