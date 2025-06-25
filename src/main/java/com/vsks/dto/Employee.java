package com.vsks.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@NotNull
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Null(message = "ID should not be supplied")
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 200, message = "Name must be between 10 and 200 characters")
    private String name;

    @NotNull(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String emailId;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @NotBlank(message = "Role cannot be blank")
    private String role;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime joiningDate;

    /*public Employee(String id, String name, Department department, String role, String location) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
        this.location = location;
    }*/

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDateTime joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "Emp[" + id + ", " + name + ", " + department + ", " + role + ", " + location + ", " + joiningDate + "]";
    }
}
