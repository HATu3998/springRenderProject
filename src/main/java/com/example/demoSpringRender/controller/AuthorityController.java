package com.example.demoSpringRender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demoSpringRender.model.Authority;
import com.example.demoSpringRender.repo.AuthorityRepository;

import java.util.List;

@RestController
@RequestMapping("/api/authorities")
public class AuthorityController {
    @Autowired
    private AuthorityRepository authorityRepository;

    @GetMapping
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @GetMapping("/{username}")
    public List<Authority> getAuthoritiesByUsername(@PathVariable String username) {
        return authorityRepository.findAllByUsername(username);
    }

    // Các phương thức thêm, sửa và xóa quyền
}
