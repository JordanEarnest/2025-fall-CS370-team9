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

    private User user;

    public Application() {
        initWindow();

        user = new User("Jordan", "123", BodyGoal.MAINTAIN, DietType.NONE, 125, 5.583, 19, true);
    }

    private void initWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1280, 720);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
