package com.recipez.recipe;

import com.recipez.user.User;
import org.json.*;
import java.util.ArrayList;
import java.util.List;
import com.recipez.util.DietType;

public class Recipe {
    /*
        Recipes should be saved into a JSON (JavaScript Object Notation) format and stored
        locally in resources/data. JSON will provide an easy way to describe recipes with
        "key" "value" pairs.

        Objects of this class will be created from resources/data/recipes.json as temporary
        memory when application is running. This is for quick look-ups and easy access.
        When application closes, this memory is lost. However, the data remains safe in
        recipes.json.

        e.g. recipes.json

        [
            {
                "name": "Cake",
                "ingredients": [
                    "Milk",
                    "Eggs",
                    "Wheat",
                    "Sugar"
                ],
                "calories": 1000
            },
            {
                "name": "Bread",
                "ingredients": [
                    "Eggs",
                    "Wheat",
                    "Sugar"
                ],
                "calories": 100
            }
        ]
     */

    // Name of the recipe.
    private String name;
    // Short description of the recipe describing the meal.
    private String description;

    // Short instructions for making the meal
    private String instructions;
  
    private JSONObject recipeJson;
    private JSONArray ingredientsJson;

    // total calories in the meal
    private int calories;

    private DietType dietType;
    // Store all ingredients into an ArrayList (dynamically sizes).
    // Data is lost when application closes.
    private List<Ingredient> ingredients;

    public Recipe(String name, String description, String instructions, List<Ingredient> ingredients, int calories, DietType dietType) {
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.calories = calories;
        this.dietType = dietType;

        // Setup JSONArray for recipe
        recipeJson = new JSONObject();
        recipeJson.put("name", name);
        recipeJson.put("description", description);
        recipeJson.put("instructions", instructions);
        recipeJson.put("calories", calories);
        recipeJson.put("dietType", dietType);

        ingredientsJson = new JSONArray();

        for (Ingredient ingredient : ingredients)
            ingredientsJson.put(ingredient.getIngredientJson());
        recipeJson.put("ingredients", ingredientsJson);
    }

    // Getters.
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getInstructions() {
        return instructions;
    }
    public int getCalories() { return calories; }
    public DietType getDietType() {
        return dietType;
    }
    public JSONObject getRecipeJson() {
        return recipeJson;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
