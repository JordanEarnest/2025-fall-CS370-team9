package com.recipez.user;

import com.recipez.recipe.Ingredient;
import com.recipez.recipe.MeasurementType;
import com.recipez.recipe.Recipe;
import com.recipez.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String DATA_DIR = "Data";
    private static final String FILE_NAME = "Users.json";
    private static final Path DEFAULT_PATH = Paths.get(DATA_DIR, FILE_NAME);

    private User user;
    // Holds actual JSONArray object for our FILE_NAME in DATA_DIR, we are using a library for this.
    private JSONArray usersJson;
    // Holds the user specific JSONObject found within JSONArray
    private JSONObject userJson;

    public UserManager(User user) throws IOException {
        // Set the currently logged-in user
        this.user = user;

        usersJson = new JSONArray();

        // Creates Data folder to store json in either project directory or working directory of a built jar file.
        Files.createDirectories(DEFAULT_PATH.getParent());

        // If the Users.json already exists, we don't need to create another one.
        if (!Files.exists(DEFAULT_PATH)) {
            Files.createFile(DEFAULT_PATH);
            Log.info("Created file " + FILE_NAME + " in " + DATA_DIR);
        } else {
            // Instead, let's load from Users.json on startup.
            Log.info(FILE_NAME + " in " + DEFAULT_PATH + " already exists");
            pullJson(); // Stores Users.json into UsersJson array object, if file already exists.
            Log.info("Pulling data from " + DEFAULT_PATH);
        }

        // If the user exists, let's assign it's specific JSON Object to our instance variable userJson to store it for later modifications or updates.
        // Might be null if new user, but updateUserInformationIntoUsersJson() will handle and create a new user JSONObject.
        userJson = getUserJSONObject();
        // This makes sure that all the recipes from the json are put into are user recipes data structure.
        // This way we can manipulate the data of the recipes and later push it back to the json
        copyRecipesFromUsersJsonToUsersRecipesList();
    }

    public void updateUserInformationIntoUsersJson() {
        // Before updating other data, update the BMR first. There is a chance a dependent variable such as age was changed in which case
        // we need to update BMR.
        user.setBMR(calculateBMR());
        // If user doesn't exist in the usersJson then create a new user JSON object
        if (userJson == null) {
            userJson = new JSONObject();
            // These are all the attributes of the user we want to save into the main users.json
            userJson.put("name", user.getName());
            userJson.put("password", user.getPassword());
            userJson.put("bodyGoal", user.getBodyGoal());
            userJson.put("dietType", user.getDietType());
            userJson.put("weight", user.getWeight());
            userJson.put("height", user.getHeight());
            userJson.put("age", user.getAge());
            userJson.put("bmr", user.getBMR());
            userJson.put("isMan", user.getIsMan());
            userJson.put("recipes", new JSONArray());
        } else { // If user does exist, let's update the usersJson file to accommodate for any changes to attributes of user.

            int indexOfUserJSONObject = getIndexOfUserJSONObject();

            userJson.put("name", user.getName());
            userJson.put("bodyGoal", user.getBodyGoal());
            userJson.put("dietType", user.getDietType());
            userJson.put("weight", user.getWeight());
            userJson.put("height", user.getHeight());
            userJson.put("age", user.getAge());
            userJson.put("bmr", user.getBMR());
            userJson.put("isMan", user.getIsMan());

            usersJson.remove(indexOfUserJSONObject); // remove so we can add a new replacement with updated details

            Log.info("User " + user.getName() + " already exists in " + DEFAULT_PATH + "... updating user attributes instead");
        }

        // --------------
        usersJson.put(userJson); // Append userJson to usersJson
        pushJson(); // Push the new usersJson to the Users.json
    }

    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    // Checks in Users.json for hash and compares with user typed in password, returns true or false
    public boolean checkPassword(String password) {
        try {
            return BCrypt.checkpw(password, getHashedPassword());
        } catch (IllegalArgumentException e) { // Catches some error with an incorrectly formatted hashedPassword.
            // In some cases, if the user hasn't been registered yet but still attempts to log in, it will look for a
            // hash that doesn't exist in the database, so we catch this error here.
            Log.warning("When checking password for user " + user.getName() + ", an incorrectly or non-existent hashed password was used. Try creating an account first?");
            return false;
        }
    }

    public void copyRecipesFromUsersJsonToUsersRecipesList() {
        pullJson();
        List<Recipe> recipes = new ArrayList<>(); // will be returned at end of function

        JSONArray recipesJsonArray = userJson.getJSONArray("recipes");

        // Loop through all recipes
        for (int i = 0; i < recipesJsonArray.length(); i++) {
            JSONObject currentRecipeJsonObject = recipesJsonArray.getJSONObject(i);
            // For every recipe, loop through its ingredients, then create recipe and store in return variable "recipes"
            JSONArray ingredientsJsonArray = currentRecipeJsonObject.getJSONArray("ingredients");
            List<Ingredient> ingredients = new ArrayList<>(); // changes often for new recipes
            for (int j = 0; j < ingredientsJsonArray.length(); j++) {
                JSONObject currentIngredientJsonObject = ingredientsJsonArray.getJSONObject(j);
                ingredients.add(new Ingredient(currentIngredientJsonObject.getString("name"), currentIngredientJsonObject.getFloat("quantifier"), MeasurementType.valueOf(currentIngredientJsonObject.getString("measurementType"))));
            }
            recipes.add(new Recipe(currentRecipeJsonObject.getString("name"), currentRecipeJsonObject.getString("description"), currentRecipeJsonObject.getString("instructions"), ingredients, currentRecipeJsonObject.getInt("calories")));
        }
        user.setRecipes(recipes);
    }


    // Used for when we create accounts with just parameters "name" and "password"
    // We find the information we need in Users.json and assign them to the instance variables in the user object.
    public void loadUserDataFromUsersJson() {
        if (userJson == null) {
            Log.warning("Failed to load user data for " + user.getName() + " probably because " + user.getName() + " doesn't exist within " + DEFAULT_PATH + ". Try creating an account for this name.");
            return;
        }

        pullJson();
        user.setBodyGoal(BodyGoal.valueOf(userJson.getString("bodyGoal")));
        user.setDietType(DietType.valueOf(userJson.getString("dietType")));
        user.setWeight(userJson.getFloat("weight"));
        user.setHeight(userJson.getFloat("height"));
        user.setAge(userJson.getInt("age"));
        user.setIsMan(userJson.getBoolean("isMan"));
        user.setBMR(calculateBMR());
    }

    private String getHashedPassword() {
        if (userJson == null)
            return "undefined";
        return userJson.getString("password");
    }

    // Finds the BMR of the user and automatically sets instance variable "bmr" of user correctly when method is run.
    public int calculateBMR() {
        double weightToKg = user.getWeight() * 0.453592; // lbs to kg
        double heightToCm = user.getHeight() * 30.48; // ft to cm
        int age = user.getAge();
        final double womenBMRConstant = -161;
        final double menBMRConstant = 5;

        int bmr;

        double baseEquation = (10 * weightToKg) + (6.25 * heightToCm) - (5 * age);

        // BMR Equation -> Mifflin–St Jeor Equation (modern, most commonly used)
        if (user.getIsMan())
            bmr = (int)(baseEquation + menBMRConstant);
        else ///  BMR Equation for Women
            bmr = (int)(baseEquation + womenBMRConstant);

        return bmr;
    }

    public void storeRecipe(Recipe recipe){
        pullJson();
        // Find user in Users.json and if they exist, put the recipe in (if there are no duplicate recipes)
        for (int i = 0; i < usersJson.length(); i++) {
            JSONObject selectedUser = usersJson.getJSONObject(i);
            if (selectedUser.get("name").equals(user.getName())) {
                // At this point, we have found the user
                // But is the recipe a duplicate? Let's check.
                for (int j = 0; j < selectedUser.getJSONArray("recipes").length(); j++) {
                    // Holds the current recipe being looked at for user
                    JSONObject selectedRecipe = selectedUser.getJSONArray("recipes").getJSONObject(j);

                    // Compare the current recipe with the recipe attempting to being stored.
                    // If they are the same, then break out of the method.
                    if (selectedRecipe.get("name").equals(recipe.getName())) {
                        Log.warning("Attempted to store recipe " + recipe.getName() + " with user " + user.getName() + " but failed since " + recipe.getName() + " already exists.");
                        return;
                    }
                }
                // At this point, there are no duplicate recipes. Safe to store.
                Log.info("Storing recipe called " + recipe.getName() + " for " + user.getName());
                selectedUser.getJSONArray("recipes").put(recipe.getRecipeJson());
                Log.info("Successfully stored recipe called " + recipe.getName() + " for " + user.getName());

                pushJson(); // Update Users.json

                copyRecipesFromUsersJsonToUsersRecipesList(); // Update users' recipe data structure
                return;
            }
        }
        Log.warning("Failed storing recipe " + recipe.getName() + " into " + user.getName());
    }

    // Remove a recipe from the json database Users.json and also update users data structure "recipes" accordingly
    public void removeRecipe(String name) {
        pullJson();
        JSONArray recipesJsonArray = userJson.getJSONArray("recipes");
        for (int i = 0; i < recipesJsonArray.length(); i++) {
            JSONObject currentRecipeJsonObject = recipesJsonArray.getJSONObject(i);

            // Found a match, delete
            if (currentRecipeJsonObject.getString("name").equals(name)) {
                recipesJsonArray.remove(i);
                //userJson.remove("recipes");
                userJson.put("recipes", recipesJsonArray); // replace recipes with new updated recipes array object
                usersJson.remove(getIndexOfUserJSONObject()); // remove the last userJson
                usersJson.put(userJson); // add updated userJson
                pushJson(); // write to file
                copyRecipesFromUsersJsonToUsersRecipesList(); // update users recipe data structure
                break;
            }

        }

    }


    private JSONObject getUserJSONObject() {
        for (int i = 0; i < usersJson.length(); i++) {
            JSONObject selectedUser = usersJson.getJSONObject(i);
            if (selectedUser.get("name").equals(user.getName()))
                return selectedUser;
        }
        return null;
    }

    private int getIndexOfUserJSONObject() {
        for (int i = 0; i < usersJson.length(); i++) {
            JSONObject selectedUser = usersJson.getJSONObject(i);
            if (selectedUser.get("name").equals(user.getName()))
                return i;
        }
        return -1;
    }


    // Ensures that we have the latest JSON data to store it into our JSON Array Object in this class.
    private void pullJson() {
        try {
            String content = Files.readString(DEFAULT_PATH, StandardCharsets.UTF_8);
            // CHECK: Don't pull from json file if it is empty.
            // This prevents an error for below init.
            if (content.isEmpty())
                return;

            usersJson = new JSONArray(content);
            Log.info("Pulled data from " + DEFAULT_PATH + " for user " + user.getName());

        } catch (IOException e) {
            Log.error("Failed to pull data from " + DEFAULT_PATH + " for user " + user.getName());
        }
    }

    // Push our users json array object into the actual .json file in DATA_DIR
    private void pushJson() {
        try {
            Files.writeString(DEFAULT_PATH, usersJson.toString(4), StandardCharsets.UTF_8);
            Log.info("Pushed user " + user.getName() + " data to " + DEFAULT_PATH);
        } catch (IOException e) {
            Log.error("Failed to push user " + user.getName() + " data to " + DEFAULT_PATH);
        }
    }
}
