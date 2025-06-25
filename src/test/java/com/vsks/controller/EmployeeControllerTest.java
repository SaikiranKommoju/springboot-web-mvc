package com.vsks.controller;

import com.vsks.config.ApplicationTestConfig;
import com.vsks.dto.Employee;
import com.vsks.repo.EmployeeRepo;
import com.vsks.service.impl.EmployeeServiceImpl;
import com.vsks.utils.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    EmployeeRepo employeeRepo;

    @Configuration
    @Import({ApplicationTestConfig.class, EmployeeController.class, EmployeeServiceImpl.class})
    static class EmployeeControllerTestConfig {
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("SaiKiran Kommoju");
        e.setEmailId("SaiKiran_Kommoju@epam.com");
        e.setDepartment("IT");
        e.setRole("Developer");
        e.setLocation("Hyderabad");

        Mockito.when(employeeRepo.findById(any())).thenReturn(Optional.of(e));

        mockMvc.perform(get("/employee/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id", CoreMatchers.is(1)));
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee e = new Employee();
        e.setName("SaiKiran Kommoju");
        e.setEmailId("SaiKiran_Kommoju@epam.com");
        e.setDepartment("IT");
        e.setRole("Developer");
        e.setLocation("Hyderabad");

        Employee e2 = new Employee();
        BeanUtils.copyProperties(e, e2);
        e2.setId(1L);

        System.out.println("EmployeeControllerTest :: employeeRepo: " + employeeRepo.hashCode());

        Mockito.when(employeeRepo.save(any())).thenReturn(e2);

        mockMvc.perform(post("/employee")
                        .content(TestUtils.asJsonString(e))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id", CoreMatchers.is(1)));
    }
}
