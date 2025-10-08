package com.recipez.recipe;


import com.recipez.util.DietType;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearch {

    //search recipe by diet type (helper method)
    private static List<Recipe> filterByDietType(List<Recipe> recipes, DietType dietType) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe r : recipes) {
            if( r.getDietType().equals(dietType) ){ //need to import dietType from user class
                filteredRecipes.add(r);
            }
        }
        return filteredRecipes;
    }
    // Search vegetarian recipes
    public static List<Recipe> filterVegetarian(List<Recipe> recipes) {
        return filterByDietType(recipes, DietType.VEGETARIAN);
    }

    // Search vegan recipes
    public static List<Recipe> filterVegan(List<Recipe> recipes) {
        return filterByDietType(recipes, DietType.VEGAN);
    }

    // Search vegetarian recipes
    public static List<Recipe> filterPescatarian(List<Recipe> recipes) {
        return filterByDietType(recipes, DietType.PESCATARIAN);
    }

    // Search vegetarian recipes (helper method)
    public static List<Recipe> filterKeto(List<Recipe> recipes) {
        return filterByDietType(recipes, DietType.KETO);
    }

    // Search vegetarian recipes (helper method)
    public static List<Recipe> filterCarnivorous(List<Recipe> recipes) {
        return filterByDietType(recipes, DietType.CARNIVOROUS);
    }


}
