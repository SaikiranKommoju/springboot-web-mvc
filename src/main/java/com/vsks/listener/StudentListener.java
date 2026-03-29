package com.vsks.listener;

import com.vsks.domain.mongodb.Student;
import com.vsks.service.StudentSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentListener extends AbstractMongoEventListener<Student> {

    @Autowired
    StudentSequenceService studentSequenceService;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Student> event) {
        Student student = event.getSource();
        if (Objects.isNull(student.getId()) && Objects.isNull(student.getRollNo())) {
            student.setId(studentSequenceService.getNextId());//Global ID
            student.setRollNo(studentSequenceService.getNextRollNo(student.getYear(), student.getDepartment()));//Year-Department 2010ECE1, 2010ECE2, 2011ECE1,
        }
    }
}
