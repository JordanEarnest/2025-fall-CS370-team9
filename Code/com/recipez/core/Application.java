package com.recipez.core;

import com.formdev.flatlaf.FlatLightLaf;
import com.recipez.recipe.Ingredient;
import com.recipez.recipe.MeasurementType;
import com.recipez.recipe.Recipe;
import com.recipez.ui.AuthenticationUI;
import com.recipez.ui.DashboardUI;
import com.recipez.ui.Window;
import com.recipez.user.User;
import com.recipez.user.UserManager;
import com.recipez.util.DietType;
import com.recipez.util.Log;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Application {

    private final String TITLE = "RecipeZ";
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private Window window;

    public static User activeUser;

    public DashboardUI dashboardUI;

    private AuthenticationUI authenticationUI;

    private CardLayout applicationCardLayout;

    private JPanel applicationPanel;

    public static Application activeInstance;


    public Application() {
        activeInstance = this;
        FlatLightLaf.setup(); // Setup better look and feel (UI) for Swing
        UIManager.put("defaultFont", new FontUIResource("Verdana", Font.PLAIN, 12)); // Make default font Verdana
        UIManager.put("ComboBox.selectionBackground", Color.decode("#121417"));
        UIManager.put("ComboBox.selectionForeground", Color.decode("#C5D1BA"));
        window = new Window(TITLE, WIDTH, HEIGHT);

        applicationCardLayout = new CardLayout();
        applicationPanel = new JPanel(applicationCardLayout);

        authenticationUI = new AuthenticationUI();
        dashboardUI = new DashboardUI();

        applicationPanel.add(authenticationUI.getAuthenticationPanel(), "auth");
        applicationPanel.add(dashboardUI.getMainPanel(), "dashboard");

        applicationCardLayout.show(applicationPanel, "auth");

        window.add(applicationPanel);

        //loginUser(new User("Jordan", "123"));

        window.display();
        //loginUser(new User("Jordan", "123"));

        //activeUser.getUserManager().removeRecipe("Salad");

    /*   List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Lettuce", 1, MeasurementType.CUP));
        activeUser.getUserManager().storeRecipe(new Recipe("Salad", "A healthy snack.", "Toss in a bowl.", ingredients, 100, DietType.VEGETARIAN));*/

    }


    private void registerUser(User user) {
        Log.info("Successfully registered user with name " + user.getName());
    }

    public void loginUser(User user) {
        UserManager um = user.getUserManager();
        String typedPassword = user.getPassword();

        if (um.checkPassword(typedPassword)) {
            activeUser = user;
            Log.info("Successfully logged in user with name " + user.getName());

            dashboardUI.populateRecipeGridPanel();

            // Switch UI
            applicationCardLayout.show(applicationPanel, "dashboard");

        } else {
            Log.warning("Failed to login user with name " + user.getName());
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
        }
    }

    public DashboardUI getDashboardUI() {
        return dashboardUI;
    }

}
