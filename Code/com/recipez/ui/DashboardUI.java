package com.recipez.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.recipez.core.Application;
import com.recipez.recipe.Ingredient;
import com.recipez.recipe.MeasurementType;
import com.recipez.recipe.Recipe;
import com.recipez.user.User;
import com.recipez.util.DietType;
import com.recipez.util.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DashboardUI {

    private JPanel mainPanel;
    private JPanel leftSideBarPanel;

    private JPanel rightSideBarPanel;


    private JPanel centerPanelCardContainer;
    private CardLayout centerPanelCardLayout;
    private JPanel centerPanel;
    private JPanel createRecipePanel;

    private JPanel searchBarPanel;

    private JPanel recipeGridPanel;

    private JButton homeButton;

    private JButton filterButton;

    private JTextField searchField;


    private JLabel rightSideBarHeaderLabel;


    private List<RecipeCardPanel> recipeCards;

    public DashboardUI() {
        // Main panel to fit in JFrame, can add additional panels to EAST, WEST, NORTH, and SOUTH directions
        mainPanel =  new JPanel(new BorderLayout());
        recipeCards = new ArrayList<>();


        centerPanelCardLayout = new CardLayout();
        centerPanelCardContainer = new JPanel(centerPanelCardLayout);


        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.decode("#121417"));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        centerPanelCardContainer.add(centerPanel, "center");

        createRecipePanel = new JPanel(new GridBagLayout());
        createRecipePanel.setBackground(Color.decode("#121417"));
        createRecipePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing between components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // center all components
        gbc.fill = GridBagConstraints.HORIZONTAL; // widen components nicely

        // create recipe ui and functionality ////////////////////////////////////
        gbc.gridwidth = 2;
        JLabel title = new JLabel("Create Recipe");
        title.putClientProperty(FlatClientProperties.STYLE, "font: bold 25; foreground:#C5D1BA;");
        createRecipePanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        JLabel recipeTitle = new JLabel("Recipe Title");
        recipeTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold; foreground:#C5D1BA;");
        createRecipePanel.add(recipeTitle, gbc);

        gbc.gridx++;
        JTextField recipeTitleTextField = new JTextField();
        recipeTitleTextField.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "background:#121417;" +
                        "foreground:#C5D1BA;" +
                        "caretColor:#C5D1BA;" +
                        "borderColor:#C5D1BA;"
                        + "borderWidth: 2;"
                        + "margin: 5,5,5,5;"
        );
        createRecipePanel.add(recipeTitleTextField, gbc);

        // next row
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel descriptionTitle = new JLabel("Description");
        descriptionTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold; foreground:#C5D1BA;");
        createRecipePanel.add(descriptionTitle, gbc);

        gbc.gridx = 1;
        JTextField descriptionTextField = new JTextField();
        descriptionTextField.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "background:#121417;" +
                        "foreground:#C5D1BA;" +
                        "caretColor:#C5D1BA;" +
                        "borderColor:#C5D1BA;"
                        + "borderWidth: 2;"
                        + "margin: 5,5,5,5;"

        );
        createRecipePanel.add(descriptionTextField, gbc);

        // next row
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel instructionsTitle = new JLabel("Instructions");
        instructionsTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold; foreground:#C5D1BA;");
        createRecipePanel.add(instructionsTitle, gbc);

        gbc.gridx = 1;
        JTextField instructionsTextField = new JTextField();
        instructionsTextField.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "background:#121417;" +
                        "foreground:#C5D1BA;" +
                        "caretColor:#C5D1BA;" +
                        "borderColor:#C5D1BA;"
                        + "borderWidth: 2;"
                        + "margin: 5,5,5,5;"

        );
        createRecipePanel.add(instructionsTextField, gbc);
        // next row - calories
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel caloriesTitle = new JLabel("Calories");
        caloriesTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold; foreground:#C5D1BA;");
        createRecipePanel.add(caloriesTitle, gbc);

        gbc.gridx = 1;
        JTextField caloriesTextField = new JTextField();
        caloriesTextField.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "background:#121417;" +
                        "foreground:#C5D1BA;" +
                        "caretColor:#C5D1BA;" +
                        "borderColor:#C5D1BA;" +
                        "borderWidth:2;" +
                        "margin:5,5,5,5;"
        );
        createRecipePanel.add(caloriesTextField, gbc);

        // next row - diet type
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel dietTypeTitle = new JLabel("Diet Type");
        dietTypeTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold; foreground:#C5D1BA;");
        createRecipePanel.add(dietTypeTitle, gbc);

        gbc.gridx = 1;
        JComboBox<DietType> dietTypeComboBox = new JComboBox<>(DietType.values());
        dietTypeComboBox.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;"
                        + "background:#121417;"
                        + "foreground:#C5D1BA;"
                        + "borderColor:#C5D1BA;"
                        + "borderWidth:2;"
                        + "focusWidth:0;"
                        + "buttonArrowColor:#C5D1BA;"
                        + "buttonBackground:#252A2F;"
                        + "popupBackground:#121417;"
                        + "buttonEditableBackground:#121417;"
                        + "padding: 5,5,5,5;"

        );
        createRecipePanel.add(dietTypeComboBox, gbc);

