package com.recipez.user;

import com.recipez.recipe.Recipe;
import com.recipez.util.Log;

import java.io.IOException;
import java.util.List;

public class User {

    private String name;
    private String password; // ENCRYPT THIS LATER PLEASE, NOT NOW BUT IN FORESEEABLE FUTURE
    private UserManager userManager;

    public User(String name, String password) {
        this.name = name;
        this.password = password;

        try {
            userManager = new UserManager(this);
        } catch (IOException e) {
            Log.error("Failed to create UserManager for " + this.name);
        }
    }

    public UserManager getUserManager() {
        return userManager;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
}
