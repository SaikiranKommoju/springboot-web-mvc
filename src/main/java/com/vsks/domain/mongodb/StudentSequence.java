package com.vsks.domain.mongodb;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Data
@ToString
@Document(collection = "Student_Sequence")
public class StudentSequence {

    @Id
    private String id;
    private long seq;
}
