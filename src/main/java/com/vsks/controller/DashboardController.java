package com.vsks.controller;

import com.vsks.domain.Employee;
import com.vsks.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public ModelAndView dashboard() {
        List<Employee> employees = employeeService.getEmployees();
        ModelAndView mav = new ModelAndView("employee/dashboard");
        mav.addObject("employeeCount", employees.size());
        mav.addObject("employees", employees);
        return mav;
    }
}
