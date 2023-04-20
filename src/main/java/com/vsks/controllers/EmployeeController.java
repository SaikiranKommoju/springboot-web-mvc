package com.vsks.controllers;

import com.vsks.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeRestService;

    @RequestMapping(value = "/findEmployeeEmailId/{empId}", produces = "application/json")
    @ResponseBody
    public String findEmployeeEmailId(@PathVariable Long empId) {
        return "{\"data\": \"" + employeeRestService.fetchEmployeeEmailId(empId) + "\"}";
    }

}
