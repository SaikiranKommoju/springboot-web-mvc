package com.vsks.controller;

import com.vsks.service.EmployeeService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class EmployeeController2 {

    @Autowired
    private EmployeeService2 employeeRestService;

    @RequestMapping(value = "/findEmployeeEmailId/{empId}", produces = "application/json")
    @ResponseBody
    public String findEmployeeEmailId(@PathVariable Long empId) {
        return "{\"data\": \"" + employeeRestService.fetchEmployeeEmailId(empId) + "\"}";
    }

}
