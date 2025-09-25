package com.recipez.recipe;

import org.json.JSONObject;

public class Ingredient {
    private String name;

    private MeasurementType measurementType;

    private float quantifier;


    public Ingredient(String name, float quantifier, MeasurementType measurementType) {
        this.name = name;
        this.quantifier = quantifier;
        this.measurementType = measurementType;

    }

    // Setters and getters.
    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public String getName() {
        return name;
    }

    public float getQuantifier() {
        return quantifier;
    }
}
