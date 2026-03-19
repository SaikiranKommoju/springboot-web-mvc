package com.vsks.dto.mongodb;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Student")
@ToString
public class Student {

    @Id
    private Long id;
    private String rollNo;
    private String name;
    private String department;
    private Integer year;
    private Contact contact;
}

@Data
@ToString
class Contact {
    private String address;
    private Long mobileNo;
    private String emailId;
}
