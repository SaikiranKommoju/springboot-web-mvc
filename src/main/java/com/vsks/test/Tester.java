package com.vsks.test;

import com.vsks.dto.Department;
import com.vsks.dto.Employee;
import com.vsks.vo.EmployeeVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tester {

    private static final List<Department> departments = new ArrayList<>();
    private static final Map<String, Department> deptCodeDeptMap = new ConcurrentHashMap<>();
    private static final List<Employee> employees = new ArrayList<>();

    static {
        List<Department> departmentsList = Arrays.asList(
                new Department("Business Analysis", "BA", "HYD"),
                new Department("Front-End Design", "UX", "HYD"),
                new Department("Front-End Development", "UI", "HYD"),
                new Department("Back-End Development", "DEV", "HYD"),
                new Department("Database Maintenance", "DB", "HYD"),
                new Department("Testing", "QA", "HYD"),
                new Department("System Administration", "ITS", "HYD")
        );

        departments.addAll(departmentsList);

        deptCodeDeptMap.putAll(departments.stream().collect(Collectors.toMap(Department::getCode, Function.identity())));
        Map<String, String> deptCodeNameMap = departments.stream().collect(Collectors.toMap(Department::getCode, Department::getName));
        List<String> depts = departments.stream().map(it -> (it.getCode() + "-" + it.getName())).collect(Collectors.toList());

        List<Employee> employeeList = Arrays.asList(
                new Employee("RXRF31", "Sourabh", deptCodeDeptMap.get("BA"), "Product Owner", "HYD"),
                new Employee("RXRF32", "Palash", deptCodeDeptMap.get("DEV"), "Lead Developer", "PUN"),
                new Employee("RXRF33", "Sivakrishna", deptCodeDeptMap.get("DEV"), "Key Developer", "HYD"),
                new Employee("RXRF34", "Saikiran", deptCodeDeptMap.get("DEV"), "Developer", "HYD"),
                new Employee("RXRF35", "Sidhanshu", deptCodeDeptMap.get("DEV"), "Developer", "GUR"),
                new Employee("RXRF36", "Sulabh", deptCodeDeptMap.get("QA"), "Lead Test Engineer", "PUN"),
                new Employee("RXRF37", "Pratik", deptCodeDeptMap.get("QA"), "Test Engineer", "PUN")
        );

        employees.addAll(employeeList);

        mapEmpToDept();

        /*System.out.println(departments);
        System.out.println(employees);*/
    }

    public static void main(String[] args) {
        /*Department department = deptCodeDeptMap.get("BA");
        DepartmentVO departmentVO = new DepartmentVO();
        BeanUtils.copyProperties(department, departmentVO);
        System.out.println(department + ", " + departmentVO);
        System.out.println(department.hashCode() + ", " + departmentVO.hashCode());
        System.out.println(department.getEmployeeList().hashCode() + ", " + departmentVO.getEmployeeList().hashCode());*/

        Employee employee = employees.get(3);
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employee, employeeVO);
        System.out.println(employee + ", " + employeeVO);
        System.out.println(employee.hashCode() + ", " + employeeVO.hashCode());
        System.out.println(employee.getDepartment().hashCode() + ", " + employeeVO.getDepartment().hashCode());

    }

    private static Map<String, List<Employee>> groupEmpByDeptCode() {
        return employees.stream().collect(Collectors.groupingBy(it -> it.getDepartment().getCode()));
    }

    private static void mapEmpToDept() {
        Map<String, List<Employee>> deptCodeEmpMap = groupEmpByDeptCode();
        for (Map.Entry<String, List<Employee>> entry : deptCodeEmpMap.entrySet()) {
            deptCodeDeptMap.get(entry.getKey()).getEmployeeList().addAll(entry.getValue());
        }
    }

}
