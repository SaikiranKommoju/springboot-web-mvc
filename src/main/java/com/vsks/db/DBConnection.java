package com.vsks.db;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DBConnection {

    private String url;
    private String username;
    private String password;
    private String port;
}
