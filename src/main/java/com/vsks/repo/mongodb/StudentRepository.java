package com.vsks.repo.mongodb;

import com.vsks.domain.mongodb.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {

    Optional<Student> findStudentByRollNo(String rollNo);
    List<Student> findStudentByDepartment(String department);
}