// INGREDIENT SECTION ////////////////////////////////////////////////////////////
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        JLabel ingredientTitle = new JLabel("Ingredients");
        ingredientTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold; foreground:#C5D1BA;");
        createRecipePanel.add(ingredientTitle, gbc);

// panel to hold dynamic ingredient rows
        gbc.gridy++;
        JPanel ingredientListPanel = new JPanel(new GridBagLayout());
        ingredientListPanel.setBackground(Color.decode("#121417"));
        createRecipePanel.add(ingredientListPanel, gbc);

// controls placement inside ingredientListPanel
        GridBagConstraints igbc = new GridBagConstraints();
        igbc.insets = new Insets(5, 5, 5, 5);
        igbc.anchor = GridBagConstraints.CENTER;

// method helper to add ingredient row
        Runnable addIngredientRow = new Runnable() {
            @Override
            public void run() {
                int row = ingredientListPanel.getComponentCount() / 3;

                // ingredient name
                igbc.gridx = 0;
                igbc.gridy = row;
                JTextField ingredientNameField = new JTextField(10);
                ingredientNameField.putClientProperty(FlatClientProperties.STYLE,
                        "arc:20;" +
                                "background:#121417;" +
                                "foreground:#C5D1BA;" +
                                "caretColor:#C5D1BA;" +
                                "borderColor:#C5D1BA;" +
                                "borderWidth:2;" +
                                "margin:5,5,5,5;"
                );
                ingredientListPanel.add(ingredientNameField, igbc);

                // quantifier
                igbc.gridx = 1;
                JTextField quantifierField = new JTextField(5);
                quantifierField.putClientProperty(FlatClientProperties.STYLE,
                        "arc:20;" +
                                "background:#121417;" +
                                "foreground:#C5D1BA;" +
                                "caretColor:#C5D1BA;" +
                                "borderColor:#C5D1BA;" +
                                "borderWidth:2;" +
                                "margin:5,5,5,5;"
                );
                ingredientListPanel.add(quantifierField, igbc);

                // measurement
                igbc.gridx = 2;
                JComboBox<MeasurementType> measurementBox = new JComboBox<>(MeasurementType.values());
                measurementBox.putClientProperty(FlatClientProperties.STYLE,
                        "arc:20;"
                                + "background:#121417;"
                                + "foreground:#C5D1BA;"
                                + "borderColor:#C5D1BA;"
                                + "borderWidth:2;"
                                + "focusWidth:0;"
                                + "buttonArrowColor:#C5D1BA;"
                                + "buttonBackground:#252A2F;"
                                + "popupBackground:#121417;"
                                + "buttonEditableBackground:#121417;"
                                + "padding: 5,5,5,5;"

                );
                ingredientListPanel.add(measurementBox, igbc);

                ingredientListPanel.revalidate();
                ingredientListPanel.repaint();
            }
        };

// add first ingredient row automatically
        addIngredientRow.run();

// next row — add ingredient button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addIngredientButton = new JButton("Add Ingredient");
        addIngredientButton.setFocusable(false);
        addIngredientButton.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "background:#252A2F;" +
                        "borderWidth: 2;" +
                        "borderColor: #C5D1BA;" +
                        "foreground:#C5D1BA;" +
                        "font:bold;" +
                        "margin:5,5,5,5;"
        );
        addIngredientButton.addActionListener(e -> addIngredientRow.run());
        createRecipePanel.add(addIngredientButton, gbc);

