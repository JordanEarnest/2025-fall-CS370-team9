package com.recipez.recipe;

public class Ingredient {
    private String name;

    private MeasurementType measurementType;

    private float quantifier;

    public Ingredient() {
        name = "Default";
        measurementType = MeasurementType.NUMBER;
        quantifier = 0f;
    }

    public Ingredient(String name, float quantifier, MeasurementType measurementType) {
        this.name = name;
        this.measurementType = measurementType;
        this.quantifier = quantifier;
    }

    // Setters and getters.
    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }
    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setQuantifier(float quantifier) {
        this.quantifier = quantifier;
    }
    public float getQuantifier() {
        return quantifier;
    }
}
