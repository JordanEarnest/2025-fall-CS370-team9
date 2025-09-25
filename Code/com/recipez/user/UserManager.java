package com.recipez.user;

import com.recipez.recipe.Recipe;
import com.recipez.util.Log;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserManager {
    private static final String DATA_DIR = "Data";
    private static final String FILE_NAME = "Users.json";
    private static final Path DEFAULT_PATH = Paths.get(DATA_DIR, FILE_NAME);

    private User currentUser;

    private Path pathToJsonFile;
    // Holds actual JSONArray object for our FILE_NAME in DATA_DIR, we are using a library for this.
    private JSONArray usersJson;

    public UserManager(User currentUser) throws IOException {
        // Set the currently logged-in user
        this.currentUser = currentUser;

        // Defines where recipe data is stored
        this.pathToJsonFile = DEFAULT_PATH;
        // Creates Data folder to store json in either project directory or working directory of a built jar file.
        Files.createDirectories(this.pathToJsonFile.getParent());

        // If the Recipes.json already exists, we don't need to create another one.
        if (!Files.exists(this.pathToJsonFile)) {
            Files.createFile(pathToJsonFile);
        } else {
            Log.info("File at " + pathToJsonFile.toString() + " already exists!");
            updateJSON();
        }
    }

    public void storeRecipe(Recipe recipe){
        updateJSON();
        // Check to see if user already is in Recipes.json, otherwise create a new key


    }

    public Recipe loadRecipe(Recipe recipe) {
        updateJSON();
        // Check to see if recipe doesn't exist
        return  null;
    }

    // Ensures that we have the latest JSON data and store it into our JSON Array Object.
    private void updateJSON() {
        try {
            usersJson = new JSONArray(Files.readString(pathToJsonFile, StandardCharsets.UTF_8));
        } catch (IOException e) {
            Log.error("Failed to update JSON file at " +  pathToJsonFile.toString() + "!");
        }
    }

    public Path getPathToJsonFile() {
        return pathToJsonFile;
    }
}
