package com.recipez.ui;

import javax.swing.*;
import java.awt.*;

public class Authentication {

    private CardLayout authenticationCardLayout;

    private JPanel authenticationPanel;

    private JPanel loginPanel;

    private JPanel registerPanel;

    public Authentication() {
        authenticationCardLayout = new CardLayout();
        authenticationPanel = new JPanel(authenticationCardLayout);

        initLoginPanel();
        initRegisterPanel();

        authenticationPanel.add(loginPanel, "login");
        authenticationPanel.add(registerPanel, "register");

        authenticationCardLayout.show(authenticationPanel, "login");
    }

    private void initRegisterPanel() {
        registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 1;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/logo.png"));
        Image resizedImageIcon = imageIcon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(resizedImageIcon));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        registerPanel.add(iconLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel createUsernameLabel = new JLabel("Create Username");
        registerPanel.add(createUsernameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        JTextField usernameField = new JTextField();
        registerPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel createPasswordLabel = new JLabel("Create Password");
        registerPanel.add(createPasswordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        JPasswordField passwordField = new JPasswordField();
        registerPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton registerButton = new JButton("Register");
        registerPanel.add(registerButton, gbc);
    }

    private void initLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 1;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/logo.png"));
        Image resizedImageIcon = imageIcon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(resizedImageIcon));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(iconLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel usernameLabel = new JLabel("Username");
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        JTextField usernameField = new JTextField();
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Password");
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton, gbc);
    }

    public JPanel getAuthenticationPanel() {
        return authenticationPanel;
    }
}
