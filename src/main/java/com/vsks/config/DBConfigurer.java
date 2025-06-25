package com.vsks.config;

import com.vsks.db.DBConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
//@Configuration
@PropertySource({"db-config-${profile}.properties"})
public class DBConfigurer {

    @Value("${url}")
    private String url;

    @Value("${usrname:defaultuser}")
    private String username = "fixeduser";

    @Value("${password}")
    private String password;

    @Value("${port}")
    private String port;

    @Bean
    public DBConnection establishConnection() {
        System.out.println("establishConnection");
        DBConnection dbConnection = new DBConnection(url, username, password, port);
        System.out.println(dbConnection);
        return dbConnection;
    }

}
