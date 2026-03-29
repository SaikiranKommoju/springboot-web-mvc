package com.vsks.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import com.vsks.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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
    @Size(min = 3, max = 30, message = "Name must be between 10 and 30 characters")
    private String name;

    @NotNull(message = "Gender cannot be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String emailId;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @NotBlank(message = "Role cannot be blank")
    private String role;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    /*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime joiningDate;*/

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate joiningDate;
}
