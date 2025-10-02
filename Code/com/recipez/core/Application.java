package com.recipez.core;

import com.recipez.recipe.Ingredient;
import com.recipez.recipe.MeasurementType;
import com.recipez.recipe.Recipe;
import com.recipez.user.BodyGoal;
import com.recipez.user.DietType;
import com.recipez.user.User;
import com.recipez.user.UserManager;
import com.recipez.util.Log;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private JFrame window;

    private User activeUser;

    public Application() {
        initWindow();

        loginUser(new User("Jordan", "123"));

        activeUser.getUserManager().removeRecipe("Salad");

/*        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Lettuce", 1, MeasurementType.CUP));
        activeUser.getUserManager().storeRecipe(new Recipe("Salad", "A healthy snack.", "Toss in a bowl.", ingredients, 100));*/

    }

    private void initWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1280, 720);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private void registerUser(User user) {
        Log.info("Successfully registered user with name " + user.getName());
    }

    private void loginUser(User user) {
       if (user.getUserManager().checkPassword(user.getPassword())) {
            // Login user, set active user to this user
           activeUser = user;
           Log.info("Successfully logged in user with name " + user.getName());
       } else {
           Log.warning("Failed to login user with name " + user.getName());
       }
    }

}
