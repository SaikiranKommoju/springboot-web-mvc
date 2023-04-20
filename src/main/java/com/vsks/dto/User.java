package com.vsks.dto;

import java.util.List;

public class User {

    private Long id;
    private String name;
    private String emailId;
    private String role;
    private List<String> authorities;
    private String password;
    private boolean active;

    public User(Long id, String name, String emailId, String role, List<String> authorities, String password, boolean active) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.role = role;
        this.authorities = authorities;
        this.password = password;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        return "User[" + id + ", " + name + ", " + emailId + ", " + role + ", " + authorities + ", " + password + "]";
    }
}
