package com.vsks.dao.impl;

import com.vsks.dao.EmployeeDAO;
import com.vsks.db.DBConnection;
import com.vsks.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private DBConnection dbConnection;

    @Override
    public List<Employee> loadAllEmployees() {
        Employee e1 = new Employee(1L, "saikiran.k@indianeagle.com");
        Employee e2 = new Employee(2L, "praveen.k@indianeagle.com");
        Employee e3 = new Employee(3L, "nagendra.u@indianeagle.com");
        return Arrays.asList(e1, e2, e3);
    }
}
