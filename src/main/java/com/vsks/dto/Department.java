package com.vsks.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Department {

    private String name;
    private String code;
    private String location;
    private List<Employee> employeeList = new ArrayList<>();
}
