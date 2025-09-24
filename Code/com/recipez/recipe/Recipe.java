package com.recipez.recipe;

import com.recipez.user.User;

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

    private String instructions;

    // Store all ingredients into an ArrayList (dynamically sizes).
    // Data is lost when application closes.
    private final List<String> ingredients;

    public Recipe() {
        ingredients = new ArrayList<>();
        name = "Default";
        description = "No description.";
        instructions = "No instructions.";
    }

    public Recipe(String name, String description, String instructions) {
        ingredients = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.instructions = instructions;
    }

    // Adding ingredients to the ArrayList.
    public void addIngredient(String ingredient) {
        /*
            Establish a consistent ingredient format for the user.
            First set the string to all lower case.
            Then capitalize the first letter.
            e.g. egGs --> eggs --> Eggs
         */
        String formattedIngredient = ingredient.substring(0, 1).toUpperCase() + ingredient.substring(1).toLowerCase();

        // Add the correctly formatted ingredient to the ArrayList.
        ingredients.add(formattedIngredient);
    }

    // Print out the ingredients of a recipe. For debugging purposes.
    public void printIngredients() {
        System.out.println("Ingredients for " + name + ":");
        for (String ingredient : ingredients) {
            System.out.println(ingredient);
        }
    }

    // Store recipe into recipes.json file according to which user is logged in
    public void store(User user) {

    }


    // Setters and getters.
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getInstructions() {
        return instructions;
    }
}
