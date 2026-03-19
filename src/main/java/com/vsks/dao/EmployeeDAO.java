package com.vsks.dao;

import com.vsks.domain.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> loadAllEmployees();
}
