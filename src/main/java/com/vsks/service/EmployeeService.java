package com.vsks.service;

import com.vsks.dto.Employee;

import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    Employee findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    Employee updateEmployee(Long id, Map<String, Object> fieldValueMap);
}
