package com.recipez.recipe;


import com.recipez.util.DietType;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearch {

    //search recipe by diet type (helper method)
    private static List<Recipe> filterByDietType(List<Recipe> recipes, DietType dietType) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.getDietType().equals(dietType)) { //need to import dietType from user class
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

    // Search vegetarian recipes (
    public static List<Recipe> filterKeto(List<Recipe> recipes) {
        return filterByDietType(recipes, DietType.KETO);
    }

    // Search vegetarian recipes
    public static List<Recipe> filterCarnivorous(List<Recipe> recipes) {
        return filterByDietType(recipes, DietType.CARNIVOROUS);
    }

    // Sort the recipe array in ascending order based on calories (Wrapper Function, Sorts whole list)
    public static void quickSortCaloriesAscending(List<Recipe> recipes) {
        if(recipes.size() < 2) {
            return; //Recipe is already sorted
        }
        quickSortCaloriesAscending(recipes, 0, recipes.size() - 1);
    }
    // Sort the recipe array in ascending order based on calories
    public static void quickSortCaloriesAscending(List<Recipe> recipes, int low, int high){
        if(low < high){
            int pivotIndex = partition(recipes, low, high);
            quickSortCaloriesAscending(recipes, low ,pivotIndex - 1);
            quickSortCaloriesAscending(recipes, pivotIndex +  1, high);
        }
    }
    // Sort the recipe array in ascending order based on calories, Partition helper function
    static int partition(List<Recipe> recipes, int low, int high) {
        int pivot = recipes.get(high).getCalories();
        int i = (low - 1);
        for(int j = low; j < high; j++) {
            if (recipes.get(j).getCalories() <= pivot) {
                i++;
                //swap
                Recipe temp = recipes.get(i);
                recipes.set(i, recipes.get(j));
                recipes.set(j, temp);
            }
        }
        // Swap [high] and [i+1] and pivot
        Recipe temp = recipes.get(i + 1);
        recipes.set(i + 1, recipes.get(high));
        recipes.set(high, temp);
        return i + 1;
    }


}
