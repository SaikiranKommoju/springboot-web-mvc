package com.vsks.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
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
}
