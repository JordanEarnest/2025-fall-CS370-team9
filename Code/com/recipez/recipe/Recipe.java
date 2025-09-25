package com.recipez.recipe;

import com.recipez.user.User;
import org.json.*;
import java.util.ArrayList;
import java.util.List;

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

    // Enumerate directions
    private String directions;

    private JSONObject recipeJson;
    private JSONArray ingredientsJson;

    // Store all ingredients into an ArrayList (dynamically sizes).
    // Data is lost when application closes.
    private List<Ingredient> ingredients;

    public Recipe(String name, String description, String directions, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.ingredients = ingredients;

        // Setup JSONArray for recipe
        recipeJson = new JSONObject();
        recipeJson.put("name", name);
        recipeJson.put("description", description);
        recipeJson.put("directions", directions);

        ingredientsJson = new JSONArray();

        for (Ingredient ingredient : ingredients)
            ingredientsJson.put(ingredient.getIngredientJson());
        recipeJson.put("ingredients", ingredientsJson);
    }
    // Print out the ingredients of a recipe. For debugging purposes.
    public void printIngredients() {
        System.out.println("Ingredients for " + name + ":");
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName());
        }
    }

    // Getters.
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getDirections() {
        return directions;
    }
    public JSONObject getRecipeJson() {
        return recipeJson;
    }
}
