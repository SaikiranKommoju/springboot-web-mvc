package com.vsks.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class Response {

    private Employee data;
    private Support support;

    @NoArgsConstructor
    @Setter
    @Getter
    public static class Employee {
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

    @Setter
    @Getter
    public static class Support {
        private String url;
        private String text;
    }

}
