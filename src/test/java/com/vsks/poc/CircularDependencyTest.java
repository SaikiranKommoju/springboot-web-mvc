package com.vsks.poc;

import com.vsks.poc.circular.dependency.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CircularDependencyTest {

    @Autowired
    ApplicationContext context;

    @Configuration
    @ComponentScan(basePackages = {"com.vsks.poc.circular.dependency"})
    static class CircularDependencyTestConfig {
    }

    @Test
    public void testCircularDependencyTest() {
        A a = context.getBean(A.class);
        System.out.println("Test :: B in A = " + a.getB());
    }
}
