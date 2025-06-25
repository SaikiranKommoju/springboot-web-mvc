package com.vsks.service;

import com.vsks.dto.mongodb.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Student findStudentById(String rollNo);
    List<Student> findAllStudents();
    Student saveStudent(Student student);
    Student updateStudent(String rollNo, Student student);
    Student updateStudent(String rollNo, Map<String, Object> fieldValueMap);
    Student deleteStudent(String rollNo);
}
