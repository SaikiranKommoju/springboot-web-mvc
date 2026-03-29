package com.vsks.service;

import com.vsks.domain.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    Employee updateEmployee(Long id, Map<String, Object> fieldValueMap);
}
