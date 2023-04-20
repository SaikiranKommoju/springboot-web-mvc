package com.vsks.dto;

public class Employee {

    private String id;
    private String name;
    private Department department;
    private String role;
    private String location;

    public Employee(String id, String name, Department department, String role, String location) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Emp[" + id + ", " + name + ", " + department.getCode() + ", " + role + ", " + location + "]";
    }
}
