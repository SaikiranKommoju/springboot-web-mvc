package com.vsks.service.impl;

import com.vsks.dto.mongodb.StudentSequence;
import com.vsks.dto.mongodb.Student;
import com.vsks.repo.mongodb.StudentRepository;
import com.vsks.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String PRIMARY_SEQUENCE = "primarySequence";

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Student findStudentById(String rollNo) {
        return studentRepository.findStudentByRollNo(rollNo).orElse(null);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        if (Objects.isNull(student.getYear())) {
            student.setYear(LocalDate.now().getYear());
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(String rollNo, Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByRollNo(rollNo);
        optionalStudent.ifPresent(it -> student.setRollNo(it.getRollNo()));
        return optionalStudent.map(it -> studentRepository.save(student)).orElse(null);
    }

    @Override
    public Student updateStudent(String rollNo, Map<String, Object> fieldValueMap) {
        Optional<Student> optionalStudent = studentRepository.findStudentByRollNo(rollNo);
        if (optionalStudent.isPresent()) {
            Student emp = optionalStudent.get();
            fieldValueMap.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(Student.class, k);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, emp, v);
                }
            });
            return studentRepository.save(emp);
        }
        return null;
    }

    @Override
    public Student deleteStudent(String rollNo) {
        Optional<Student> optionalStudent = studentRepository.findStudentByRollNo(rollNo);
        optionalStudent.ifPresent(it -> studentRepository.delete(it));
        return optionalStudent.orElse(null);
    }

}
