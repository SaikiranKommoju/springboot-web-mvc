package com.vsks.test;

import com.vsks.dto.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        int[] arr = {1, 2, 10, 20, 30};

        int sum = Arrays.stream(arr)
                .reduce(Integer::sum)
                .getAsInt();

        System.out.println(sum);

        List<Employee> empList = new ArrayList<>();

        Predicate<Employee> p1 = Objects::nonNull;
        Predicate<Employee> p2 = e -> e.getId() > 10;

        List<String> empList2 = empList.stream()
                .filter(p1.and(p2))
                .map(Employee::getName)
                .collect(Collectors.toList());
    }
}
