package com.vsks.service.impl;

import com.vsks.dto.Employee;
import com.vsks.repo.EmployeeRepo;
import com.vsks.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public Employee findEmployeeById(Long id) {
        /*if (id > 0) {
            throw new EmployeeDatabaseException("Employee database is down");
        }*/
        return employeeRepo.findById(id).orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        //return employeeRepo.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmp = employeeRepo.findById(id);
        optionalEmp.ifPresent(it -> employee.setId(it.getId()));
        return optionalEmp.orElse(null);
    }

    @Override
    public Employee updateEmployee(Long id, Map<String, Object> fieldValueMap) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee emp = optionalEmployee.get();
            fieldValueMap.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(Employee.class, k);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, emp, v);
                }
            });
            return employeeRepo.save(emp);
        }
        return null;
    }
}
