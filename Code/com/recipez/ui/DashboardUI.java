package com.recipez.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.recipez.core.Application;
import com.recipez.recipe.Ingredient;
import com.recipez.recipe.MeasurementType;
import com.recipez.recipe.Recipe;
import com.recipez.recipe.RecipeSearch;
import com.recipez.user.BodyGoal;
import com.recipez.user.User;
import com.recipez.util.DietType;
import com.recipez.util.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
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
    private JButton returnButton;

    private JButton searchButton;
    private JButton filterButton;

    private JTextField searchField;


    private JLabel rightSideBarHeaderLabel;
    private JTextArea recipeInfoLabel;

    private JButton removeRecipeButton;

    private String currentSelectedRecipeName = null;
    private RecipeCardPanel currentSelectedCardPanel;


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
                populateRecipeGridPanel(Application.activeUser.getRecipes());

                centerPanelCardLayout.show(centerPanelCardContainer, "center");

                JOptionPane.showMessageDialog(createRecipePanel, "Recipe saved successfully!");

                // reset all text entry
                recipeTitleTextField.setText("");
                descriptionTextField.setText("");
                instructionsTextField.setText("");
                caloriesTextField.setText("");

                ingredientListPanel.removeAll();
                addIngredientRow.run(); // always make sure theres at least one


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

        leftSideBarPanel = new JPanel(new BorderLayout());
        //leftSideBarPanel.setLayout(new BoxLayout(leftSideBarPanel, BoxLayout.Y_AXIS));
        //leftSideBarPanel.add(Box.createVerticalStrut(10));
        leftSideBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

        recipeInfoLabel = new JTextArea("no recipe selected");
        recipeInfoLabel.setEditable(false);
        recipeInfoLabel.setLineWrap(true);
        recipeInfoLabel.setWrapStyleWord(true);

        recipeInfoLabel.putClientProperty(
                FlatClientProperties.STYLE,
                "font: 18; foreground:#C5D1BA; background:#252A2F"
        );

        // add scroll if text becomes long
        JScrollPane scroll = new JScrollPane(recipeInfoLabel);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        scroll.setBackground(Color.decode("#252A2F"));





        ImageIcon homeButtonIcon = new ImageIcon(getClass().getResource("/assets/home.png"));
        Image scaledHomeButtonIcon = homeButtonIcon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);

        homeButton = new JButton(new ImageIcon(scaledHomeButtonIcon));
        homeButton.setFocusPainted(false);
        homeButton.setPreferredSize(new Dimension(100, 100));
        homeButton.setMinimumSize(new Dimension(100, 100));
        homeButton.setMaximumSize(new Dimension(100, 100));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // return button to log out
        ImageIcon returnButtonIcon = new ImageIcon(getClass().getResource("/assets/return.png"));
        Image scaledReturnButtonIcon = returnButtonIcon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);

        returnButton = new JButton(new ImageIcon(scaledReturnButtonIcon));
        returnButton.setFocusPainted(false);
        returnButton.setPreferredSize(new Dimension(100, 100));
        returnButton.setMinimumSize(new Dimension(100, 100));
        returnButton.setMaximumSize(new Dimension(100, 100));
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon searchButtonIcon = new ImageIcon(getClass().getResource("/assets/Search.png"));
        Image scaledSearchButtonIcon = searchButtonIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);

        searchButton = new JButton(new ImageIcon(scaledSearchButtonIcon));
        searchButton.setFocusPainted(false);
        searchButton.setPreferredSize(new Dimension(50, 50));
        searchButton.setMinimumSize(new Dimension(50, 50));
        searchButton.setMaximumSize(new Dimension(50, 50));

        // filter button
        ImageIcon filterButtonIcon = new ImageIcon(getClass().getResource("/assets/dropdownButton.png"));
        Image scaledFilterButtonIcon = filterButtonIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);

        filterButton = new JButton(new ImageIcon(scaledFilterButtonIcon));
        filterButton.setFocusPainted(false);
        filterButton.setPreferredSize(new Dimension(50, 50));
        filterButton.setMinimumSize(new Dimension(50, 50));
        filterButton.setMaximumSize(new Dimension(50, 50));




        removeRecipeButton = new JButton("Remove Recipe");
        removeRecipeButton.setFocusPainted(false);

        String homeButtonStyle =
                "arc:20;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#00000000;"
                        + "hoverBackground: #757E75;"
                        + "borderWidth: 0;"
                        + "focusWidth:0;"
                        + "margin:5,5,5,5;";

        String removeRecipeButtonStyle = "arc:20;"
                + "font:bold 18;"
                + "foreground:#C5D1BA;"
                + "background:#252A2F;"
                + "borderColor:#C5D1BA;"
                + "borderWidth: 2;"
                + "margin: 5,5,5,5;";

        removeRecipeButton.putClientProperty(FlatClientProperties.STYLE, removeRecipeButtonStyle);

        homeButton.putClientProperty(FlatClientProperties.STYLE,  homeButtonStyle);
        returnButton.putClientProperty(FlatClientProperties.STYLE,  homeButtonStyle);
        filterButton.putClientProperty(FlatClientProperties.STYLE,  homeButtonStyle);


        String searchButtonStyle =
                "arc:20;"
                        + "font:bold;"
                        + "foreground:#C5D1BA;"
                        + "background:#00000000;"
                        + "hoverBackground: #757E75;"
                        + "borderWidth: 0;"
                        + "focusWidth:0;"
                        + "margin:5,5,5,5;";


        searchButton.putClientProperty(FlatClientProperties.STYLE,  searchButtonStyle);

        leftSideBarPanel.add(homeButton, BorderLayout.NORTH);
        leftSideBarPanel.add(returnButton, BorderLayout.SOUTH);
        searchBarPanel.add(filterButton, BorderLayout.WEST);

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


        //populateRecipeGridPanel(Application.activeUser.getRecipes());

        // when home button is pressed
        homeButton.addActionListener(e -> {
            // Rebuild recipe cards so the UI updates
            populateRecipeGridPanel(Application.activeUser.getRecipes());

            // Switch to the center "home" panel
            centerPanelCardLayout.show(centerPanelCardContainer, "center");

            // Revalidate + repaint to ensure UI updates visually
            centerPanelCardContainer.revalidate();
            centerPanelCardContainer.repaint();
        });

        searchButton.addActionListener(e -> {
            // Rebuild recipe cards so the UI updates
            if (!searchField.getText().isBlank())
                populateRecipeGridPanel(RecipeSearch.searchByString(Application.activeUser.getRecipes(),  searchField.getText()));
            else
                populateRecipeGridPanel(Application.activeUser.getRecipes());


            // Switch to the center "home" panel

            // Revalidate + repaint to ensure UI updates visually
            centerPanel.revalidate();
            centerPanel.repaint();
        });

        filterButton.addActionListener(e -> openFilterDialog());

        returnButton.addActionListener(e -> {
            // Rebuild recipe cards so the UI updates
            //populateRecipeGridPanel();

            // Switch to the center "home" panel
            Application.activeInstance.logout();
            recipeInfoLabel.setText("");


        });

        removeRecipeButton.addActionListener(e -> {
            // Rebuild recipe cards so the UI updates
            //populateRecipeGridPanel();

            // Switch to the center "home" panel
            if (currentSelectedRecipeName != null) {
                Application.activeUser.getUserManager().removeRecipe(currentSelectedRecipeName);
                recipeGridPanel.remove(currentSelectedCardPanel);
            }

            centerPanel.revalidate();
            centerPanel.repaint();
            recipeInfoLabel.setText("no recipe selected");

        });



        searchBarPanel.add(searchField, BorderLayout.CENTER);
        searchBarPanel.add(searchButton, BorderLayout.EAST);

        centerPanel.add(searchBarPanel, BorderLayout.NORTH);
        centerPanel.add(recipeGridPanel, BorderLayout.CENTER);

        rightSideBarPanel.add(rightSideBarHeaderLabel, BorderLayout.NORTH);
        rightSideBarPanel.add(scroll, BorderLayout.CENTER);
        rightSideBarPanel.add(removeRecipeButton, BorderLayout.SOUTH);
        rightSideBarPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        centerPanelCardLayout.show(centerPanelCardContainer, "center");

        mainPanel.add(centerPanelCardContainer, BorderLayout.CENTER);
        mainPanel.add(rightSideBarPanel, BorderLayout.EAST);
        mainPanel.add(leftSideBarPanel, BorderLayout.WEST);

        // Create overall margin for application
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10))


    }

    private final ActionListener recipeCardClickListener = e -> {
        currentSelectedCardPanel = (RecipeCardPanel) e.getSource();

        if (currentSelectedCardPanel.getIsPlusButton()) {
            centerPanelCardLayout.show(centerPanelCardContainer, "create");
            return;
        }
        // Normal recipe card
        String recipeName = currentSelectedCardPanel.getRecipeName();

        Recipe target = null;
        // find a match
        for (Recipe recipe : Application.activeUser.getRecipes()) {
            if (recipe.getName().equals(recipeName)) {
                target = recipe;
                currentSelectedRecipeName = target.getName();
                break;
            }
        }
        if (target != null) {
            String details =
                    "Name: " + target.getName() + "\n\n" +
                            "Description:\n" + target.getDescription() + "\n\n" +
                            "Instructions:\n" + target.getInstructions() + "\n\n" +
                            "Calories: " + target.getCalories() + "\n\n" +
                            "Diet Type: " + target.getDietType().toString() + "\n\n" +
                            "Ingredients:\n";

            for (Ingredient ing : target.getIngredients()) {
                details += "- " + ing.getQuantifier() + " " + ing.getMeasurementType() +
                        " " + ing.getName() + "\n";
            }


            recipeInfoLabel.setText(details);
        }
        //Application.activeUser.getRecipes()
    };

    public void populateRecipeGridPanel(List<Recipe> recipes) {
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
        for (Recipe recipe : recipes) {
            RecipeCardPanel recipePanelButtonN = new RecipeCardPanel(recipe.getName(), false);
            recipeCards.add(recipePanelButtonN);
            recipePanelButtonN.addActionListener(recipeCardClickListener);
            recipeGridPanel.add(recipeCards.get(i));
            i++;
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void openFilterDialog() {
        // Styled dialog window
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(centerPanel), "Filter", true);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(Color.decode("#121417"));
        dialog.setSize(300, 220);
        dialog.setLocationRelativeTo(centerPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label
        JLabel sortLabel = new JLabel("Filter Options");
        sortLabel.putClientProperty(FlatClientProperties.STYLE,
                "foreground:#C5D1BA; font:bold 16;");
        dialog.add(sortLabel, gbc);

        // Dropdown options
        gbc.gridy++;
        String[] sortOptions = {
                "None",
                "Calories Ascending",
                "Calories Descending",
                "Alphabetical Ascending",
                "Alphabetical Descending",
                "Vegetarian Only",
                "Personalized"
        };

        JComboBox<String> sortCombo = new JComboBox<>(sortOptions);
        sortCombo.putClientProperty(FlatClientProperties.STYLE,
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
                        + "padding: 5,5,5,5;");
        dialog.add(sortCombo, gbc);

        // Confirm button
        gbc.gridy++;
        JButton confirmButton = new JButton("Apply Filter");
        confirmButton.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;"
                        + "font:bold 18;"
                        + "foreground:#C5D1BA;"
                        + "background:#252A2F;"
                        + "borderColor:#C5D1BA;"
                        + "borderWidth: 2;"
                        + "margin: 5,5,5,5;");
        dialog.add(confirmButton, gbc);

        // Logic when confirm is clicked
        confirmButton.addActionListener(ev -> {
            String selection = sortCombo.getSelectedItem().toString();

            List<Recipe> recipes = Application.activeUser.getRecipes();

            List<Recipe> filtered = new ArrayList<>();

            if (selection.equals("None")) {
                filtered = recipes;
            } else if(selection.equals("Calories Ascending")) {
                filtered = RecipeSearch.quickSortCaloriesAscending(recipes, 0, recipes.size() - 1);
            } else if (selection.equals("Calories Descending")) {
                filtered = RecipeSearch.quickSortCaloriesDescending(recipes, 0, recipes.size() - 1);
            } else if (selection.equals("Alphabetical Ascending")) {
                filtered = RecipeSearch.quickSortNameAscending(recipes, 0, recipes.size() - 1);
            } else if (selection.equals("Alphabetical Descending")) {
                filtered = RecipeSearch.quickSortNameDescending(recipes, 0, recipes.size() - 1);
            } else if (selection.equals("Vegetarian Only")) {
                filtered = RecipeSearch.filterByDietType(recipes, DietType.VEGETARIAN);
            } else if (selection.equals("Personalized")) {
                if (Application.activeUser.getDietType() != DietType.NONE) {
                    filtered = RecipeSearch.filterByDietType(recipes, Application.activeUser.getDietType());
                }
                if (Application.activeUser.getBodyGoal() == BodyGoal.BULK) {
                    filtered = RecipeSearch.quickSortCaloriesDescending(filtered, 0, filtered.size() - 1);
                } else if (Application.activeUser.getBodyGoal() == BodyGoal.CUT) {
                    filtered = RecipeSearch.quickSortCaloriesAscending(filtered, 0, filtered.size() - 1);
                }
            }

            populateRecipeGridPanel(filtered);
            centerPanel.revalidate();
            centerPanel.repaint();

            dialog.dispose();
        });

        dialog.setVisible(true);
    }




    public JPanel getMainPanel() {
        return mainPanel;
    }



}
