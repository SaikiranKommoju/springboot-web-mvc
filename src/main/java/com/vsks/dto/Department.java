package com.vsks.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Department {

    private String name;
    private String code;
    private String location;
    private List<Employee> employeeList = new ArrayList<>();

    public Department(String name, String code, String location) {
        this.name = name;
        this.code = code;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Dept[" + name + ", " + code + ", " + employeeList.stream().map(Employee::getId).collect(Collectors.toList()) + ", " + location + "]";
    }
}
