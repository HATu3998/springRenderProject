package com.example.demoSpringRender.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpringRender.model.DiscountCode;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, String> {
}