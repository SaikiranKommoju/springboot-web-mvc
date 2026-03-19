package com.vsks.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
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
}
