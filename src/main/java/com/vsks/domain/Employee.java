package com.vsks.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Employee {

    private Long id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public Employee(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
