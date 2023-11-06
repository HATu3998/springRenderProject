package com.example.demoSpringRender.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpringRender.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);
 
}