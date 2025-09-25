package com.recipez.user;

import com.recipez.recipe.Recipe;
import com.recipez.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserManager {
    private static final String DATA_DIR = "Data";
    private static final String FILE_NAME = "Users.json";
    private static final Path DEFAULT_PATH = Paths.get(DATA_DIR, FILE_NAME);

    private User user;

    private Path pathToJsonFile;
    // Holds actual JSONArray object for our FILE_NAME in DATA_DIR, we are using a library for this.
    private JSONArray usersJson;

    public UserManager(User user) throws IOException {
        // Set the currently logged-in user
        this.user = user;

        usersJson = new JSONArray();

        // Defines where recipe data is stored
        this.pathToJsonFile = DEFAULT_PATH;
        // Creates Data folder to store json in either project directory or working directory of a built jar file.
        Files.createDirectories(this.pathToJsonFile.getParent());

        // If the Recipes.json already exists, we don't need to create another one.
        if (!Files.exists(this.pathToJsonFile)) {
            Files.createFile(pathToJsonFile);
        } else {
            Log.info(FILE_NAME + " in " + DATA_DIR + " already exists");
            pullJson(); // Updates our usersJson if Users.json already exists.
        }

        // If user doesn't exist in the usersJson then create a new user JSON object
        if (!userExistsInUsersJson(user)) {
            // Create a user into the users json array if they previously didn't exist
            // These are all the attributes of the user we want to save into the main users.json
            // --------------
            JSONObject userJson = new JSONObject();
            userJson.put("name", user.getName());
            userJson.put("password", user.getPassword());
            userJson.put("recipes", new JSONArray());
            // --------------
            usersJson.put(userJson);
            pushJson(); // Push the new userJson to the Users.json
        } else {
            Log.info("User " + user.getName() + " already exists in " + this.pathToJsonFile.toString() + " so ignored");
        }
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
                return;
            }
        }
        Log.warning("Failed storing recipe " + recipe.getName() + " into " + user.getName());
    }

    public Recipe loadRecipe(Recipe recipe) {
        pullJson();
        // Check to see if recipe doesn't exist
        return  null;
    }

    public void removeRecipe(Recipe recipe) {

    }

    private boolean userExistsInUsersJson(User user) {
        for (int i = 0; i < usersJson.length(); i++) {
            JSONObject selectedUser = usersJson.getJSONObject(i);
            if (selectedUser.get("name").equals(user.getName()))
                return true;
        }
        return false;
    }

    // Ensures that we have the latest JSON data to store it into our JSON Array Object in this class.
    private void pullJson() {
        try {
            String content = Files.readString(pathToJsonFile, StandardCharsets.UTF_8);
            // CHECK: Don't pull from json file if it is empty.
            // This prevents an error for below init.
            if (content.isEmpty())
                return;

            usersJson = new JSONArray(content);
        } catch (IOException e) {
            Log.error("Failed to pull from " + FILE_NAME + " at " + pathToJsonFile.toString() + "!");
        }
    }

    // Push our users json array object into the actual .json file in DATA_DIR
    private void pushJson() {
        try {
            Files.writeString(DEFAULT_PATH, usersJson.toString(4), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Log.error("Failed to push to " + FILE_NAME + " at " + pathToJsonFile.toString() + "!");
        }
    }

    public Path getPathToJsonFile() {
        return pathToJsonFile;
    }
}
