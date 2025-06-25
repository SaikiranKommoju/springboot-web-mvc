package com.vsks.controller;

import com.vsks.dto.Employee;

import com.vsks.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class EmployeeController {

    public EmployeeController() {
        System.out.println("EmployeeController :: no-param constructor");
    }

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/employee/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        System.out.println("Employee got is: " + employee);
        return (null != employee) ? ResponseEntity.ok(employee) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(value = "/employee", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employee employee) {
        System.out.println("Saving employee " + employee + " ...");
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping(value = "/employee/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        System.out.println("Updating employee " + employee + " by PUT req method ...");
        Employee emp = employeeService.updateEmployee(id, employee);
        return null != emp ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(emp);
    }

    @PatchMapping(value = "/employee/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Map<String, Object> fieldValueMap) {
        System.out.println("Updating employee " + fieldValueMap + " by PATCH req method ...");
        Employee emp = employeeService.updateEmployee(id, fieldValueMap);
        return null != emp ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(emp);
    }
}
