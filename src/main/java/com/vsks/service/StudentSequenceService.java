package com.vsks.service;

public interface StudentSequenceService {

    Long getNextId();

    String getNextRollNo(int year, String department);
}