// SAVE BUTTON ////////////////////////////////////////////////////////////////
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton saveRecipeButton = new JButton("Save Recipe");
        saveRecipeButton.setFocusable(false);
        saveRecipeButton.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "background:#121417;" +
                        "borderWidth: 2;" +
                        "borderColor: #C5D1BA;" +
                        "foreground:#C5D1BA;" +
                        "font:bold;" +
                        "margin:5,5,5,5;"
        );
        createRecipePanel.add(saveRecipeButton, gbc);

        // WHEN USER CLICKS SAVE RECIPE
        saveRecipeButton.addActionListener(e -> {
            try {
                //pull basic recipe information
                String name = recipeTitleTextField.getText().trim();
                String description = descriptionTextField.getText().trim();
                String instructions = instructionsTextField.getText().trim();
                String caloriesString = caloriesTextField.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(createRecipePanel, "Recipe title cannot be empty!");
                    return;
                }

                int calories;
                try {
                    calories = Integer.parseInt(caloriesString);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(createRecipePanel, "Calories must be a number!");
                    return;
                }

                DietType dietType = (DietType) dietTypeComboBox.getSelectedItem();

               // extra ingredient rows
                List<Ingredient> ingredients = new ArrayList<>();

                Component[] comps = ingredientListPanel.getComponents();

                for (int i = 0; i < comps.length; i += 3) {
                    JTextField ingredientNameField = (JTextField) comps[i];
                    JTextField quantifierField = (JTextField) comps[i + 1];
                    JComboBox<?> measurementBox = (JComboBox<?>) comps[i + 2];

                    String ingredientName = ingredientNameField.getText().trim();
                    String quantifierStr = quantifierField.getText().trim();

                    if (ingredientName.isEmpty()) {
                        JOptionPane.showMessageDialog(createRecipePanel, "Ingredient name cannot be empty!");
                        return;
                    }

                    float quantifier;
                    try {
                        quantifier = Float.parseFloat(quantifierStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(createRecipePanel, "Invalid number for ingredient quantity!");
                        return;
                    }

                    MeasurementType measurement =
                            (MeasurementType) measurementBox.getSelectedItem();

                    ingredients.add(new Ingredient(ingredientName, quantifier, measurement));
                }

                // create recipe object
                Recipe recipe = new Recipe(
                        name,
                        description,
                        instructions,
                        ingredients,
                        calories,
                        dietType
                );

                // store recipe in json data
                Application.activeUser.getUserManager().storeRecipe(recipe);

                // add recipe to data structure
                Application.activeUser.getRecipes().add(recipe);

                // repopulate grid layout for recipe cards
                populateRecipeGridPanel();

                centerPanelCardLayout.show(centerPanelCardContainer, "center");

                JOptionPane.showMessageDialog(createRecipePanel, "Recipe saved successfully!");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(createRecipePanel,
                        "An unexpected error occurred while saving the recipe.");
            }
        });



        centerPanelCardContainer.add(createRecipePanel, "create");

        recipeGridPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 15, 20));
        recipeGridPanel.setBackground(Color.decode("#121417"));

        searchBarPanel = new JPanel(new BorderLayout());
        searchBarPanel.setBackground(Color.decode("#121417"));

        leftSideBarPanel = new JPanel();
        leftSideBarPanel.setLayout(new BoxLayout(leftSideBarPanel, BoxLayout.Y_AXIS));
        leftSideBarPanel.add(Box.createVerticalStrut(10));
        leftSideBarPanel.setBackground(Color.decode("#252A2F"));

        leftSideBarPanel.setPreferredSize(new Dimension(120, Integer.MAX_VALUE));
        leftSideBarPanel.setMinimumSize(new Dimension(120, Integer.MAX_VALUE));
        leftSideBarPanel.setMaximumSize(new Dimension(120, Integer.MAX_VALUE));

        rightSideBarPanel = new JPanel();
        rightSideBarPanel.setLayout(new BorderLayout());
        rightSideBarPanel.setBackground(Color.decode("#252A2F"));


        rightSideBarPanel.setPreferredSize(new Dimension(250, Integer.MAX_VALUE));
        rightSideBarPanel.setMinimumSize(new Dimension(250, Integer.MAX_VALUE));
        rightSideBarPanel.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));

        String rightSideBarHeaderStyle = "font:bold 20; foreground:#C5D1BA;";
        rightSideBarHeaderLabel = new JLabel("Details");
        rightSideBarHeaderLabel.setHorizontalAlignment(JLabel.CENTER);

        rightSideBarHeaderLabel.putClientProperty(FlatClientProperties.STYLE, rightSideBarHeaderStyle);



        ImageIcon homeButtonIcon = new ImageIcon(getClass().getResource("/assets/home.png"));
        Image scaledHomeButtonIcon = homeButtonIcon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);

        homeButton = new JButton(new ImageIcon(scaledHomeButtonIcon));
        homeButton.setFocusPainted(false);
        homeButton.setPreferredSize(new Dimension(100, 100));
        homeButton.setMinimumSize(new Dimension(100, 100));
        homeButton.setMaximumSize(new Dimension(100, 100));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon filterButtonIcon = new ImageIcon(getClass().getResource("/assets/dropdownButton.png"));
        Image scaledfilterButtonIcon = filterButtonIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);

        filterButton = new JButton(new ImageIcon(scaledfilterButtonIcon));
        filterButton.setFocusPainted(false);
        filterButton.setPreferredSize(new Dimension(50, 50));
        filterButton.setMinimumSize(new Dimension(50, 50));
        filterButton.setMaximumSize(new Dimension(50, 50));
        //filterButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        String homeButtonStyle =
                "arc:20;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#00000000;"
                        + "hoverBackground: #757E75;"
                        + "borderWidth: 0;"
                        + "focusWidth:0;"
                        + "margin:5,5,5,5;";

        homeButton.putClientProperty(FlatClientProperties.STYLE,  homeButtonStyle);


        String filterButtonStyle =
                "arc:20;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#00000000;"
                        + "hoverBackground: #757E75;"
                        + "borderWidth: 0;"
                        + "focusWidth:0;"
                        + "margin:5,5,5,5;";


        filterButton.putClientProperty(FlatClientProperties.STYLE,  filterButtonStyle);

        leftSideBarPanel.add(homeButton);

        mainPanel.setBackground(Color.decode("#121417"));

        // add text entry for search bar
        searchField = new JTextField();

        String searchFieldStyle =
                "arc:40;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#121417;"
                        + "borderWidth: 4;"
                        + "borderColor: #C5D1BA;"
                        + "caretColor: #C5D1BA; "
                        + "focusWidth:0;"
                        + "margin:20,20,20,20;";

        searchField.putClientProperty(FlatClientProperties.STYLE, searchFieldStyle);


        populateRecipeGridPanel();

        // when home button is pressed
        homeButton.addActionListener(e -> {
            // Rebuild recipe cards so the UI updates
            populateRecipeGridPanel();

            // Switch to the center "home" panel
            centerPanelCardLayout.show(centerPanelCardContainer, "center");

            // Revalidate + repaint to ensure UI updates visually
            centerPanelCardContainer.revalidate();
            centerPanelCardContainer.repaint();
        });


        searchBarPanel.add(searchField, BorderLayout.CENTER);
        searchBarPanel.add(filterButton, BorderLayout.EAST);

        centerPanel.add(searchBarPanel, BorderLayout.NORTH);
        centerPanel.add(recipeGridPanel, BorderLayout.CENTER);

        rightSideBarPanel.add(rightSideBarHeaderLabel, BorderLayout.NORTH);

        centerPanelCardLayout.show(centerPanelCardContainer, "center");

        mainPanel.add(centerPanelCardContainer, BorderLayout.CENTER);
        mainPanel.add(rightSideBarPanel, BorderLayout.EAST);
        mainPanel.add(leftSideBarPanel, BorderLayout.WEST);

        // Create overall margin for application
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10))


    }

    private final ActionListener recipeCardClickListener = e -> {
        RecipeCardPanel card = (RecipeCardPanel) e.getSource();

        if (card.getIsPlusButton()) {
            centerPanelCardLayout.show(centerPanelCardContainer, "create");
            return;
        }
        // Normal recipe card
        String recipeName = card.getRecipeName();

        Recipe target = null;
        // find a match
        for (Recipe recipe : Application.activeUser.getRecipes()) {
            if (recipe.getName().equals(recipeName)) {
                target = recipe;
                break;
            }
        }
        if (target != null) {
            Log.info(target.getName());
        }
        //Application.activeUser.getRecipes()
    };

    public void populateRecipeGridPanel() {
        // reset recipe cards
        if (!recipeCards.isEmpty()) {
            for (RecipeCardPanel recipeCardPanel : recipeCards) {
                recipeGridPanel.remove(recipeCardPanel);
            }
            recipeCards.clear();
        }

        // every time it runs it resets and clears everything to repopulate cards
        // recipe cards always starts with one
        RecipeCardPanel recipePanelButton = new RecipeCardPanel("null", true);
        recipeCards.add(recipePanelButton);
        recipePanelButton.addActionListener(recipeCardClickListener);
        recipeGridPanel.add(recipeCards.get(0));

        if (Application.activeUser == null)
            return;

        int i = 1;
        for (Recipe recipe : Application.activeUser.getRecipes()) {
            RecipeCardPanel recipePanelButtonN = new RecipeCardPanel(recipe.getName(), false);
            recipeCards.add(recipePanelButtonN);
            recipePanelButtonN.addActionListener(recipeCardClickListener);
            recipeGridPanel.add(recipeCards.get(i));
            i++;
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }



    public JPanel getMainPanel() {
        return mainPanel;
    }



}
