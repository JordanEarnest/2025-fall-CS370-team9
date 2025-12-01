package com.recipez.ui;

import com.recipez.user.BodyGoal;
import com.recipez.user.User;
import com.recipez.util.DietType;
import com.recipez.util.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationUI {

    private CardLayout authenticationCardLayout;

    private JPanel authenticationPanel;

    private JPanel loginPanel;

    private JPanel registerPanel;

    private JLabel errorLabelForRegistration;

    public AuthenticationUI() {
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
        gbc.gridwidth = 3;
        registerPanel.add(iconLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3;
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

        // HANDLE USER INPUT DATA LIKE WEIGHT AND HEIGHT
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 3;
        JLabel weightLabel = new JLabel("Weight (lbs)");
        registerPanel.add(weightLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        JTextField weightTextField = new JTextField();
        registerPanel.add(weightTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 1;
        JLabel heightLabel = new JLabel("Height (ft + in)");
        registerPanel.add(heightLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        Integer[] heightFeetOptions = {1, 2, 3, 4, 5, 6, 7};
        JComboBox<Integer> heightFeetComboBox = new JComboBox<>(heightFeetOptions);
        registerPanel.add(heightFeetComboBox, gbc);

        gbc.gridx = 2; gbc.gridy = 4;
        Integer[] heightInchesOptions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        JComboBox<Integer> heightInchesComboBox = new JComboBox<>(heightInchesOptions);
        registerPanel.add(heightInchesComboBox, gbc);

        // AGE
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 3;
        JLabel ageLabel = new JLabel("Age");
        registerPanel.add(ageLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        JTextField ageTextField = new JTextField();
        registerPanel.add(ageTextField, gbc);

        // GENDER
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 3;
        JLabel genderLabel = new JLabel("Gender");
        registerPanel.add(genderLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 6;
        String[] genderOptions = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);
        registerPanel.add(genderComboBox, gbc);

        // DIET TYPES
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 3;
        JLabel dietTypeLabel = new JLabel("Diet Type");
        registerPanel.add(dietTypeLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 7;
        String[] dietTypeOptions = {"NONE", "VEGAN", "VEGETARIAN", "PESCATARIAN",  "KETO", "CARNIVOROUS"};
        JComboBox<String> dietTypeComboBox = new JComboBox<>(dietTypeOptions);
        registerPanel.add(dietTypeComboBox, gbc);

        // BODY GOAL TYPES
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 3;
        JLabel bodyGoalLabel = new JLabel("Body Goal");
        registerPanel.add(bodyGoalLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 8;
        String[] bodyGoalOptions = {"CUT", "BULK", "MAINTAIN"};
        JComboBox<String> bodyGoalComboBox = new JComboBox<>(bodyGoalOptions);
        registerPanel.add(bodyGoalComboBox, gbc);


        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 3;
        JButton createAccountButton = new JButton("Create Account");
        registerPanel.add(createAccountButton, gbc);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can also check which button was pressed if you have multiple
                if (e.getSource() == createAccountButton) {
                    createAccount(usernameField.getText(), passwordField.getText(), weightTextField.getText(), (Integer)heightFeetComboBox.getSelectedItem(), (Integer)heightInchesComboBox.getSelectedItem(), (String)genderComboBox.getSelectedItem(), ageTextField.getText(), (String)dietTypeComboBox.getSelectedItem(), (String)bodyGoalComboBox.getSelectedItem());
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 3;
        JButton backToLoginButton = new JButton("Back to Login");
        registerPanel.add(backToLoginButton, gbc);


        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can also check which button was pressed if you have multiple
                if (e.getSource() == backToLoginButton) {
                    backToLoginButtonClicked();

                    // Reset all text boxes
                    usernameField.setText("");
                    passwordField.setText("");
                    heightFeetComboBox.setSelectedIndex(0);
                    heightInchesComboBox.setSelectedIndex(0);
                    genderComboBox.setSelectedIndex(0);
                    ageTextField.setText("");
                    dietTypeComboBox.setSelectedIndex(0);
                    bodyGoalComboBox.setSelectedIndex(0);
                    errorLabelForRegistration.setText("");

                }
            }
        });

        gbc.gridx = 0; gbc.gridy = 11;
        gbc.gridwidth = 3;
        errorLabelForRegistration = new JLabel();
        errorLabelForRegistration.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabelForRegistration.setForeground(Color.RED);
        registerPanel.add(errorLabelForRegistration, gbc);

    }

    private void initLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 1;

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/logo.png"));
        Image resizedImageIcon = imageIcon.getImage().getScaledInstance(256, 256, Image.SCALE_SMOOTH);
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

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton registerButton = new JButton("Register?");
        loginPanel.add(registerButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can also check which button was pressed if you have multiple
                if (e.getSource() == registerButton) {
                    registerButtonClicked();
                }
            }
        });


    }

    private void createAccount(String username, String password, String weight, int heightFeet, int heightInches, String gender, String age, String dietType, String bodyGoal) {
        int validationCode = validateCreateAccountData(username, password, weight, age);

        String errorMessage = "";

        if (validationCode != 0) {
            if (validationCode == 100) {
                errorMessage = "Empty username";
            } else if (validationCode == 200) {
                errorMessage = "Empty password";
            } else if  (validationCode == 300) {
                errorMessage = "Empty weight";
            } else if  (validationCode == 301) {
                errorMessage = "Negative weight";
            } else if (validationCode == 302) {
                errorMessage = "Weight must be a number";
            } else if (validationCode == 400) {
                errorMessage = "Empty age";
            } else if (validationCode == 401) {
                errorMessage = "Negative age";
            } else if (validationCode == 402) {
                errorMessage = "Age must be a number";
            }
            errorLabelForRegistration.setText(errorMessage);
            return;
        }

        double heightInFeet = heightFeet + (heightInches / 12.0);
        boolean isMan;
        isMan = gender.equals("Male");
        double convertedWeight = Double.parseDouble(weight);
        int convertedAge = Integer.parseInt(age);

        // passed safety checks, we can now create the account
        User newUser = new User(username, password, BodyGoal.valueOf(bodyGoal), DietType.valueOf(dietType), convertedWeight, heightInFeet, convertedAge, isMan);

        authenticationCardLayout.show(authenticationPanel, "login");
    }

    private int validateCreateAccountData(String username, String password, String weight, String age) {
        // Username checks: 100
        if (username.equals(""))
            return 100; // empty username
        // Password checks: 200
        if (password.equals(""))
            return 200; // empty password
        // Weight checks: 300
        try {
            if (weight.equals(""))
                return 300; // empty weight
            else if (Double.parseDouble(weight) < 0)
                return 301; // negative weight
        } catch (NumberFormatException e) {
            return 302; // not a number
        }

        // Age checks: 400
        try {
            if (age.equals(""))
                return 400; // empty weight
            else if (Integer.parseInt(age) < 0)
                return 401; // negative age

        } catch (NumberFormatException e) {
            return 402; // not a number
        }

        return 0; //success
    }

    private void registerButtonClicked() {
        authenticationCardLayout.show(authenticationPanel, "register");
    }

    private void backToLoginButtonClicked() {
        authenticationCardLayout.show(authenticationPanel, "login");
    }

    public JPanel getAuthenticationPanel() {
        return authenticationPanel;
    }
}
