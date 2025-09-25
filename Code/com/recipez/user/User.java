package com.recipez.user;

public class User {

    private String name;
    private String password; // ENCRYPT THIS LATER PLEASE, NOT NOW BUT IN FORESEEABLE FUTURE
    private String bodyGoal; //cut,bulk, or maintain.
    private String preference; //vegetarian, pescatarian, etc.
    private double weight; //U.S. standard Lbs format
    private double height; //get height in feet and convert to cm for easier use in formulas

    public User(String name, String password, String bodyGoal, String preference) {
        this.name = name;
        this.password = password;
        this.bodyGoal = bodyGoal;
        this.preference = preference;
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
    public void setBodyGoal(String bodyGoal) {this.bodyGoal = bodyGoal;}
    public String getBodyGoal() {return bodyGoal;}
    public void setPreference(String preference) {this.preference = preference;}
    public String getPreference() {return preference;}
    public void setWeight(double weight) {this.weight = weight;}
    public double getWeight() {return weight;}
    public void setHeight(double height) {this.height = height;}
    public double getHeight() {return height;}
}
