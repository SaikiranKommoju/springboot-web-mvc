package com.vsks.db;

public class DBConnection {

    private String url;
    private String username;
    private String password;
    private String port;

    public DBConnection() {
    }

    public DBConnection(String url, String username, String password, String port) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    @Override
    public String toString() {
        return "DBConnection{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
