package com.vsks.controller;

import com.vsks.dto.mongodb.Student;
import com.vsks.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/{rollNo}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudentByRollNo(@PathVariable String rollNo) {
        Student student = studentService.findStudentById(rollNo);
        System.out.println("Student got is: " + student);
        return (null != student) ? ResponseEntity.ok(student) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStudents() {
        List<Student> studentList = studentService.findAllStudents();
        System.out.println("Students: " + studentList);
        return (!CollectionUtils.isEmpty(studentList)) ? ResponseEntity.ok(studentList) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        System.out.println("Saving Student " + student + " ...");
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping(value = "/update/{rollNo}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@PathVariable String rollNo, @RequestBody Student student) {
        System.out.println("Updating Student " + student + " by PUT req method ...");
        Student student1 = studentService.updateStudent(rollNo, student);
        return null != student1 ? ResponseEntity.ok(student1) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping(value = "/update/{rollNo}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@PathVariable String rollNo, @RequestBody Map<String, Object> fieldValueMap) {
        System.out.println("Updating Student " + fieldValueMap + " by PATCH req method ...");
        Student student = studentService.updateStudent(rollNo, fieldValueMap);
        return null != student ? ResponseEntity.ok(student) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value = "/delete/{rollNo}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteStudent(@PathVariable String rollNo) {
        System.out.println("Deleting Student of Roll No " + rollNo);
        Student student = studentService.deleteStudent(rollNo);
        return null != student ? ResponseEntity.ok(student) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
