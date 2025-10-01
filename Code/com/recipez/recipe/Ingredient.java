package com.recipez.recipe;

import org.json.JSONObject;

public class Ingredient {
    private String name;

    private MeasurementType measurementType;

    private float quantifier;

    JSONObject ingredientJson;

    public Ingredient(String name, float quantifier, MeasurementType measurementType) {
        this.name = name;
        this.quantifier = quantifier;
        this.measurementType = measurementType;

        ingredientJson = new JSONObject();
        ingredientJson.put("name", name);
        ingredientJson.put("quantifier", quantifier);
        ingredientJson.put("measurementType", measurementType.toString());
    }

    // Getters.
    public JSONObject getIngredientJson() {
        return ingredientJson;
    }
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
