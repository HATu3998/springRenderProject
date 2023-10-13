package com.example.demoSpringRender.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    private String username;
    private String authority;
	public Authority(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}
	public Authority() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

    // Getters và setters
}