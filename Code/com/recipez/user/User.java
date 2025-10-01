package com.recipez.user;

import com.recipez.util.Log;
import org.mindrot.jbcrypt.BCrypt;

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

    // Sole purpose, logging in users
    public User(String name, String password) {
        // Create default values but will certainly be changed through userManager when user is found in Users.json
        this.name = name;
        this.password = password;
        this.bodyGoal = BodyGoal.NONE;
        this.dietType = DietType.NONE;
        this.weight = 0.0;
        this.height = 0.0;
        this.age = 0;
        this.bmr = 0;
        this.isMan = false;

        try {
            userManager = new UserManager(this);
        } catch (IOException e) {
            Log.error("Failed to create UserManager for " + this.name);
        }

        // Re-initializes instance variables to correct values found in Users.json
        userManager.loadUserDataFromUsersJson();
    }

    // Sole purpose, creating accounts
    public User(String name, String password, BodyGoal bodyGoal, DietType dietType, double weight, double height, int age, boolean isMan) {
        this.name = name;
        this.bodyGoal = bodyGoal;
        this.dietType = dietType;
        this.weight = weight;
        this.height = height;
        this.isMan = isMan;
        this.age = age;

        try {
            userManager = new UserManager(this);
        } catch (IOException e) {
            Log.error("Failed to create UserManager for " + this.name);
        }

        // Initialize user with correct BMR, rerun this method on user for additional updates to BMR if attributes such as weight/height change.
        this.bmr = userManager.calculateBMR();
        // Encrypts our password with jBCrypt using a salt
        this.password = userManager.encryptPassword(password);

        // This places data from user like attributes weight, gender, bodyGoal into Users.json
        userManager.updateUserInformationIntoUsersJson();
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

    public void printInfo() {
        Log.info("User name : " + name);
        Log.info("Password : " + password);
        Log.info("BodyGoal : " + bodyGoal);
        Log.info("DietType : " + dietType);
        Log.info("Weight : " + weight);
        Log.info("Height : " + height);
        Log.info("Age : " + age);
        Log.info("BMR : " + bmr);
        Log.info("isMan : " + isMan);
    }

    public void setBodyGoal(BodyGoal bodyGoal) {
        this.bodyGoal = bodyGoal;
    }
    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setIsMan(boolean isMan) {
        this.isMan = isMan;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setBMR(int bmr) {
        this.bmr = bmr;
    }
}
