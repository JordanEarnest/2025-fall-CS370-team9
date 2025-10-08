package com.recipez.core;

import com.formdev.flatlaf.FlatLightLaf;
import com.recipez.ui.AuthenticationUI;
import com.recipez.ui.DashboardUI;
import com.recipez.ui.Window;
import com.recipez.user.User;
import com.recipez.util.Log;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;


public class Application {

    private final String TITLE = "RecipeZ";
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private Window window;

    private User activeUser;

    private DashboardUI dashboardUI;

    private AuthenticationUI authenticationUI;


    public Application() {
        FlatLightLaf.setup(); // Setup better look and feel (UI) for Swing
        UIManager.put("defaultFont", new FontUIResource("Verdana", Font.PLAIN, 12)); // Make default font Verdana
        window = new Window(TITLE, WIDTH, HEIGHT);


        authenticationUI = new AuthenticationUI();

        window.add(authenticationUI.getAuthenticationPanel());


        window.display();
        //loginUser(new User("Jordan", "123"));

        //activeUser.getUserManager().removeRecipe("Salad");

/*        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Lettuce", 1, MeasurementType.CUP));
        activeUser.getUserManager().storeRecipe(new Recipe("Salad", "A healthy snack.", "Toss in a bowl.", ingredients, 100));*/

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
