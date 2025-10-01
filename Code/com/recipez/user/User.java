package com.recipez.user;

import com.recipez.util.Log;

import java.io.IOException;

public class User {

    private String name;
    private String password; // ENCRYPT THIS LATER PLEASE, NOT NOW BUT IN FORESEEABLE FUTURE
    private UserManager userManager;
    private BodyGoal bodyGoal; //cut,bulk, or maintain.
    private DietType dietType; //vegetarian, pescatarian, etc.
    private double weight; //U.S. standard Lbs format
    private double height; //get height in feet and convert to cm for easier use in formulas
    private int age;
    private int bmr;
    private boolean isMan;

    public User(String name, String password, BodyGoal bodyGoal, DietType dietType, double weight, double height, int age, boolean isMan) {
        this.name = name;
        this.password = password;
        this.bodyGoal = bodyGoal;
        this.dietType = dietType;
        this.weight = weight;
        this.height = height;
        this.isMan = isMan;

        try {
            userManager = new UserManager(this);
        } catch (IOException e) {
            Log.error("Failed to create UserManager for " + this.name);
        }

        // Initialize user with correct BMR, rerun this method on user for additional updates to BMR if attributes such as weight/height change.
        userManager.calculateBMR();
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
    public BodyGoal getBodyGoal() {
        return bodyGoal;
    }
    public DietType getDietType() {
        return dietType;
    }
    public double getWeight() {
        return weight;
    }
    public double getHeight() {
        return height;
    }
    public boolean getIsMan() {
        return isMan;
    }
    public int getAge() {
        return age;
    }
    public int getBMR() {
        return bmr;
    }
    public void setBMR(int bmr) {
        this.bmr = bmr;
    }
}
