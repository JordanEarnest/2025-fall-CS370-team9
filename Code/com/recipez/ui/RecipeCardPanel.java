package com.recipez.ui;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RecipeCardPanel extends JButton
{
    private Dimension cardDimension = new Dimension(200, 120);

    private JLabel recipeNameLabel;

    private String recipeName;

    private boolean isPlusButton;

    public RecipeCardPanel(String recipeName, boolean isPlusButton)
    {
        this.recipeName = recipeName;
        this.isPlusButton = isPlusButton;
        setLayout(new BorderLayout());
        setPreferredSize(cardDimension);
        setMinimumSize(cardDimension);
        setMaximumSize(cardDimension);

        if (isPlusButton) {
            recipeNameLabel = new JLabel("+");

            recipeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            recipeNameLabel.setVerticalAlignment(SwingConstants.CENTER);

            String recipeNameLabelStyle = "font:bold 50; foreground:#FFE96B;";

            recipeNameLabel.putClientProperty(FlatClientProperties.STYLE, recipeNameLabelStyle);

            // change actual card client properties to rounded corners etc
            String cardStyle = "arc:20; borderWidth: 4; borderColor: #FFE96B; background:#121417; focusedBackground:#121417";

            putClientProperty(FlatClientProperties.STYLE, cardStyle);

            add(recipeNameLabel, BorderLayout.CENTER);


            return;
        }

        recipeNameLabel = new JLabel(recipeName);

        recipeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recipeNameLabel.setVerticalAlignment(SwingConstants.CENTER);


        String recipeNameLabelStyle = "font:bold 20; foreground:#121417;";

        recipeNameLabel.putClientProperty(FlatClientProperties.STYLE, recipeNameLabelStyle);

        // change actual card client properties to rounded corners etc
        String cardStyle = "arc:20; borderWidth: 0; background:#FFE96B; focusedBackground:#FFE96B";

        putClientProperty(FlatClientProperties.STYLE, cardStyle);

        add(recipeNameLabel, BorderLayout.CENTER);
    }

    public String getRecipeName() {
        return recipeName;
    }

    public boolean getIsPlusButton()
    {
        return isPlusButton;
    }
}
