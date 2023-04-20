package com.vsks.dao;

import com.vsks.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> loadAllEmployees();

}
