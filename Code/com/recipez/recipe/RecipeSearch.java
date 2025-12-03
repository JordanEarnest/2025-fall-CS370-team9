package com.recipez.recipe;


import com.recipez.util.DietType;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearch {

    //search recipe by diet type (helper method)
    public static List<Recipe> filterByDietType(List<Recipe> recipes, DietType dietType) {
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

    public static List<Recipe> searchByString(List<Recipe> recipes, String searchString) {
        List<Recipe> foundRecipes = new ArrayList<>();

        for (Recipe r : recipes) {
            String lowercaseRecipeName = r.getName().toLowerCase();
            if (lowercaseRecipeName.contains(searchString.toLowerCase())) {
                foundRecipes.add(r);
            }
        }
        return foundRecipes;
    }


    //*************************
    //QUICKSORT BY CALORIES
    //*************************
    // Sort the recipe array in ascending order based on calories, Partition helper function
    static int partitionCaloriesAscending(List<Recipe> recipes, int low, int high) {
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

    // Sort the recipe array in Descending order based on calories, Partition helper function
    static int partitionCaloriesDescending(List<Recipe> recipes, int low, int high) {
        int pivot = recipes.get(high).getCalories();
        int i = (low - 1);
        for(int j = low; j < high; j++) {
            if (recipes.get(j).getCalories() >= pivot) {
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

    // Sort the recipe array in ascending order based on calories (Wrapper Function, Sorts whole list)
    public static void quickSortCaloriesAscending(List<Recipe> recipes) {
        if(recipes.size() < 2) {
            return; //Recipe is already sorted
        }
        quickSortCaloriesAscending(recipes, 0, recipes.size() - 1);
    }

    // Sort the recipe array in ascending order based on calories (Wrapper Function, Sorts whole list)
    public static void quickSortCaloriesDescending(List<Recipe> recipes) {
        if(recipes.size() < 2) {
            return; //Recipe is already sorted
        }
        quickSortCaloriesDescending(recipes, 0, recipes.size() - 1);
    }

    // Sort the recipe array in ascending order based on calories
    public static List<Recipe> quickSortCaloriesAscending(List<Recipe> recipes, int low, int high) {
        if (low < high){
            int pivotIndex = partitionCaloriesAscending(recipes, low, high);
            quickSortCaloriesAscending(recipes, low, pivotIndex - 1);
            quickSortCaloriesAscending(recipes, pivotIndex + 1, high);
        }
        return recipes;
    }

    // Sort the recipe array in ascending order based on calories
    public static List<Recipe> quickSortCaloriesDescending(List<Recipe> recipes, int low, int high){
        if(low < high){
            int pivotIndex = partitionCaloriesDescending(recipes, low, high);
            quickSortCaloriesDescending(recipes, low ,pivotIndex - 1);
            quickSortCaloriesDescending(recipes, pivotIndex +  1, high);
        }
        return recipes;
    }

    
    //*************************
    //QUICKSORT BY NAME
    //*************************
    // Sort the recipe array in alphabetically ascending order based on Name, Partition helper function
    static int partitionNameAscending(List<Recipe> recipes, int low, int high) {
        String pivot = recipes.get(high).getName();
        int i = (low - 1);
        for(int j = low; j < high; j++) {
            if (recipes.get(j).getName().compareTo(pivot) <= 0) {
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

    // Sort the recipe array in alphabetically Descending order based on Name, Partition helper function
    static int partitionNameDescending(List<Recipe> recipes, int low, int high) {
        String pivot = recipes.get(high).getName();
        int i = (low - 1);
        for(int j = low; j < high; j++) {
            if (recipes.get(j).getName().compareTo(pivot) >= 0) {
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

    // Sort the recipe array in ascending alphabetic order based on Name (Wrapper Function, Sorts whole list)
    public static void quickSortNameAscending(List<Recipe> recipes) {
        if(recipes.size() < 2) {
            return; //Recipe is already sorted
        }
        quickSortNameAscending(recipes, 0, recipes.size() - 1);

    }

    // Sort the recipe array in Descending alphabetic order based on Name (Wrapper Function, Sorts whole list)
    public static void quickSortNameDescending(List<Recipe> recipes) {
        if(recipes.size() < 2) {
            return; //Recipe is already sorted
        }
        quickSortNameDescending(recipes, 0, recipes.size() - 1);

    }

    // Sort the recipe array in alphabetically ascending order based on Name
    public static List<Recipe> quickSortNameAscending(List<Recipe> recipes, int low, int high) {
        if (low < high){
            int pivotIndex = partitionNameAscending(recipes, low, high);
            quickSortNameAscending(recipes, low, pivotIndex - 1);
            quickSortNameAscending(recipes, pivotIndex + 1, high);
        }
        return recipes;
    }

    // Sort the recipe array in alphabetically Descending order based on Name
    public static List<Recipe> quickSortNameDescending(List<Recipe> recipes, int low, int high) {
        if (low < high){
            int pivotIndex = partitionNameDescending(recipes, low, high);
            quickSortNameDescending(recipes, low, pivotIndex - 1);
            quickSortNameDescending(recipes, pivotIndex + 1, high);
        }
        return recipes;
    }

}

