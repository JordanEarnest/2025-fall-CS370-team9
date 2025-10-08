package com.recipez.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.recipez.user.User;

import javax.swing.*;
import java.awt.*;

public class Dashboard {

    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel topPanel; // Header for application
    // Center Panel should be a card layout eventually to swap between UI depending on what the user presses
    // on side panel.
    private JPanel centerPanel;

    private User activeUser;

    public Dashboard(User activeUser) {
        this.activeUser = activeUser;
        // Main panel to fit in JFrame, can add additional panels to EAST, WEST, NORTH, and SOUTH directions
        mainPanel =  new JPanel(new BorderLayout());

        // Create overall margin for application
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top Panel ---------------------
//        topPanel = new JPanel();
//        topPanel.setLayout(new BorderLayout());
//
//        // JPanel with FlowLayout for user info on top right in header
//        JPanel userInfoPanel = new JPanel();
//        userInfoPanel.setLayout(new FlowLayout());
//
//        JLabel appNameLabel = new JLabel();
//        ImageIcon appTextLogoImage = new ImageIcon(getClass().getResource("/assets/textlogo.png"));
//        Image appTextLogoImageScaled = appTextLogoImage.getImage().getScaledInstance(187, 50, Image.SCALE_SMOOTH);
//        appNameLabel.setIcon(new ImageIcon(appTextLogoImageScaled));
//        appNameLabel.putClientProperty("FlatLaf.style", "font: 20");
//        JLabel userInfoLabel = new JLabel("Logged in as " + activeUser.getName());
//        userInfoLabel.putClientProperty("FlatLaf.style", "font: 14");
//        JButton logoutButton = new JButton("Logout");
//        JSeparator separator = new JSeparator();
//
//        userInfoPanel.add(userInfoLabel);
//        userInfoPanel.add(logoutButton);
//
//        topPanel.add(appNameLabel, BorderLayout.WEST);
//        topPanel.add(userInfoPanel, BorderLayout.EAST);
//        topPanel.add(separator, BorderLayout.SOUTH);
//        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Side Panel ------------------
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        sidePanel.setBackground(new Color(0xD8E8D1));
        JLabel appNameLabel = new JLabel();
        ImageIcon appTextLogoImage = new ImageIcon(getClass().getResource("/assets/textlogo.png"));
        Image appTextLogoImageScaled = appTextLogoImage.getImage().getScaledInstance(187, 48, Image.SCALE_SMOOTH);
        appNameLabel.setIcon(new ImageIcon(appTextLogoImageScaled));
        appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        appNameLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 25, 0));
        sidePanel.add(appNameLabel);


        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Dashboard");
        model.addElement("My Recipes");
        model.addElement("Favorites");
        model.addElement("Settings");
        model.addElement("Log Out");
        JList<String> dashboardSelectionList = new JList<>(model);

        dashboardSelectionList.putClientProperty("FlatLaf.style", "font: 20");

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) dashboardSelectionList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);


        dashboardSelectionList.setFixedCellHeight(40);
        dashboardSelectionList.setFixedCellWidth(200);
        dashboardSelectionList.setBackground(new Color(0xD8E8D1));
        dashboardSelectionList.setForeground(new Color(0x2F3B2C));
        dashboardSelectionList.setSelectionBackground(new Color(0xA6C4A0));
        dashboardSelectionList.setSelectionForeground(new Color(0x003300));

        sidePanel.add(dashboardSelectionList);

        mainPanel.add(sidePanel, BorderLayout.WEST);

        // Center Panel -------------------
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(new Color(0xEDF4E9));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Place within borderlayout center another panel of a box layout vertical to add widgets
        JPanel widgetsPanel = new JPanel();
        widgetsPanel.setLayout(new GridLayout(2, 1, 0, 0));
        // Flow layout left to right showing max recipes, favorites etc
        JPanel overviewStatsPanel  = new JPanel();
        overviewStatsPanel.setLayout(new GridBagLayout());
        overviewStatsPanel.setBackground(new Color(0xEDF4E9));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 100, 0, 100);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        JPanel maxRecipesPanel = new JPanel();
        maxRecipesPanel.setLayout(new BoxLayout(maxRecipesPanel, BoxLayout.Y_AXIS));
        maxRecipesPanel.setBackground(new Color(0xD8E8D1));
        maxRecipesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel totalRecipesCountLabel = new JLabel("" + activeUser.getRecipes().size());
        totalRecipesCountLabel.putClientProperty("FlatLaf.style", "font: bold 70");
        totalRecipesCountLabel.setForeground(new Color(0x003300));
        totalRecipesCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalRecipesCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel totalRecipesLabel = new JLabel("Total Recipes");
        totalRecipesLabel.putClientProperty("FlatLaf.style", "font: 20");
        totalRecipesLabel.setForeground(new Color(0x003300));
        maxRecipesPanel.add(totalRecipesCountLabel);
        maxRecipesPanel.add(totalRecipesLabel);
        totalRecipesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        overviewStatsPanel.add(maxRecipesPanel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;

        JPanel favoriteRecipesPanel = new JPanel();
        favoriteRecipesPanel.setLayout(new BoxLayout(favoriteRecipesPanel, BoxLayout.Y_AXIS));
        favoriteRecipesPanel.setBackground(new Color(0xD8E8D1));
        favoriteRecipesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel favoriteRecipesCountLabel = new JLabel("0");
        favoriteRecipesCountLabel.setForeground(new Color(0x003300));
        favoriteRecipesCountLabel.putClientProperty("FlatLaf.style", "font: bold 70");
        favoriteRecipesCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        favoriteRecipesPanel.add(favoriteRecipesCountLabel);

        JLabel favoriteRecipesLabel = new JLabel("Favorites");
        favoriteRecipesLabel.setForeground(new Color(0x003300));
        favoriteRecipesLabel.putClientProperty("FlatLaf.style", "font: 20");
        favoriteRecipesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        favoriteRecipesPanel.add(favoriteRecipesLabel);

        overviewStatsPanel.add(favoriteRecipesPanel);

        gbc.gridx = 2; gbc.gridy = 0;

        JPanel newRecipesThisWeekPanel = new JPanel();
        newRecipesThisWeekPanel.setLayout(new BoxLayout(newRecipesThisWeekPanel, BoxLayout.Y_AXIS));
        newRecipesThisWeekPanel.setBackground(new Color(0xD8E8D1));
        newRecipesThisWeekPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel newRecipesThisWeekCountLabel = new JLabel("0");
        newRecipesThisWeekCountLabel.setForeground(new Color(0x003300));
        newRecipesThisWeekCountLabel.putClientProperty("FlatLaf.style", "font: bold 70");
        newRecipesThisWeekCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newRecipesThisWeekPanel.add(newRecipesThisWeekCountLabel);

        JLabel newRecipesThisWeekLabel = new JLabel("New Recipes This Week");
        newRecipesThisWeekLabel.putClientProperty("FlatLaf.style", "font: 20");
        newRecipesThisWeekLabel.setForeground(new Color(0x003300));
        newRecipesThisWeekLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newRecipesThisWeekPanel.add(newRecipesThisWeekLabel);

        overviewStatsPanel.add(newRecipesThisWeekPanel, gbc);


        JLabel welcomeLabel = new JLabel("Welcome back, " + activeUser.getName());
        welcomeLabel.putClientProperty("FlatLaf.style", "font: bold 40");
        welcomeLabel.setForeground(new Color(0x003300));
        centerPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel recipesInfoPanel = new JPanel();
        recipesInfoPanel.setBackground(new Color(0xFF0000));


        centerPanel.add(widgetsPanel, BorderLayout.CENTER);
        widgetsPanel.add(overviewStatsPanel);
        widgetsPanel.add(recipesInfoPanel);



        mainPanel.add(centerPanel, BorderLayout.CENTER);

    }



    public JPanel getMainPanel() {
        return mainPanel;
    }
}
