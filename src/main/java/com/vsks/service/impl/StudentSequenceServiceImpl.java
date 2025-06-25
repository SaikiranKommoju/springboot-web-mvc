package com.vsks.service.impl;

import com.vsks.dto.mongodb.StudentSequence;
import com.vsks.service.StudentSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.vsks.constants.StringConstants.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class StudentSequenceServiceImpl implements StudentSequenceService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Long getNextId() {
        Criteria criteria = Criteria.where(MONGO_DB_DOC_ID).is("Student_Sequence");

        Query query = Query.query(criteria);

        Update update = new Update().inc(SEQUENCE_FIELD, 1);

        FindAndModifyOptions findAndModifyOptions = options()
                .returnNew(true)//Returns new(incremented) value
                .upsert(true)//Creates doc if nothing exists with the criteria
                ;

        StudentSequence studentSequence = mongoOperations.findAndModify(query, update, findAndModifyOptions, StudentSequence.class);

        System.out.println("Student Sequence for Seq ID Student: " + studentSequence);

        return Objects.isNull(studentSequence) ? 1L : studentSequence.getSeq();
    }

    @Override
    public String getNextRollNo(int year, String department) {
        String studentSequenceId = "Student_Sequence_" + year + "_" + department;

        Criteria criteria = Criteria.where(MONGO_DB_DOC_ID).is(studentSequenceId);

        Query query = Query.query(criteria);

        Update update = new Update().inc(SEQUENCE_FIELD, 1);

        FindAndModifyOptions findAndModifyOptions = options()
                .returnNew(true)
                .upsert(true)
                ;

        StudentSequence studentSequence = mongoOperations.findAndModify(query, update, findAndModifyOptions, StudentSequence.class);

        System.out.println("Student Sequence for Seq ID " + studentSequenceId + studentSequence);

        return Objects.isNull(studentSequence) ? (year + department + 1) : (year + department + studentSequence.getSeq());
    }
}
