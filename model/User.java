package com.example.daskalski.warcraftbookstore.model;

/**
 * Created by Daskalski on 9/1/16.
 */
public class User {

    private String username;
    private String password;
    private String address;
    private String email;

    public User(String username, String password, String address, String email) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
