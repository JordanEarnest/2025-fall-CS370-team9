package com.recipez.user;

public class User {

    private String name;
    private String password; // ENCRYPT THIS LATER PLEASE, NOT NOW BUT IN FORESEEABLE FUTURE

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
