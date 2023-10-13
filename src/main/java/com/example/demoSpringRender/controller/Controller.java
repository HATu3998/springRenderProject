package com.example.demoSpringRender.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoSpringRender.model.Product;
 

import org.springframework.ui.Model;


@org.springframework.stereotype.Controller
public class Controller {
 

	@GetMapping("/")
	public String index(Model model ,Principal principal) {
		
		return "index";
	}
	@GetMapping("/showLoginPage")
	public String login() {
		return "login";
	}
	
	@GetMapping("/leaders")
	public String showLeader() {
		return "leaders";
	}
	
	@GetMapping("/errorLogin")
	public String errorLogin() {
		return "/errorLogin";
	}
}
